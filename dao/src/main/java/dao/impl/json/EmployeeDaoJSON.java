package dao.impl.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.EmployeeDao;
import employees.exceptions.IdNotFoundException;
import employees.models.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class EmployeeDaoJSON implements EmployeeDao {
    File jsonFile;
    ObjectMapper mapper;

    public EmployeeDaoJSON(File jsonFile) {
        this.jsonFile = jsonFile;
        if (!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public EmployeeDaoJSON(String jsonFilePath){
        this.jsonFile = new File(jsonFilePath);
        if (!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFilePath);
                writer.write("[]");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        }

    public void addEmployee(Employee employee) {
        Collection<Employee> employees = readAllEmployee();
        employees.add(employee);

        try{
            mapper.writeValue(jsonFile,employees);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Employee> readAllEmployee() {
        Collection<Employee> employees = new ArrayList<Employee>();
        try{
            employees = mapper.readValue(jsonFile, new TypeReference<Collection<Employee>>() {
            });
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee readEmployee(UUID id) throws IdNotFoundException {
        Collection<Employee> employees = readAllEmployee();
        for (Employee e: employees){
            if(e.getId().toString().equalsIgnoreCase((id.toString()))){
                return e;
            }
        }
        throw new IdNotFoundException();


    }

    public void updateEmployee(UUID id, Employee employee) throws IdNotFoundException {
        Employee delete = readEmployee(id);
        deleteEmployee(delete);
        addEmployee(employee);
    }

    public void deleteEmployee(Employee employee) throws IdNotFoundException {
        Collection<Employee> employees = readAllEmployee();
        Collection<Employee> result = new ArrayList<Employee>();
        try {
            Employee delete = readEmployee(employee.getId());

            for(Employee e : employees){
                if(!(e.getId().equals(delete.getId()))){
                    result.add(e);
                }
            }
            mapper.writeValue(jsonFile, result);
        } catch (IdNotFoundException idNotFoundException) {
            throw idNotFoundException;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
