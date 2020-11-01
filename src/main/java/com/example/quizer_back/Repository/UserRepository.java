package com.example.quizer_back.Repository;

import com.example.quizer_back.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> getUserByNickname(String nickname);
}
