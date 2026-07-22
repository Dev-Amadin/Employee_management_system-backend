package com.amadin.ems.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Page<Employee> findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String firstName, String LastName, Pageable pageable);

}
