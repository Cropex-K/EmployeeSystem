package ru.sbrfproject.employeessystem.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sbrfproject.employeessystem.model.Task;
import ru.sbrfproject.employeessystem.service.TaskService;

@Controller
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/addTask")
    public String createTask(@ModelAttribute Task task, long departmentId) {
        System.out.println("task is : " + task);
        long id = taskService.addEmp(task, departmentId);
        if (id != 0){
            return "redirect:/task";
        }
        else {
            return String.format("Sorry, your department can't set task for id %d department +++id %d", departmentId, id);
        }
    }

    @GetMapping("/allTaskByDepId/{DepId}")
    public Page<Task> getAllTaskByDep(@PathVariable (value = "DepId") Long depId, Pageable pageable) {
        return taskService.findAllByDepId(depId, pageable);
    }

    @GetMapping("/task")
    public String taskList(Model model) {
        model.addAttribute("allTasks", taskService.all());
        model.addAttribute("task", new Task());
        return "task_page";
    }

    @PostMapping("/findByIdTask")
    public Task findByIdTask(@RequestParam long id){
        return taskService.findById(id);
    }

    @PostMapping("/doneById")
    public String doneByIdTask(@RequestParam long id){
        if (!taskService.findById(id).equals(null)) {
            taskService.doneById(id);
            return String.format("Task successfully done id: %d", id);
        }
        else {
            return "Sorry, task with this id doesn't exists";
        }
    }


    @PostMapping("/doneTask")
    public String  doneTask(@RequestParam(required = true, defaultValue = "" ) Long id,
                             @RequestParam(required = true, defaultValue = "" ) String action,
                             Model model) {
        if (action.equals("done")){
//            if (!taskService.findById(id).equals(null)) {
                taskService.doneById(id);
            }
//            else {
//                return "Sorry, task with this id doesn't exists";
//            }
//        }
        return "redirect:/task";
    }

}
