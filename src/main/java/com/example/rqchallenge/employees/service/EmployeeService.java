package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.dto.Employee;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EmployeeService {


    List<Employee> getAllEmployees() throws IOException;


    List<Employee> getEmployeesByNameSearch(@PathVariable String searchString);


    Employee getEmployeeById(@PathVariable String id);


    Integer getHighestSalaryOfEmployees();


    List<String> getTopTenHighestEarningEmployeeNames();


    Employee createEmployee(@RequestBody Map<String, Object> employeeInput);


    String deleteEmployeeById(@PathVariable String id);

}
