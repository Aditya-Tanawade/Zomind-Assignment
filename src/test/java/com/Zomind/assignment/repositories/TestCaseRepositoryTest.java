package com.Zomind.assignment.repositories;

import com.Zomind.assignment.entities.TestCaseEntity;
import com.Zomind.assignment.entities.enums.Priority;
import com.Zomind.assignment.entities.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class TestCaseRepositoryTest {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Test
    void findAllByStatusAndPriority_ShouldReturnCorrectResults() {
        // Arrange
        TestCaseEntity testCase = new TestCaseEntity();
        testCase.setTitle("Test Case 1");
        testCase.setStatus(Status.IN_PROGRESS);
        testCase.setPriority(Priority.HIGH);
        testCaseRepository.save(testCase);

        Pageable pageable = PageRequest.of(0, 10);
        Page<TestCaseEntity> results = (Page<TestCaseEntity>) testCaseRepository.findAllByStatusAndPriority(
                Status.IN_PROGRESS,
                Priority.HIGH,
                pageable
        );

        assertNotNull(results);
        assertEquals(1, results.getTotalElements());
        assertEquals("Test Case 1", results.getContent().get(0).getTitle());
    }
}
