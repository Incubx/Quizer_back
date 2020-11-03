package com.example.quizer_back.Service;


import com.example.quizer_back.Model.*;
import com.example.quizer_back.Repository.QuestionRepository;
import com.example.quizer_back.Repository.QuizRepository;
import com.example.quizer_back.Repository.UserQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class QuizService {

    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;

    private UserQuizRepository userQuizRepository;

    @Autowired
    public void setUserQuizRepository(UserQuizRepository userQuizRepository) {
        this.userQuizRepository = userQuizRepository;
    }

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
    public List<Quiz> getNotEmptyQuizList() {
        List<Quiz> quizList = (List<Quiz>) quizRepository.findAll();
        quizList.removeIf(quiz -> quiz.getSize() == 0);
        return quizList;
    }

    @Transactional
    public List<Quiz> getUserQuizList(User user) {
        List<Quiz> quizList = getNotEmptyQuizList();
        for (Quiz quiz : quizList) {
            Optional<UserQuiz> userQuiz = userQuizRepository.findByUserAndQuiz(user, quiz);
            quiz.setCompleted(userQuiz.isPresent() && userQuiz.get().getRating() > 50);
        }
        return quizList;
    }

    @Transactional
    public Quiz getQuizById(int id) throws NoSuchElementException {
        Optional<Quiz> quizOpt = quizRepository.findById(id);
        if (quizOpt.isPresent())
            return quizOpt.get();
        else throw new NoSuchElementException();
    }

    @Transactional
    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Transactional
    public void deleteQuizById(int id) {
        quizRepository.deleteById(id);
    }


    @Transactional
    public Question getQuestionById(int id) throws NoSuchElementException {
        Optional<Question> questionOpt = questionRepository.findById(id);
        if (questionOpt.isPresent())
            return questionOpt.get();
        else throw new NoSuchElementException();
    }

    @Transactional
    public void addQuestion(Question question) {
        for (Answer answer : question.getAnswers()) {
            answer.setQuestion(question);
        }
        questionRepository.save(question);
        changeQuizSize(question, 1);
    }

    @Transactional
    public void editQuestion(Question question) {
        questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Question question) {
        changeQuizSize(question, -1);
        questionRepository.deleteById(question.getId());
    }

    private void changeQuizSize(Question question, int changeNumber) {
        Quiz quiz = question.getQuiz();
        quiz.changeSize(changeNumber);
        quizRepository.save(quiz);
    }
}
