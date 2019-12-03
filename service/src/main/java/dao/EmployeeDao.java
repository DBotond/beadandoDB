package dao;

import employees.exceptions.IdNotFoundException;
import employees.models.Employee;

import java.util.Collection;
import java.util.UUID;

public interface EmployeeDao {
    void addEmployee(Employee employee);
    Collection<Employee> readAllEmployee();
    Employee readEmployee(UUID id) throws IdNotFoundException;
    void updateEmployee(Employee employee) throws IdNotFoundException;
    void deleteEmployee(Employee employee) throws IdNotFoundException;
}
