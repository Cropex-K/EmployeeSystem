package ru.sbrfproject.employeessystem.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sbrfproject.employeessystem.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbrfproject.employeessystem.dto.EmployeeDto;
import ru.sbrfproject.employeessystem.service.EmployeesService;

@Controller
public class EmployeesController {

    private EmployeesService employeesService;

    @Autowired
    public EmployeesController(EmployeesService employeesService){
        this.employeesService = employeesService;
    }

    @GetMapping("/emp")
    public String employeeList(Model model) {
        model.addAttribute("allEmp", employeesService.allEmp());
        model.addAttribute("employee", new Employee());
        return "emp_page";
    }

    @GetMapping(path="/allByDepId/{DepId}")
    public  Page<Employee> getAllUsersByDep(@PathVariable (value = "DepId") Long depId, Pageable pageable) {
        return employeesService.findAllByDepId(depId, pageable);
    }


    @PostMapping("/findById")
    public Employee findById(@RequestParam long id){
        return employeesService.findById(id);
    }


    @PostMapping("/delete")
    public String delete(@RequestBody EmployeeDto employeeDto){
        if (employeesService.existEmp(employeeDto)){
            employeesService.delete(employeeDto);
            return "Delete employee: " + employeeDto;
        } else {
            return "Sorry, that employee doesn't exists";
        }
    }

    @GetMapping("/deleteAll")
    public String deleteAll(){
        employeesService.deleteAll();
        return "Delete all employees" ;
    }

    @GetMapping("/emp/gt/{employeeId}")
    public String  gtEmployee (@PathVariable("employeeId") Long employeeId, Model model) {
        model.addAttribute("allEmp", employeesService.employeegtList(employeeId));
        return "emp";
    }
}
