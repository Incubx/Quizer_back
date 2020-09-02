package com.example.quizer_back.Service;


import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Repository.QuestionRepository;
import com.example.quizer_back.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;


    @Autowired
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Transactional
    public Iterable<Quiz> getQuizList() {
        return quizRepository.findAll();

    }

    @Transactional
    public Quiz getQuizById(int id) {
        Optional<Quiz> quizOpt = quizRepository.findById(id);
        return quizOpt.orElse(null);
    }

    @Transactional
    public void addQuiz(Quiz quiz){
        quizRepository.save(quiz);
    }

    @Transactional
    public void deleteQuizById(int id){
        quizRepository.deleteById(id);
    }

    @Transactional
    public void updateQuiz(Quiz quiz){
        quizRepository.save(quiz);
    }
}
