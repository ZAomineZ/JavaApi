package com.example.api.controller;

import com.example.api.model.Employee;
import com.example.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public Iterable<Employee> getEmployees()
    {
        return employeeService.getEmployees();
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee)
    {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee)
    {
        Optional<Employee> e = employeeService.getEmployee(id);

        if (e.isPresent()) {
            Employee currentEmployee = e.get();

            String firstName = employee.getFirstName();
            if (firstName != null) {
                currentEmployee.setFirstName(firstName);
            }

            String lastName = employee.getLastName();
            if (lastName != null) {
                currentEmployee.setLastName(lastName);
            }

            String mail = employee.getMail();
            if (mail != null) {
                currentEmployee.setMail(mail);
            }

            String password = employee.getPassword();
            if (password != null) {
                currentEmployee.setPassword(password);
            }

            employeeService.saveEmployee(currentEmployee);

            return currentEmployee;
        } else {
            return null;
        }
    }

    @GetMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable("id") final Long id)
    {
        employeeService.deleteEmployee(id);
    }
}
