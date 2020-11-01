package com.example.quizer_back.Service;

import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Model.User;
import com.example.quizer_back.Model.UserQuiz;
import com.example.quizer_back.Repository.UserQuizRepository;
import com.example.quizer_back.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserQuizRepository userQuizRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserQuizRepository(UserQuizRepository userQuizRepository) {
        this.userQuizRepository = userQuizRepository;
    }

    @Transactional
    public Iterable<User> getUserList() {
        return userRepository.findAll();
    }


    @Transactional
    public User getUserByNickname(String nickname) throws NoSuchElementException {
        Optional<User> userOpt = userRepository.getUserByNickname(nickname);
        if (userOpt.isPresent())
            return userOpt.get();
        else throw new NoSuchElementException();

    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public User getUserById(int id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent())
            return userOpt.get();
        else throw new NoSuchElementException();
    }

    @Transactional
    public void finishQuiz(User user, Quiz quiz, int rating) {
        Optional<UserQuiz> userQuizOpt = userQuizRepository.findByUserAndQuiz(user, quiz);
        if (userQuizOpt.isPresent()) {

            UserQuiz userQuiz = userQuizOpt.get();
            int curRating = userQuiz.getRating();
            if (curRating < rating) userQuiz.setRating(rating);
            userQuizRepository.save(userQuiz);
        } else {
            userQuizRepository.save(new UserQuiz(user, quiz, rating));
        }

    }

    public HashMap<Quiz,Integer> getUserRatingTable(User user) {
       List<UserQuiz> userQuizList = userQuizRepository.findByUser(user);
        HashMap<Quiz,Integer> quizRatingMap = new LinkedHashMap<>();
        for(UserQuiz userQuiz:userQuizList){
            quizRatingMap.put(userQuiz.getQuiz(),userQuiz.getRating());
        }
        return quizRatingMap;

    }
}
