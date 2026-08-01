package com.amadin.ems.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepository extends JpaRepository<Employee, String>, JpaSpecificationExecutor<Employee> {

    Page<Employee> findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String firstName, String LastName, Pageable pageable);

}
