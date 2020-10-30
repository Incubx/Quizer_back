package com.example.quizer_back.Repository;

import com.example.quizer_back.Model.UserQuiz;
import com.example.quizer_back.Model.UserQuizId;
import org.springframework.data.repository.CrudRepository;

public interface UserQuizRepository extends CrudRepository<UserQuiz, UserQuizId> {
}
