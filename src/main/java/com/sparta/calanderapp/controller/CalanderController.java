package com.sparta.calanderapp.controller;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import com.sparta.calanderapp.dto.CalanderResponseDTO;
import com.sparta.calanderapp.services.CalanderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class CalanderController {
    private final CalanderService calanderService;
    public CalanderController(CalanderService calanderService) {
        this.calanderService = calanderService;
    }


    @PostMapping("/create")
    public CalanderResponseDTO createCalander (@RequestBody CalanderRequestDTO requestDTO) {
        return calanderService.createCalander(requestDTO);

    }
    @GetMapping("/list")
    public List<CalanderResponseDTO> getCalanders() {
        return calanderService.getCalanders();
    }
    @GetMapping("/{id}")
    public CalanderResponseDTO getCalanderbyId(@PathVariable Long id) {
        return calanderService.getCalanderById(id);
    }
    @PutMapping("/update/{id}")
    public Long updateCalander(@PathVariable Long id, @RequestBody CalanderRequestDTO requestDTO) {
        return calanderService.updateCalander(id, requestDTO);
    }
    @DeleteMapping("/delete/{id}")
    public Long deleteCalander(@PathVariable Long id, @RequestParam String password) {
        System.out.println(password);
        calanderService.deleteCalander(id, password);
        return id;
    }
}
