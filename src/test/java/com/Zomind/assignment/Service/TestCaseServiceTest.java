
package com.Zomind.assignment.Service;

import com.Zomind.assignment.dtos.TestCaseRequestDTO;
import com.Zomind.assignment.dtos.TestCaseResponseDTO;
import com.Zomind.assignment.entities.TestCaseEntity;
import com.Zomind.assignment.exceptions.ResourceNotFoundException;
import com.Zomind.assignment.repositories.TestCaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestCaseServiceTest {

    @Mock
    private TestCaseRepository testCaseRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TestCaseService testCaseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTestCases_ShouldReturnAllTestCases() {
        TestCaseEntity testCase = new TestCaseEntity();
        testCase.setId("1");
        when(testCaseRepo.findAll()).thenReturn(List.of(testCase));
        when(modelMapper.map(any(TestCaseEntity.class), eq(TestCaseResponseDTO.class)))
                .thenReturn(new TestCaseResponseDTO());

        List<TestCaseResponseDTO> result = testCaseService.getAllTestCases();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(testCaseRepo, times(1)).findAll();
    }

    @Test
    void findTestCaseById_ShouldThrowExceptionIfNotFound() {
        when(testCaseRepo.findById("1")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> testCaseService.findTestCaseById("1"));
    }

    @Test
    void createNewTestCase_ShouldReturnCreatedTestCase() {
        TestCaseRequestDTO requestDTO = new TestCaseRequestDTO();
        TestCaseEntity entity = new TestCaseEntity();
        when(modelMapper.map(requestDTO, TestCaseEntity.class)).thenReturn(entity);
        when(testCaseRepo.save(entity)).thenReturn(entity);
        when(modelMapper.map(entity, TestCaseResponseDTO.class)).thenReturn(new TestCaseResponseDTO());

        TestCaseResponseDTO response = testCaseService.createNewTestCase(requestDTO);

        assertNotNull(response);
        verify(testCaseRepo, times(1)).save(entity);
    }
}

