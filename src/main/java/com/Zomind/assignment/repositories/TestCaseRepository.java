package com.Zomind.assignment.repositories;

import com.Zomind.assignment.entities.TestCaseEntity;
import com.Zomind.assignment.entities.enums.Priority;
import com.Zomind.assignment.entities.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestCaseRepository extends JpaRepository<TestCaseEntity, Long> {

    Page<TestCaseEntity> findAllByStatusAndPriority(Status status, Priority priority, Pageable pageable);

}