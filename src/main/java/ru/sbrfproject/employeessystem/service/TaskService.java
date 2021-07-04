package ru.sbrfproject.employeessystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sbrfproject.employeessystem.model.Task;

public interface TaskService {

    public long addEmp(Task task, Long departmentId);

    public Page<Task> findAllByDepId(Long depId, Pageable pageable);

    public Iterable<Task> all();

    public Task findById(long id);

    public void doneById(long id);

    public void done(Task task);


}
