package com.example.quizer_back.Repository;


import com.example.quizer_back.Model.Category;
import com.example.quizer_back.Model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository  extends CrudRepository<Quiz, Integer> {

    List<Quiz> getQuizzesByCategory(Category category);

}
