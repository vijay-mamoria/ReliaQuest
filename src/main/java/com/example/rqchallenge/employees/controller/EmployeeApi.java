package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.dto.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EmployeeApi {

    ResponseEntity<List<Employee>> getAllEmployees() throws IOException;


    ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString);


    ResponseEntity<Employee> getEmployeeById(@PathVariable String id);


    ResponseEntity<Integer> getHighestSalaryOfEmployees();


    ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames();


    ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput);


    ResponseEntity<String> deleteEmployeeById(@PathVariable String id);

}
