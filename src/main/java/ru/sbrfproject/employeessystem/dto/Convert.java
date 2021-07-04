package ru.sbrfproject.employeessystem.dto;

import org.springframework.stereotype.Component;
import ru.sbrfproject.employeessystem.model.Employee;

@Component
public class Convert {

    public Employee fromEmployeesDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setSecondName(employeeDto.getSecondName());
        employee.setJobPost(employeeDto.getJobPost());
        employee.setPhone(employeeDto.getPhone());
        employee.setEmail(employeeDto.getEmail());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setDepartment(employeeDto.getDepartment());
        return employee;
    }

    public EmployeeDto fromEmployeeToEmployeesDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setSecondName(employee.getSecondName());
        employeeDto.setJobPost(employee.getJobPost());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setDepartment(employee.getDepartment());
        return employeeDto;
    }
}
