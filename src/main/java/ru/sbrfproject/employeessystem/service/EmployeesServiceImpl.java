package ru.sbrfproject.employeessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.sbrfproject.employeessystem.model.Department;
import ru.sbrfproject.employeessystem.model.Employee;
import org.springframework.stereotype.Service;
import ru.sbrfproject.employeessystem.dto.Convert;
import ru.sbrfproject.employeessystem.dto.EmployeeDto;
import ru.sbrfproject.employeessystem.repository.DepartmentRepository;
import ru.sbrfproject.employeessystem.repository.EmployeesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService, UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private Convert convert;

    public EmployeesServiceImpl(EmployeesRepository employeesRepository, Convert convert, DepartmentRepository departmentRepository) {
        this.employeesRepository = employeesRepository;
        this.convert = convert;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public long addEmp(EmployeeDto employeeDto, Long departmentId) {
        Employee employee = convert.fromEmployeesDtoToEmployee(employeeDto);
        departmentRepository.findById(departmentId).map(department ->{
            employee.setDepartment(department);
            employee.setRole(getRoleByDep(department));
            employee.setPassword(bCryptPasswordEncoder.encode(employee.getPhone()));
            return employeesRepository.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("DepartmentID " + departmentId + " not found"));
        return employee.getId();
    }

    @Override
    public Page<Employee> findAllByDepId(Long depId, Pageable pageable) {
       return employeesRepository.findByDepartmentId(depId, pageable);
    }

    @Override
    public List<Employee> allEmp() {
        return employeesRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        return employeesRepository.findById(id).get();
    }

    @Override
    public boolean deleteById(long id) {
        if (employeesRepository.findById(id).isPresent()) {
            employeesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void delete(EmployeeDto employeeDto) {
        Employee worker = convert.fromEmployeesDtoToEmployee(employeeDto);
        employeesRepository.delete(worker);
    }

    @Override
    public void deleteAll() {
        employeesRepository.deleteAll();
    }

    @Override
    public boolean existEmp(EmployeeDto  employeeDto) {
        Employee employee = convert.fromEmployeesDtoToEmployee(employeeDto);
        List<Employee> emp = employeesRepository.findAll();
        for (int i = 0; i < emp.size(); i++) {
            try {
                if (emp.get(i).getEmail().equals(employee.getEmail())) {
                    return true;
                }
            } catch (NullPointerException e) {

            }
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeesRepository.getByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee not found");
        }
        return employee;
    }

    @Override
    public List<Employee> employeegtList(Long idMin) {
        return em.createQuery("SELECT u FROM Employee u WHERE u.id > :id", Employee.class)
                .setParameter("id", idMin).getResultList();
    }

    public String getRoleByDep (Department department){
        String role;
        if (department.getHierarchy()==1){
            role="ADMIN";
        }
        else {
            role="USER";
        }
        return  role;
    }

}

