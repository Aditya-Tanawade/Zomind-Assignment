package com.Zomind.assignment.repositories;

import com.Zomind.assignment.entities.TestCaseEntity;
import com.Zomind.assignment.entities.enums.Priority;
import com.Zomind.assignment.entities.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestCaseRepository extends MongoRepository<TestCaseEntity, String> {
    List<TestCaseEntity> findAllByStatusAndPriority(Status status, Priority priority, Pageable pageable);
}
