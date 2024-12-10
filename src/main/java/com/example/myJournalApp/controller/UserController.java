package com.example.myJournalApp.controller;

import com.example.myJournalApp.entity.JournalEntry;
import com.example.myJournalApp.entity.UserEntity;
import com.example.myJournalApp.services.JournalEntryService;
import com.example.myJournalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        List<UserEntity> allList = userService.getAllUsers();
        return allList == null || allList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(allList,HttpStatus.OK);

    }

    @PostMapping()
    public void createUser(@RequestBody UserEntity user){
        userService.saveUser(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user,@PathVariable String userName){
       UserEntity fetchedUser = userService.findByUserName(userName);
       if(fetchedUser != null ){
           fetchedUser.setUserName(user.getUserName());
           fetchedUser.setPassword(user.getPassword());
           userService.saveUser(user);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
