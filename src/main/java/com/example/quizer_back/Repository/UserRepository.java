package com.example.quizer_back.Repository;

import com.example.quizer_back.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {


}
