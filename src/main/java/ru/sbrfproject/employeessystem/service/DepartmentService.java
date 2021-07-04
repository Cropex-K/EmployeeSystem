package ru.sbrfproject.employeessystem.service;

import ru.sbrfproject.employeessystem.model.Department;

import java.util.List;

public interface DepartmentService {

    public Iterable<Department> allDepartments();

    public Department findById(long id);

    public Department findByName(String name);

    public void delete(Department department);

    public void deleteById(long id);

    public void deleteAll();

    public long addDep(Department department);

    public boolean existDep(Department department);

    public List<Department> departmentgtList(Long idMin);

}
