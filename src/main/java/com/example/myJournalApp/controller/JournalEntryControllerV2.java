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

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;
    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        UserEntity userEntity = userService.findByUserName(userName);
        if(userEntity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<JournalEntry> allList = userEntity.getJournalEntries();
        return allList == null || allList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(allList,HttpStatus.OK);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry journalEntry, @PathVariable String userName) {
        try {
            journalEntryService.saveEntry(journalEntry,userName);
            return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
        JournalEntry journalEntry = journalEntryService.getJournalEntryById(id).orElse(null);
        return journalEntry != null ? new ResponseEntity<>(journalEntry,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

//        return journalEntryService.getJournalEntryById(id).orElse(null);
    }

    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId id, @PathVariable String userName) {
        journalEntryService.deleteJournalById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
        JournalEntry oldJournalEntry = journalEntryService.getJournalEntryById(id).orElse(null);
//        if(oldJournalEntry != null ){
//            oldJournalEntry.setContent(journalEntry.getContent() != null ? journalEntry.getContent() : oldJournalEntry.getContent());
//            oldJournalEntry.setHeading(journalEntry.getHeading() != null ? journalEntry.getHeading() : oldJournalEntry.getHeading());
//            journalEntryService.saveEntry(oldJournalEntry, userEntity);
//            return new ResponseEntity<>(oldJournalEntry,HttpStatus.CREATED);
//        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
