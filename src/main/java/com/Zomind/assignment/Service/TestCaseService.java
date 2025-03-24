package com.Zomind.assignment.Service;

import com.Zomind.assignment.dtos.TestCaseRequestDTO;
import com.Zomind.assignment.dtos.TestCaseResponseDTO;
import com.Zomind.assignment.entities.TestCaseEntity;
import com.Zomind.assignment.exceptions.ResourceNotFoundException;
import com.Zomind.assignment.repositories.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepo;
    private final ModelMapper modelMapper;

    public List<TestCaseResponseDTO> getAllTestCases() {
        return testCaseRepo.findAll()
                .stream()
                .map(testCase -> modelMapper.map(testCase, TestCaseResponseDTO.class))
                .collect(Collectors.toList());
    }

    public TestCaseResponseDTO findTestCaseById(String id) {
        TestCaseEntity testCase = testCaseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test case not found for id: " + id));
        return modelMapper.map(testCase, TestCaseResponseDTO.class);
    }

    public TestCaseResponseDTO createNewTestCase(TestCaseRequestDTO requestDTO) {
        TestCaseEntity testCase = modelMapper.map(requestDTO, TestCaseEntity.class);
        testCase.setCreatedAt(LocalDateTime.now());
        TestCaseEntity savedEntity = testCaseRepo.save(testCase);
        return modelMapper.map(savedEntity, TestCaseResponseDTO.class);
    }

    public TestCaseResponseDTO updateTestCaseById(String id, TestCaseRequestDTO requestDTO) {
        TestCaseEntity testCase = testCaseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test case not found for id: " + id));

        modelMapper.map(requestDTO, testCase);
        testCase.setUpdatedAt(LocalDateTime.now());
        TestCaseEntity updatedEntity = testCaseRepo.save(testCase);
        return modelMapper.map(updatedEntity, TestCaseResponseDTO.class);
    }

    public void deleteTestCaseById(String id) {
        if (!testCaseRepo.existsById(id)) {
            throw new ResourceNotFoundException("Test case not found for id: " + id);
        }
        testCaseRepo.deleteById(id);
    }
}
