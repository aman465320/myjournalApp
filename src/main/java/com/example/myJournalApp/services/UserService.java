package com.example.myJournalApp.services;

import com.example.myJournalApp.entity.JournalEntry;
import com.example.myJournalApp.entity.UserEntity;
import com.example.myJournalApp.repository.JournalEntryRepository;
import com.example.myJournalApp.repository.UserRespository;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRespository userRespository;

    public void saveUser(UserEntity userEntity){
        userRespository.save(userEntity);
    }

    public List<UserEntity> getAllUsers(){
        return userRespository.findAll();
    }

    public Optional<UserEntity> getUserById(ObjectId id){
        System.out.println(userRespository.findById(id).toString());
        return userRespository.findById(id);
    }

    public UserEntity findByUserName(String userName){
        return userRespository.findByUserName(userName);
    }

    public void deleteUserById(ObjectId id){
        userRespository.deleteById(id);
    }

}
