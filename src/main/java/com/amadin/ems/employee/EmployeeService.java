package com.amadin.ems.employee;

import org.springframework.data.domain.Page;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(String employeeId);

    Page<EmployeeDto> getAllEmployees(int size, int page, String sortField, String sortDirection);

    EmployeeDto updateEmployee(String employeeId, EmployeeDto employeeDto);

    void deleteEmployee(String employeeId);

}
