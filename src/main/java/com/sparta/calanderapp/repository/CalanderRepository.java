package com.sparta.calanderapp.repository;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import com.sparta.calanderapp.dto.CalanderResponseDTO;
import com.sparta.calanderapp.model.Calander;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@AllArgsConstructor
public class CalanderRepository {
    private final JdbcTemplate jdbcTemplate;

    public Calander insertRow(Calander calander) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO calander (title, content, name, date, password) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, calander.getTitle());
                    preparedStatement.setString(2, calander.getContent());
                    preparedStatement.setString(3, calander.getName());
                    preparedStatement.setDate(4, calander.getDate());
                    preparedStatement.setString(5, calander.getPassword());
                    return preparedStatement;
                },
                keyHolder);
        long id = keyHolder.getKey().longValue();
        calander.setId(id);
        return calander;
    }

    public List<CalanderResponseDTO> getRows() {
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

    public Calander getRowById(Long id) {
        String sql = "SELECT * FROM calander WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()){
                Calander calander = new Calander();
                calander.setId(resultSet.getLong("id"));
                calander.setTitle(resultSet.getString("title"));
                calander.setContent(resultSet.getString("content"));
                calander.setName(resultSet.getString("name"));
                calander.setDate(resultSet.getDate("date"));
                calander.setPassword(resultSet.getString("password"));
                return calander;
            } else {
                return null;
            }
        }, id);
    }

    public void updateRow(Calander calander, CalanderRequestDTO requestDTO) {
        if(calander.getPassword().equals(requestDTO.getPassword())) {
            if(calander != null) {
                String sql = "UPDATE calander SET title = ?, content = ?, name = ?, date = ? where id = ?";
                jdbcTemplate.update(sql, requestDTO.getTitle(), requestDTO.getContent(), requestDTO.getName(), requestDTO.getDate(), calander.getId());
            } else {
                throw new IllegalArgumentException("존재하지 않는 일정입니다.");
            }
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    public String deleteRow(Calander calander, String password) {
        if(calander.getPassword().equals(password)) {
            if(calander != null) {
                String sql = "DELETE FROM calander WHERE id = ?";
                jdbcTemplate.update(sql, calander.getId());

                return "redirect:/index.html";
            } else {
                throw new IllegalArgumentException("존재하지 않는 일정입니다.");
            }
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
