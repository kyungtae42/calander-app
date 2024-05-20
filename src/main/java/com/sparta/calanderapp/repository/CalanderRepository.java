package com.sparta.calanderapp.repository;

import com.sparta.calanderapp.model.Calander;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalanderRepository extends JpaRepository<Calander, Long> {

}
