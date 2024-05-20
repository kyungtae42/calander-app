package com.sparta.calanderapp.controller;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import com.sparta.calanderapp.dto.CalanderResponseDTO;
import com.sparta.calanderapp.model.Calander;
import com.sparta.calanderapp.services.CalanderService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class CalanderController {
    private final CalanderService calanderService;
    public CalanderController(CalanderService calanderService) {
        this.calanderService = calanderService;
    }

    @GetMapping("/")
    public String getCalanders(Model model) {
        calanderService.getCalanders(model);
        return "index";
    }
    @GetMapping("/create")
    public String getCreateCalander(@ModelAttribute("requestDTO") CalanderRequestDTO requestDTO) {
        return "create";
    }
    @PostMapping("/create")
    public String createCalander (@ModelAttribute CalanderRequestDTO requestDTO) {
        calanderService.createCalander(requestDTO);
        return "redirect:/";
    }
    @GetMapping("/{id}")
    public String getCalanderbyId(@PathVariable Long id, Model model) {
        calanderService.getCalanderById(id, model);
        return "detail";
    }
    @GetMapping("/update/{id}")
    public String updateGetCalanderbyId(@PathVariable Long id, @ModelAttribute("requestDTO") CalanderRequestDTO requestDTO) {
        calanderService.getUpdateCalanderById(id, requestDTO);
        return "update";
    }
    @PutMapping("/update/{id}")
    public String updateCalander(@PathVariable Long id, @ModelAttribute CalanderRequestDTO requestDTO) {
        calanderService.updateCalander(id, requestDTO);
        return "redirect:/";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCalander(@PathVariable Long id, @RequestParam String password) {
        calanderService.deleteCalander(id, password);
        return "redirect:/";
    }
}
