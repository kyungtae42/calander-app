package com.sparta.calanderapp.controller;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import com.sparta.calanderapp.dto.CalanderResponseDTO;
import com.sparta.calanderapp.model.Calander;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/app")
public class CalanderController {
    private final JdbcTemplate jdbcTemplate;
    public CalanderController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/create")
    public CalanderResponseDTO createCalander (@RequestBody CalanderRequestDTO requestDTO) {
        Calander calander = new Calander(requestDTO);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO calander (title, content, name, date, password) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, calander.getTitle());
                    preparedStatement.setString(2, calander.getName());
                    preparedStatement.setString(3, calander.getContent());
                    preparedStatement.setDate(4, calander.getDate());
                    preparedStatement.setString(5, calander.getPassword());
                    return preparedStatement;
                },
                keyHolder);
        long id = keyHolder.getKey().longValue();
        calander.setId(id);

        CalanderResponseDTO calanderResponseDTO = new CalanderResponseDTO(calander);
        return calanderResponseDTO;
    }
    @GetMapping("/list")
    public List<CalanderResponseDTO> getCalanders() {
        String sql = "SELECT * FROM calander";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String name = rs.getString("name");
            Date date = rs.getDate("date");
            String password = rs.getString("password");
            return new CalanderResponseDTO(id, title, content, name, date, password);
        });
    }
}
