package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.employees.dto.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/employees")
@RestController
public class EmployeeApiController implements EmployeeApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeApiController.class);

    private final EmployeeService employeeService;

    public EmployeeApiController(@Qualifier("mockEmployeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
        LOGGER.info("Entered getAllEmployees");

        ResponseEntity<List<Employee>> responseEntity = null;
        try {
            responseEntity = ResponseEntity.ok(employeeService.getAllEmployees());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        LOGGER.info("Exited getAllEmployees");
        return responseEntity;
    }

    @GetMapping("/search/{searchString}")
    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString) {
        LOGGER.info("Entered getEmployeesByNameSearch");
        ResponseEntity<List<Employee>> responseEntity = null;
        try {
            responseEntity = ResponseEntity.ok(employeeService.getEmployeesByNameSearch(searchString));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOGGER.info("Exited getEmployeesByNameSearch");
        return responseEntity;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        LOGGER.info("Entered getEmployeeById");
        ResponseEntity<Employee> responseEntity = null;
        try {
            responseEntity = ResponseEntity.ok(employeeService.getEmployeeById(id));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOGGER.info("Exited getEmployeeById");
        return responseEntity;
    }

    @GetMapping("/highestSalary")
    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        LOGGER.info("Entered getHighestSalaryOfEmployees");
        ResponseEntity<Integer> responseEntity = null;
        try {
            responseEntity = ResponseEntity.ok(employeeService.getHighestSalaryOfEmployees());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOGGER.info("Exited getHighestSalaryOfEmployees");
        return responseEntity;
    }

    @GetMapping("/topTenHighestEarningEmployeeNames")
    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        LOGGER.info("Entered topTenHighestEarningEmployeeNames");
        ResponseEntity<List<String>> responseEntity = null;
        try {
            responseEntity = ResponseEntity.ok(employeeService.getTopTenHighestEarningEmployeeNames());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOGGER.info("Exited topTenHighestEarningEmployeeNames");
        return responseEntity;
    }

    @PostMapping()
    @Override
    public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
        LOGGER.info("Entered createEmployee");
        ResponseEntity<Employee> responseEntity = null;
        try {
            responseEntity = ResponseEntity.ok(employeeService.createEmployee(employeeInput));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOGGER.info("Exited createEmployee");
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {
        LOGGER.info("Entered deleteEmployeeById");
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = ResponseEntity.ok(employeeService.deleteEmployeeById(id));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOGGER.info("Exited deleteEmployeeById");
        return responseEntity;
    }
}
