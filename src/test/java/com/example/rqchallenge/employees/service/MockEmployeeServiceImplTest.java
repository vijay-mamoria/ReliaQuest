package com.example.rqchallenge.employees.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.rqchallenge.employees.dto.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;
import com.example.rqchallenge.employees.service.MockEmployeeServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockEmployeeServiceImplTest {

    @Autowired
    @Qualifier("mockEmployeeServiceImpl")
    private EmployeeService employeeService;

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = null;
        try {
            employees = employeeService.getAllEmployees();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = employeeService.getEmployeeById("1");
        assertNotNull(employee);
        assertEquals(1, employee.getId());
    }

    @Test
    public void testGetEmployeesByNameSearch() {
        List<Employee> employees = employeeService.getEmployeesByNameSearch("Winters");
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @Test
    public void testGetHighestSalaryOfEmployees() {
        Integer highestSalary = employeeService.getHighestSalaryOfEmployees();
        assertNotNull(highestSalary);
        assertTrue(highestSalary > 0);
    }

    @Test
    public void testGetTopTenHighestEarningEmployeeNames() {
        List<String> employeeNames = employeeService.getTopTenHighestEarningEmployeeNames();
        assertNotNull(employeeNames);
        assertFalse(employeeNames.isEmpty());
    }

    @Test
    public void testCreateEmployee() {
        Map<String, Object> employeeInput = new HashMap<>();
        employeeInput.put("id", 10);
        employeeInput.put("employeeName", "John Doe");
        employeeInput.put("employeeSalary", 50000);
        employeeInput.put("employeeAge", 30);
        Employee employee = employeeService.createEmployee(employeeInput);
        assertNotNull(employee);
        assertEquals(10, employee.getId());
    }

    @Test
    public void testDeleteEmployeeById() {
        String message = employeeService.deleteEmployeeById("1");
        assertNotNull(message);
        assertEquals("successfully! deleted Record", message);
    }
}

