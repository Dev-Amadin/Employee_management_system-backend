package com.amadin.ems.users;

import java.time.Instant;

import com.amadin.ems.employee.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true)
    @NotNull(message = "Username can not be empty")
    private String username;
    @Size(min = 6, message = "Password must be more than 6 characters")
    private String password;
    @OneToOne
    private Employee employee;
    private String role;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;

}
