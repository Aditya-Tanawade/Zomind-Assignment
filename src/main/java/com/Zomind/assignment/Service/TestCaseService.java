package com.Zomind.assignment.Service;

import com.Zomind.assignment.dtos.TestCaseRequestDTO;
import com.Zomind.assignment.dtos.TestCaseResponseDTO;
import com.Zomind.assignment.entities.TestCaseEntity;
import com.Zomind.assignment.entities.enums.Priority;
import com.Zomind.assignment.entities.enums.Status;
import com.Zomind.assignment.exceptions.ResourceNotFoundException;
import com.Zomind.assignment.repositories.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepo;
    private final ModelMapper modelMapper;

    public List<TestCaseResponseDTO> getAllTestCases() {
        return testCaseRepo.findAll()
                .stream()
                .map(testCaseEntity -> modelMapper.map(testCaseEntity, TestCaseResponseDTO.class))
                .collect(Collectors.toList());
    }



    public TestCaseResponseDTO findTestCaseById(Long id) {
        TestCaseEntity testCase = testCaseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test case not found for id: " + id));
        return modelMapper.map(testCase, TestCaseResponseDTO.class);
    }

    public TestCaseResponseDTO createNewTestCase(TestCaseRequestDTO testCaseRequestDTO) {
        TestCaseEntity testCase = modelMapper.map(testCaseRequestDTO, TestCaseEntity.class);
        TestCaseEntity savedEntity = testCaseRepo.save(testCase);
        return modelMapper.map(savedEntity, TestCaseResponseDTO.class);
    }

    public TestCaseResponseDTO updateTestCaseById(Long id, TestCaseRequestDTO testCaseRequestDTO) {
        TestCaseEntity existingEntity = testCaseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test case not found for id: " + id));

        modelMapper.map(testCaseRequestDTO, existingEntity);
        existingEntity.setUpdatedAt(LocalDateTime.now());
        TestCaseEntity updatedEntity = testCaseRepo.save(existingEntity);
        return modelMapper.map(updatedEntity, TestCaseResponseDTO.class);
    }


    public boolean deleteTestCaseById(Long id) {
        if (!testCaseRepo.existsById(id)) {
            throw new ResourceNotFoundException("Test case not found for id: " + id);
        }
        testCaseRepo.deleteById(id);
        return true;
    }


    public Page<TestCaseResponseDTO> getTestCases(Status status, Priority priority, Pageable pageable) {
        return testCaseRepo.findAllByStatusAndPriority(status, priority, pageable)
                .map(entity -> modelMapper.map(entity, TestCaseResponseDTO.class));
    }
}
