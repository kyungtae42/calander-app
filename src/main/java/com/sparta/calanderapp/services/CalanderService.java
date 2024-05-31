package com.sparta.calanderapp.services;

import com.sparta.calanderapp.dto.CalanderRequestDTO;
import com.sparta.calanderapp.dto.CalanderResponseDTO;
import com.sparta.calanderapp.entity.Calander;
import com.sparta.calanderapp.entity.User;
import com.sparta.calanderapp.repository.CalanderRepository;
import com.sparta.calanderapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CalanderService {
    private final CalanderRepository calanderRepository;
    private final UserRepository userRepository;
    public CalanderResponseDTO createCalander(CalanderRequestDTO requestDTO, User user) {
        Calander calander = new Calander(requestDTO, user);
        calanderRepository.save(calander);
        return new CalanderResponseDTO(calander);
    }

    public List<CalanderResponseDTO> getCalanders() {
        return calanderRepository.findAll().stream().map(CalanderResponseDTO::new).toList();
    }

    public CalanderResponseDTO getCalanderById(Long id) {
        Calander calander = findById(id);
        CalanderResponseDTO calanderResponseDTO = new CalanderResponseDTO(calander);
        return calanderResponseDTO;
    }
    public void getUpdateCalanderById(Long id, CalanderRequestDTO requestDTO) {
        Calander calander = findById(id);
        requestDTO.setId(calander.getId());
        requestDTO.setTitle(calander.getTitle());
        requestDTO.setContent(calander.getContent());
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

    public Long deleteCalander(Long id, String password) {
        Calander calander = findById(id);
        if(calander.getPassword().equals(password)) {
            calanderRepository.deleteById(id);
            return id;
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    public Calander findById(Long id) {
        return calanderRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 일정입니다")
        );
    }

    public boolean isUser(Long id, User user) {
        Calander calander = findById(id);
        return calander.getUser().equals(user);
    }
}
