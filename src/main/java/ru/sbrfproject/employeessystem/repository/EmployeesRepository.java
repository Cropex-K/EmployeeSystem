package ru.sbrfproject.employeessystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrfproject.employeessystem.model.Department;
import ru.sbrfproject.employeessystem.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
    public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment (Department department);

    Page<Employee> findByDepartmentId(Long DepartmentId, Pageable pageable);
    Optional<Employee> findByIdAndDepartmentId(Long id, Long departmentId);

    Employee getByEmail (String email);

}
