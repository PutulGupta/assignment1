package com.example.assignment1.repository;

import com.example.assignment1.model.Salt;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface SaltRepository extends MongoRepository<Salt, String>{

    Salt findByIdAndDeletedFalse(String id);
    Salt findBySaltNameAndControl(String saltName, boolean control);

    //List<Salt> search(String saltName);
}
