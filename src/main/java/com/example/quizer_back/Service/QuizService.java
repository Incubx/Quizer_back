package com.example.quizer_back.Service;


import com.example.quizer_back.Model.Answer;
import com.example.quizer_back.Model.Question;
import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Repository.QuestionRepository;
import com.example.quizer_back.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
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
    public Quiz getQuizById(int id) throws NoSuchElementException{
        Optional<Quiz> quizOpt = quizRepository.findById(id);
        if(quizOpt.isPresent())
            return quizOpt.get();
        else throw new NoSuchElementException();
    }

    @Transactional
    public void saveQuiz(Quiz quiz){
        quizRepository.save(quiz);
    }

    @Transactional
    public void deleteQuizById(int id){
        quizRepository.deleteById(id);
    }


    @Transactional
    public Question getQuestionById(int id)throws NoSuchElementException{
        Optional<Question> questionOpt = questionRepository.findById(id);
        if(questionOpt.isPresent())
            return questionOpt.get();
        else throw new NoSuchElementException();
    }

    @Transactional
    public void addQuestion(Question question){
        for(Answer answer:question.getAnswers()){
            answer.setQuestion(question);
        }
        questionRepository.save(question);
        changeQuizSize(question,1);
    }

    @Transactional
    public void editQuestion(Question question){
        questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Question question) {
        changeQuizSize(question,-1);
        questionRepository.deleteById(question.getId());
    }

    private void changeQuizSize(Question question,int changeNumber){
        Quiz quiz =question.getQuiz();
        quiz.changeSize(changeNumber);
        quizRepository.save(quiz);
    }
}
