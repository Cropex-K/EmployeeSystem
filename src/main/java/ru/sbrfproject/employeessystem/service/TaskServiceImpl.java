package ru.sbrfproject.employeessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ru.sbrfproject.employeessystem.model.Task;
import ru.sbrfproject.employeessystem.repository.DepartmentRepository;
import ru.sbrfproject.employeessystem.repository.TaskRepository;

import javax.transaction.Transactional;


@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public TaskServiceImpl(TaskRepository taskRepository, DepartmentRepository departmentRepository) {
        this.taskRepository = taskRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public long addEmp(Task task, Long departmentId) {
        int depTo= departmentRepository.findById(departmentId).get().getHierarchy();
        int depFrom = departmentRepository.findByName(task.getNameDepFrom()).getHierarchy();
        if (depFrom <= depTo) {
            departmentRepository.findById(departmentId).map(department -> {
                task.setDepartment(department);
                return taskRepository.save(task);
            }).orElseThrow(() -> new ResourceNotFoundException("DepartmentID " + departmentId + " not found"));
            return task.getId();
        } else{
            return 0;
        }
    }

    @Override
    public Page<Task> findAllByDepId(Long depId, Pageable pageable) {
        return taskRepository.findByDepartmentId(depId,pageable);
    }

    @Override
    public Iterable<Task> all() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void doneById(long id) {
        taskRepository.setFixedTaskFor(id);
    }

    @Override
    public void done(Task task) {
        taskRepository.getById(task.getId()).setCompleting(true);
    }

}
