package services;

import employees.exceptions.IdNotFoundException;
import employees.exceptions.WrongDateException;
import employees.models.Employee;

import java.util.Collection;
import java.util.UUID;

public interface EmployeeService {
    Collection<Employee> getAllEmployee();
    Employee getEmployee(UUID id) throws IdNotFoundException;
    void addEmployee(Employee employee) throws WrongDateException, IllegalArgumentException;
    void deleteEmployee(Employee employee) throws IdNotFoundException, WrongDateException;
    void updateEmployee(UUID id, Employee employee) throws IdNotFoundException, IllegalArgumentException, WrongDateException;
    void deleteEmployee(UUID id) throws IdNotFoundException;
}
