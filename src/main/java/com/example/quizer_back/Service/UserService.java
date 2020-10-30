package com.example.quizer_back.Service;

import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Model.User;
import com.example.quizer_back.Model.UserQuiz;
import com.example.quizer_back.Repository.UserQuizRepository;
import com.example.quizer_back.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public Iterable<User> getUserList(){
        return userRepository.findAll();
    }


    @Transactional
    public User getUserByEmail(String email) throws NoSuchElementException{
        Optional<User> userOpt = userRepository.getUserByEmail(email);
        if(userOpt.isPresent())
            return userOpt.get();
        else throw new NoSuchElementException();

    }

    @Transactional
    public void saveUser(User user){

        userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }

    public User getUserById(int id) {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isPresent())
            return userOpt.get();
        else throw new NoSuchElementException();
    }

    @Transactional
    public void finishQuiz(User user, Quiz quiz){
        userQuizRepository.save(new UserQuiz(user,quiz,100));
    }

}
