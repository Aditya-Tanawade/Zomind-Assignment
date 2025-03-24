package com.Zomind.assignment.entities;

import com.Zomind.assignment.entities.enums.Priority;
import com.Zomind.assignment.entities.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "testCases")
public class TestCaseEntity {

    @Id
    private String id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public TestCaseEntity() {
        this.createdAt = LocalDateTime.now();
    }
}
