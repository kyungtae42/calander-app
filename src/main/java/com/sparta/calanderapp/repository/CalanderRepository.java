package com.sparta.calanderapp.repository;

import com.sparta.calanderapp.entity.Calander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalanderRepository extends JpaRepository<Calander, Long> {

}
