package com.Zomind.assignment.repositories;

import com.Zomind.assignment.entities.TestCaseEntity;
import com.Zomind.assignment.entities.enums.Priority;
import com.Zomind.assignment.entities.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TestCaseRepositoryTest {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Test
    void findAllByStatusAndPriority_ShouldReturnCorrectResults() {
        TestCaseEntity testCase = new TestCaseEntity();
        testCase.setTitle("Test Case 1");
        testCase.setStatus(Status.IN_PROGRESS);
        testCase.setPriority(Priority.HIGH);
        testCaseRepository.save(testCase);

        Page<TestCaseEntity> results = testCaseRepository.findAllByStatusAndPriority(Status.IN_PROGRESS, Priority.HIGH, PageRequest.of(0, 10));

        assertNotNull(results);
        assertEquals(1, results.getTotalElements());
    }
}
