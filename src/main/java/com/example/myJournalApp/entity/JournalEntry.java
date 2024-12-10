package com.example.myJournalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String heading;
    private String content;

    private LocalDateTime date;


}
