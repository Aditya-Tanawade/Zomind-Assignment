package com.Zomind.assignment.dtos;


import com.Zomind.assignment.annotations.EnumConstraint;
import com.Zomind.assignment.entities.enums.Priority;
import com.Zomind.assignment.entities.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 3,max = 20, message = "Number Of Characters in the Title Should be in the Range of : [3,10]")
    private String title;

    private String description;

    @EnumConstraint(enumClass = com.Zomind.assignment.entities.enums.Status.class,
            message = "Invalid status. Accepted values: [PENDING, IN_PROGRESS, PASSED, FAILED]")
    private Status status;


    @EnumConstraint(enumClass = com.Zomind.assignment.entities.enums.Priority.class,
            message = "Invalid priority. Accepted values: [LOW, MEDIUM, HIGH]")
    private Priority priority;

}

