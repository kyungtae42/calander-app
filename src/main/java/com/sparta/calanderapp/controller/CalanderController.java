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
@RequestMapping("/app")
public class CalanderController {
    private final CalanderService calanderService;
    public CalanderController(CalanderService calanderService) {
        this.calanderService = calanderService;
    }


    @PostMapping("/create")
    @ResponseBody
    public CalanderResponseDTO createCalander (@RequestBody CalanderRequestDTO requestDTO) {
        return calanderService.createCalander(requestDTO);

    }
    @GetMapping("/list")
    @ResponseBody
    public List<CalanderResponseDTO> getCalanders() {
        return calanderService.getCalanders();
    }
    @GetMapping("/{id}")
    public String getCalanderbyId(@PathVariable Long id, Model model) {
        calanderService.getCalanderById(id, model);
        return "detail";
    }
    @GetMapping("/update/{id}")
    public String updateGetCalanderbyId(@PathVariable Long id, Model model) {
        calanderService.getCalanderById(id, model);
        return "update";
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Long updateCalander(@PathVariable Long id, @RequestBody CalanderRequestDTO requestDTO) {
        return calanderService.updateCalander(id, requestDTO);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCalander(@PathVariable Long id, @RequestParam String password) {
        calanderService.deleteCalander(id, password);
        return "redirect:/index.html";
    }
}
