package com.amadin.ems.employee;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.amadin.ems.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        employee.setCreatedAt(Instant.now());
        employee.setUpdatedAt(Instant.now());
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with give id: " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public Page<EmployeeDto> getAllEmployees(int size, int page, String sortField, String sortDirection) {

        Pageable pageable = null;

        if (sortDirection.equalsIgnoreCase("ASC")) {
            pageable = PageRequest.of(page, size, Direction.ASC, sortField);
        } else {
            pageable = PageRequest.of(page, size, Direction.DESC, sortField);
        }

        Page<Employee> employees = employeeRepository.findAll(pageable);

        return employees.map(employee -> EmployeeMapper.mapToEmployeeDto(employee));
    }

    @Override
    public EmployeeDto updateEmployee(String employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with give id: " + employeeId));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setUpdatedAt(Instant.now());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee does not exist with give id: " + employeeId));

        employeeRepository.deleteById(employeeId);

    }

}
