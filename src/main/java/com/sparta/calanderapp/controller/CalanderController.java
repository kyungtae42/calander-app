package com.sparta.calanderapp.controller;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import com.sparta.calanderapp.dto.CalanderResponseDTO;
import com.sparta.calanderapp.security.UserDetailsImpl;
import com.sparta.calanderapp.services.CalanderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
@Slf4j
public class CalanderController {
    private final CalanderService calanderService;
    public CalanderController(CalanderService calanderService) {
        this.calanderService = calanderService;
    }


    @PostMapping("/create")
    public CalanderResponseDTO createCalander (@RequestBody @Valid CalanderRequestDTO requestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return null;
        }
        return calanderService.createCalander(requestDTO, userDetails.getUser());
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
    public Long updateCalander(@PathVariable Long id, @RequestBody @Valid CalanderRequestDTO requestDTO,  @AuthenticationPrincipal UserDetailsImpl userDetails,  BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return null;
        }
        return calanderService.updateCalander(id, requestDTO, userDetails.getUser());
    }
    @DeleteMapping("/delete/{id}")
    public Long deleteCalander(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String password) {
        System.out.println(password);
        calanderService.deleteCalander(id, password, userDetails.getUser());
        return id;
    }
}
