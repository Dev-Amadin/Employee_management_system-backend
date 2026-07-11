package com.amadin.ems.users;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;
    private String username;
    private String password;
    private String employeeId;
    private String employeeName;
    private String role;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
