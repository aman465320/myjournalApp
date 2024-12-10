package com.example.myJournalApp.services;

import com.example.myJournalApp.entity.JournalEntry;
import com.example.myJournalApp.entity.UserEntity;
import com.example.myJournalApp.repository.JournalEntryRepository;
import com.example.myJournalApp.repository.UserRespository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    UserService userService;
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        UserEntity userEntity = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        userEntity.getJournalEntries().add(saved);
        userService.saveUser(userEntity);
    }

    public List<JournalEntry> getAllJournals() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId id) {
        System.out.println(journalEntryRepository.findById(id).toString());
        return journalEntryRepository.findById(id);
    }

    public void deleteJournalById(ObjectId id, String userName) {
        UserEntity user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id); 
    }

}
