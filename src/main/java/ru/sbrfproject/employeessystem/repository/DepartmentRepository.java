package ru.sbrfproject.employeessystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrfproject.employeessystem.model.Department;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department,Long> {

    Department findByName (String name);

}
