package com.amadin.ems.users;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;
    @NotNull (message = "Username is required")
    private String username;
    @Size(min = 6, message = "Password must be more than 6 characters")
    private String password;
    private String employeeId;
    private String employeeName;
    private String role;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
