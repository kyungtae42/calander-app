package com.sparta.calanderapp.services;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import com.sparta.calanderapp.dto.CalanderResponseDTO;
import com.sparta.calanderapp.model.Calander;
import com.sparta.calanderapp.repository.CalanderRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.ui.Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class CalanderService {
    private final JdbcTemplate jdbcTemplate;
    private final CalanderRepository calanderRepository;

    public CalanderService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.calanderRepository = new CalanderRepository(jdbcTemplate);
    }

    public CalanderResponseDTO createCalander(CalanderRequestDTO requestDTO) {
        Calander calander = new Calander(requestDTO);
        Calander insertedCalander = calanderRepository.insertRow(calander);
        CalanderResponseDTO calanderResponseDTO = new CalanderResponseDTO(insertedCalander);
        return calanderResponseDTO;
    }

    public List<CalanderResponseDTO> getCalanders() {
        return calanderRepository.getRows();
    }

    public void getCalanderById(Long id, Model model) {
        Calander calander = findById(id);
        model.addAttribute("id", calander.getId());
        model.addAttribute("title", calander.getTitle());
        model.addAttribute("content", calander.getContent());
        model.addAttribute("name", calander.getName());
        model.addAttribute("date", calander.getDate());
        model.addAttribute("password", calander.getPassword());
    }

    public Long updateCalander(Long id, CalanderRequestDTO requestDTO) {
        Calander calander = findById(id);
        calanderRepository.updateRow(calander, requestDTO);
        return id;
    }

    public String deleteCalander(Long id, String password) {
        Calander calander = findById(id);
        return calanderRepository.deleteRow(calander, password);
    }

    public Calander findById(Long id) {
        return calanderRepository.getRowById(id);
    }
}
