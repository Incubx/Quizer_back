package com.example.quizer_back.Service;


import com.example.quizer_back.Model.*;
import com.example.quizer_back.Repository.CategoryRepository;
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
    private CategoryRepository categoryRepository;

    /*Setters _______________________________________________ */
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

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /*Quiz CRUD _______________________________________________ */
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

    /*Quiz List getters_______________________________________________ */
    @Transactional
    public Iterable<Quiz> getQuizList() {
        return quizRepository.findAll();
    }

    @Transactional
    public List<Quiz> getQuizListByCategory(Category category) {
        return quizRepository.getQuizzesByCategory(category);
    }

    @Transactional
    public Quiz getQuizWithRandomQuestions(int id) {

        Quiz quiz = getQuizById(id);
        List<Question> questionList = quiz.getQuestions();
        List<Question> randomQuestions = new ArrayList<>();
        Collections.shuffle(questionList);
        for (int i = 0; i < quiz.getSize() && i < questionList.size(); i++) {
            randomQuestions.add(questionList.get(i));
        }
        quiz.setQuestions(randomQuestions);
        return quiz;
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

    /* Question methods  _________________________________________________________________________________ */
    @Transactional
    public Question getQuestionById(int id) throws NoSuchElementException {
        Optional<Question> questionOpt = questionRepository.findById(id);
        if (questionOpt.isPresent())
            return questionOpt.get();
        else throw new NoSuchElementException();
    }

    @Transactional
    public void saveQuestion(Question question) {
        for (Answer answer : question.getAnswers()) {
            answer.setQuestion(question);
        }
        questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Question question) {
        questionRepository.deleteById(question.getId());
    }

    //TODO Category CRUD operations
    /*Category CRUD______________________________________________ */
    @Transactional
    public List<Category> getCategoryList() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Transactional
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).get();
    }
}
