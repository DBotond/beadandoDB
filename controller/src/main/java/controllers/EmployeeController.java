package controllers;


import employees.exceptions.IdNotFoundException;
import employees.exceptions.WrongDateException;
import employees.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.EmployeeService;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class EmployeeController {
    private EmployeeService service;

    public EmployeeController(@Autowired EmployeeService service){
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

    @RequestMapping(value = "/employee/{lastname}")
    @ResponseBody
    public Collection<Employee> getEmployeeByLastName(@PathVariable String lastname){
        Collection<Employee> employees = service.getAllEmployee();
        Collection<Employee> result = new ArrayList<Employee>();

        for(Employee e: employees){
            if(e.getLastname().equalsIgnoreCase(lastname)){
                result.add(e);
            }
        }

        return result;
    }

    
}
