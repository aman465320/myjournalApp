//package com.example.myJournalApp.controller;
//
//import com.example.myJournalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//    private Map<Long,JournalEntry> journalEntries = new HashMap();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createJournal(@RequestBody JournalEntry journalEntry){
//        journalEntries.put(journalEntry.getId(),journalEntry);
//        return true;
//    }
//
//    @GetMapping("/id/{Id}")
//    public JournalEntry getJournalEntryById(@PathVariable Long Id){
//        return journalEntries.get(Id);
//    }
//
//    @DeleteMapping("/id/{id}")
//    public boolean deleteJournalById(@PathVariable Long id){
//        if(!journalEntries.containsKey(id)) return false;
//        journalEntries.remove(id);
//        return true;
//    }
//
//    @PutMapping("/id/{id}")
//    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry journalEntry) {
//        return journalEntries.put(id, journalEntry);
//    }
//}
