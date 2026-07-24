package com.amadin.ems.employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getManager() != null ? employee.getManager().getId() : "",
                employee.getManager() != null
                        ? employee.getManager().getFirstName() + " " + employee.getManager().getLastName()
                        : "",
                employee.getDepartment(),
                employee.getCreatedAt(),
                employee.getUpdatedAt());
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartment(),
                null,
                null,
                employeeDto.getCreatedAt(),
                employeeDto.getUpdatedAt());
    }
}
