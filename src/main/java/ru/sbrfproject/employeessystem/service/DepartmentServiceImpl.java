package ru.sbrfproject.employeessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrfproject.employeessystem.model.Department;
import ru.sbrfproject.employeessystem.repository.DepartmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Override
    public long addDep(Department department) {
        departmentRepository.save(department);
        return department.getId();
    }

    @Override
    public Iterable<Department> allDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public void delete(Department department) {
        departmentRepository.delete(department);
    }

    @Override
    public void deleteAll() {
        departmentRepository.deleteAll();
    }

    @Override
    public boolean existDep(Department  department) {
        List<Department> dep = (List<Department>) departmentRepository.findAll();
        for (int i = 0; i < dep.size(); i++) {
            if (dep.get(i).getName().equals(department.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Department> departmentgtList(Long idMin) {
        return em.createQuery("SELECT u FROM Department u WHERE u.id > :paramId", Department.class)
                .setParameter("paramId", idMin).getResultList();
    }

    @Override
    public void deleteById(long id){
        departmentRepository.deleteById(id);
    }

}
