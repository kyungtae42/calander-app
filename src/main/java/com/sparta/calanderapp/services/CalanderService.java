package com.sparta.calanderapp.services;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import com.sparta.calanderapp.dto.CalanderResponseDTO;
import com.sparta.calanderapp.model.Calander;
import com.sparta.calanderapp.repository.CalanderRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Component
@Service
public class CalanderService {
    private final CalanderRepository calanderRepository;

    public CalanderService(CalanderRepository calanderRepository) {
        this.calanderRepository = calanderRepository;
    }

    public CalanderResponseDTO createCalander(CalanderRequestDTO requestDTO) {
        Calander calander = new Calander(requestDTO);
        Calander insertedCalander = calanderRepository.save(calander);
        CalanderResponseDTO calanderResponseDTO = new CalanderResponseDTO(insertedCalander);
        return calanderResponseDTO;
    }

    public List<CalanderResponseDTO> getCalanders() {
        return calanderRepository.findAll().stream().map(CalanderResponseDTO::new).toList();
    }

    public void getCalanderById(Long id, Model model) {
        Calander calander = findById(id);
        model.addAttribute("row", calander);
    }
    @Transactional
    public Long updateCalander(Long id, CalanderRequestDTO requestDTO) {
        Calander calander = findById(id);
        if(calander.getPassword().equals(requestDTO.getPassword())) {
            calander.update(requestDTO);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return id;
    }

    public void deleteCalander(Long id, String password) {
        Calander calander = findById(id);
        if(calander.getPassword().equals(password)) {
            calanderRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    public Calander findById(Long id) {
        return calanderRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 일정입니다")
        );
    }
}
