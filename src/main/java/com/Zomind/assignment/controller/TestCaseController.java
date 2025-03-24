package com.Zomind.assignment.controller;

import com.Zomind.assignment.Service.TestCaseService;
import com.Zomind.assignment.dtos.TestCaseRequestDTO;
import com.Zomind.assignment.dtos.TestCaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/testcases")
@RequiredArgsConstructor
public class TestCaseController {

    private final TestCaseService testCaseService;

    @GetMapping
    public ResponseEntity<List<TestCaseResponseDTO>> getAllTestCases() {
        return ResponseEntity.ok(testCaseService.getAllTestCases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCaseResponseDTO> getTestCaseById(@PathVariable String id) {
        return ResponseEntity.ok(testCaseService.findTestCaseById(id));
    }

    @PostMapping
    public ResponseEntity<TestCaseResponseDTO> createTestCase(@RequestBody TestCaseRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(testCaseService.createNewTestCase(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestCaseResponseDTO> updateTestCase(
            @PathVariable String id,
            @RequestBody TestCaseRequestDTO requestDTO) {
        return ResponseEntity.ok(testCaseService.updateTestCaseById(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable String id) {
        testCaseService.deleteTestCaseById(id);
        return ResponseEntity.noContent().build();
    }
}
