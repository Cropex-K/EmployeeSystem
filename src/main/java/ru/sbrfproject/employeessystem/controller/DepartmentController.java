package ru.sbrfproject.employeessystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sbrfproject.employeessystem.model.Department;
import ru.sbrfproject.employeessystem.service.DepartmentService;

@Controller
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/dep")
    public String departmentList(Model model) {
        model.addAttribute("allDep", departmentService.allDepartments());
        model.addAttribute("department", new Department());
        return "dep_page";
    }

    @PostMapping("/findByIdDep")
    public Department findById(@RequestParam long id) {
        return departmentService.findById(id);
    }

    @PostMapping("/findByName")
    public String findByName(@RequestParam String name) {
        departmentService.findByName(name);
        return "find department: " + name;
    }

    @GetMapping("/deleteAllDep")
    public String deleteAll() {
        departmentService.deleteAll();
        return "Delete all department";
    }

}


