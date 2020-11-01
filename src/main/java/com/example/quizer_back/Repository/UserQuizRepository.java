package com.example.quizer_back.Repository;

import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Model.User;
import com.example.quizer_back.Model.UserQuiz;
import com.example.quizer_back.Model.UserQuizId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserQuizRepository extends CrudRepository<UserQuiz, UserQuizId> {

    Optional<UserQuiz> findByUserAndQuiz(User user, Quiz quiz);

    List<UserQuiz> findByUser(User user);
}
