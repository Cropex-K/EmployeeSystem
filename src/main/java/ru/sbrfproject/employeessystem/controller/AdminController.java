package ru.sbrfproject.employeessystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sbrfproject.employeessystem.dto.EmployeeDto;
import ru.sbrfproject.employeessystem.model.Department;
import ru.sbrfproject.employeessystem.model.Employee;
import ru.sbrfproject.employeessystem.service.DepartmentService;
import ru.sbrfproject.employeessystem.service.EmployeesService;

@Controller
public class AdminController {
    private EmployeesService employeesService;
    private DepartmentService departmentService;

    @Autowired
    public AdminController(EmployeesService employeesService, DepartmentService departmentService){
        this.employeesService = employeesService;
        this.departmentService=departmentService;
    }

    @GetMapping("/admin")
    public String employeeList(Model model) {
        model.addAttribute("allEmp", employeesService.allEmp());
        model.addAttribute("employee", new Employee());
        model.addAttribute("allDep", departmentService.allDepartments());
        model.addAttribute("department", new Department());
        return "admin_page";
    }

    @PostMapping("/admin/addEmp")
    public String createEmployee(@ModelAttribute EmployeeDto employeeDto, long departmentId) {
        System.out.println("employee is : " + employeeDto);
        if (employeesService.existEmp(employeeDto)){
            return "Employee with this email already exists";
        }
        else {
            employeesService.addEmp(employeeDto, departmentId);
            return "redirect:/admin";
        }
    }

    @PostMapping("/admin/deleteEmp")
    public String  deleteEmp(@RequestParam(required = true, defaultValue = "" ) Long id,
                             @RequestParam(required = true, defaultValue = "" ) String action,
                             Model model) {
        if (action.equals("delete")){
            employeesService.deleteById(id);
        }
        return "redirect:/admin";
    }


    @PostMapping("/admin/addDep")
    public String createDep(@ModelAttribute Department department) {
        System.out.println("department is : " + department);
        departmentService.addDep(department);
        return "redirect:/admin";
    }


    @PostMapping("/admin/deleteDep")
    public String deleteById(@RequestParam(required = true, defaultValue = "" ) Long id,
                             @RequestParam(required = true, defaultValue = "" ) String action,
                             Model model) {
        if (action.equals("delete")) {
            departmentService.deleteById(id);
        }
        return "redirect:/admin";
    }

}

