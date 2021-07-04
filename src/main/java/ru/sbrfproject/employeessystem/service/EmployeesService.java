package ru.sbrfproject.employeessystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.sbrfproject.employeessystem.model.Employee;
import ru.sbrfproject.employeessystem.dto.EmployeeDto;

import java.util.List;

public interface EmployeesService extends UserDetailsService {

    public long addEmp(EmployeeDto employeeDto, Long departmentId);

    public Page<Employee> findAllByDepId(Long depId, Pageable pageable);

    public Iterable<Employee> allEmp();

    public Employee findById(long id);

    public boolean deleteById(long id);

    public void delete(EmployeeDto employeeDto);

    public void deleteAll();

    public boolean existEmp(EmployeeDto  employeeDto);

    public UserDetails loadUserByUsername(String id);

    public List<Employee> employeegtList(Long idMin);

}
