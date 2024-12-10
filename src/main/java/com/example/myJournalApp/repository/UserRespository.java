package com.example.myJournalApp.repository;

import com.example.myJournalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRespository extends MongoRepository<UserEntity, ObjectId> {
  UserEntity findByUserName(String userName);
}
