package com.Zomind.assignment.controller;

import com.Zomind.assignment.Service.TestCaseService;
import com.Zomind.assignment.dtos.TestCaseRequestDTO;
import com.Zomind.assignment.dtos.TestCaseResponseDTO;
import com.Zomind.assignment.entities.enums.Priority;
import com.Zomind.assignment.entities.enums.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/testcases")
@RequiredArgsConstructor
public class TestCaseController {

    private final TestCaseService testCaseService;

    @GetMapping("/all")
    public ResponseEntity<List<TestCaseResponseDTO>> getAllTestCasesList() {
        List<TestCaseResponseDTO> testCases = testCaseService.getAllTestCases();
        return ResponseEntity.ok(testCases);
    }


    @GetMapping()
    public ResponseEntity<Page<TestCaseResponseDTO>> getTestCases(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(testCaseService.getTestCases(status, priority, pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<TestCaseResponseDTO> getTestCaseByID(@PathVariable Long id) {
        TestCaseResponseDTO testCase = testCaseService.findTestCaseById(id);
        if (testCase != null) {
            return ResponseEntity.ok(testCase);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TestCaseResponseDTO> createNewTestCase(@RequestBody @Valid TestCaseRequestDTO testCaseRequestDTO) {
        TestCaseResponseDTO createdTestCase = testCaseService.createNewTestCase(testCaseRequestDTO);
        return new ResponseEntity<>(createdTestCase, HttpStatus.CREATED);// HTTP 201 (Created)
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestCaseResponseDTO> updateExistingTestCase(
            @PathVariable Long id,
            @RequestBody @Valid TestCaseRequestDTO testCaseRequestDTO) {
        TestCaseResponseDTO updatedTestCase = testCaseService.updateTestCaseById(id, testCaseRequestDTO);
        return ResponseEntity.ok(updatedTestCase);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestCaseById(@PathVariable Long id) {
        boolean deleted = testCaseService.deleteTestCaseById(id);
        if (deleted) {
            return ResponseEntity.ok("TestCase Deleted Succesfully "); // HTTP 204 (No Content)
        }
        return ResponseEntity.notFound().build();
    }
}
