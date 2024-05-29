package com.sparta.calanderapp.repository;

import com.sparta.calanderapp.model.Calander;
import com.sparta.calanderapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCalander(Calander calander);
}
