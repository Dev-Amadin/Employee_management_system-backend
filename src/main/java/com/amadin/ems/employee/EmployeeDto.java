package com.amadin.ems.employee;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String managerId;
    private String managerName;
    private String department;
    private Instant createdAt;
    private Instant updatedAt;

}
