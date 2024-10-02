package com.example.rqchallenge.employees.assembler;

import com.example.rqchallenge.employees.dto.CreateEmployee;
import com.example.rqchallenge.employees.dto.Employee;

public class EmployeeAssembler {

    public static Employee convertToEmployee(CreateEmployee createEmployee){
        Employee employee = new Employee();
        if(createEmployee!=null) {
            employee.setId(createEmployee.getId());
            employee.setEmployeeName(createEmployee.getName());
            employee.setEmployeeSalary(createEmployee.getSalary());
            employee.setEmployeeAge(createEmployee.getAge());
        }
        return employee;
    }
}
