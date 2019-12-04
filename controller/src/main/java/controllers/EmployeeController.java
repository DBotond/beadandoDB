package controllers;


import employees.exceptions.IdNotFoundException;
import employees.exceptions.WrongDateException;
import employees.models.Employee;
import org.springframework.web.bind.annotation.*;
import services.EmployeeService;

import java.util.Collection;

@RestController
public class EmployeeController {
    private EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @RequestMapping(value ="/count", method = RequestMethod.GET)
    @ResponseBody
    public String showEmployeeCount(){
        return String.valueOf(service.getAllEmployee().size());
    }

    @RequestMapping(value = "/employees",method = RequestMethod.GET)
    @ResponseBody
    public Collection<Employee> getAllEmployee(){
        return service.getAllEmployee();
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    @ResponseBody
    public Employee addNewEmployee(@RequestBody Employee employee) throws WrongDateException, IdNotFoundException {
        service.addEmployee(employee);
        return service.getEmployee(employee.getId());
    }
}
