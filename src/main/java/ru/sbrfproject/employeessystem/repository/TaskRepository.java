package ru.sbrfproject.employeessystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sbrfproject.employeessystem.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Page<Task> findByDepartmentId(Long DepartmentId, Pageable pageable);

    @Modifying
    @Query("update Task t set t.completing = true  where t.id = ?1")
    void setFixedTaskFor(long id);

}
