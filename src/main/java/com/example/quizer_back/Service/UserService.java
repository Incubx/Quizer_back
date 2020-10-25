package com.example.quizer_back.Service;

import com.example.quizer_back.Model.Quiz;
import com.example.quizer_back.Model.User;
import com.example.quizer_back.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public Iterable<User> getUserList(){
        return userRepository.findAll();
    }

    @Transactional
    public User getUserById(int id) throws NoSuchElementException {
        Optional<User> userOpt = userRepository.findById(id);
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

    @Transactional
    public void updateUser(User user){
        userRepository.save(user);
    }
}
