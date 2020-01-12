package services.implement;

import dao.EmployeeDao;
import employees.exceptions.CEOAlreadyExists;
import employees.exceptions.IdNotFoundException;
import employees.exceptions.WrongDateException;
import employees.models.Employee;
import employees.models.Titles;
import services.EmployeeService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public class EmployeeServiceImplement implements EmployeeService {
    private EmployeeDao dao;

    private EmployeeServiceImplement(EmployeeDao dao){
        this.dao = dao;
    }

    public Collection<Employee> getAllEmployee() {
        return dao.readAllEmployee();
    }

    public Employee getEmployee(UUID id) throws IdNotFoundException {
        return dao.readEmployee(id);
    }

    public void addEmployee(Employee employee) throws WrongDateException, IllegalArgumentException , CEOAlreadyExists {
        if(employee.getHiredate().isAfter(LocalDate.now().plusYears(1)))
            throw new WrongDateException();
        if(employee.getBirthdate().isAfter(LocalDate.now().plusYears(1)))
            throw new WrongDateException();
        if(employee.getTitle().equals(Titles.CEO))
            throw new CEOAlreadyExists(Titles.CEO);

        else dao.addEmployee(employee);
    }

    public void deleteEmployee(Employee employee) throws IdNotFoundException {
        dao.deleteEmployee(employee);
    }

    public void updateEmployee(UUID id, Employee employee) throws IdNotFoundException, WrongDateException {
        if(employee.getHiredate().isAfter(LocalDate.now().plusYears(1))){
            throw new WrongDateException();
        }
        else if(employee.getBirthdate().isAfter(LocalDate.now().plusYears(1))){
            throw new WrongDateException();
        }
        else dao.updateEmployee(id, employee);
    }

    public void deleteEmployee(UUID id) throws IdNotFoundException {
        Employee ToBeDeleted = getEmployee(id);
        dao.deleteEmployee(ToBeDeleted);
    }
}
