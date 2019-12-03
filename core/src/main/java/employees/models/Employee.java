package employees.models;

import java.time.LocalDate;
import java.util.UUID;

public class Employee {
    private UUID id;
    private String firstname;
    private String lastname;
    private Gender gender;
    private Titles title;
    private LocalDate birthdate;
    private LocalDate hiredate;
    private int salary;

    public Employee() {
        this.id = UUID.randomUUID();
    }

    public Employee(String firstname, String lastname, Gender gender, Titles title, LocalDate birthdate, LocalDate hiredate, int salary) {
        this();
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.title = title;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
        this.salary = salary;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Titles getTitle() {
        return title;
    }

    public void setTitle(Titles title) {
        this.title = title;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
