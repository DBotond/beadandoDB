package controllers;


import employees.exceptions.CEOAlreadyExists;
import employees.exceptions.IdNotFoundException;
import employees.exceptions.WrongDateException;
import employees.models.Employee;
import employees.models.Titles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import services.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

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
    public Employee addNewEmployee(@RequestBody Employee employee) throws WrongDateException, IdNotFoundException, CEOAlreadyExists {
        service.addEmployee(employee);
        return service.getEmployee(employee.getId());
    }

    @RequestMapping(value = "/employee/{lastname}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/employeeid/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployeeById(@PathVariable UUID id) throws IdNotFoundException{
        return service.getEmployee(id);
    }

    @RequestMapping(value = "/employee/{date}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Employee> getEmployeesByDate(@PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date){
        Collection<Employee> employees = service.getAllEmployee();
        Collection<Employee> result = new ArrayList<>();
        for (Employee e:
                employees) {
            if(e.getHiredate().isEqual(date)){
                result.add(e);
            }
        }
        return result;
    }

    @RequestMapping(value = "/employeeid/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteemployee(@PathVariable UUID id) throws IdNotFoundException {
        service.deleteEmployee(id);
        return "Delete successful!";
    }

    @RequestMapping(value = "/employeeid/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Employee updateEmployee(@PathVariable UUID id, @RequestBody Employee employee) throws WrongDateException, IdNotFoundException {
        employee.setId(id);
        service.updateEmployee(id,employee);
        return employee;
    }

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseBody
    public String handleIdNotFoundException(Exception ex){
        return "UUID not found: " + ex.getMessage();
    }

    @ExceptionHandler(WrongDateException.class)
    @ResponseBody
    public String handleWrongDateException(Exception ex){
        return "Bad date:" + ex.getMessage();
    }


}
