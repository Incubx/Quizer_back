package com.example.quizer_back.Repository;


import com.example.quizer_back.Model.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository  extends CrudRepository<Quiz, Integer> {

}
