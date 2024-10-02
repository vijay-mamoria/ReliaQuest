package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.assembler.EmployeeAssembler;
import com.example.rqchallenge.employees.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private static final String THIS_COMPONENT_NAME = EmployeeServiceImpl.class.getName();

    private static final String baseUrl =  "https://dummy.restapiexample.com";

    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Employee> getAllEmployees() {
        LOGGER.info("Entered getAllEmployees");

        List<Employee> employeeList = new ArrayList<>();
        String url = baseUrl + "/api/v1/employees";
        ResponseEntity<EmployeeListResponseDTO> responseDTO = restTemplate.exchange(url, HttpMethod.GET, null, EmployeeListResponseDTO.class);
        if(responseDTO.getStatusCode().is2xxSuccessful() && responseDTO.getBody()!=null && responseDTO.getBody().getStatus()!=null &&
                responseDTO.getBody().getStatus().equals("success")){
            employeeList = responseDTO.getBody().getData();
        }

        LOGGER.info("Exited getAllEmployees");
        return employeeList;
    }

    /**
     * Case-Insensitive search is performed.
     * @param searchString
     * @return
     */
    @Override
    public List<Employee> getEmployeesByNameSearch(String searchString) {

        LOGGER.info("Entered getEmployeesByNameSearch");
        List<Employee> employeeList = new ArrayList<>();
        List<Employee> origEmployeeList = getAllEmployees();
        if(origEmployeeList!=null && !origEmployeeList.isEmpty()) {
            employeeList = origEmployeeList.stream().filter(e -> e.getEmployeeName().toLowerCase().contains(searchString.toLowerCase())).collect(Collectors.toList());
        }
        LOGGER.info("Exited getEmployeesByNameSearch");
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(String id) {
        LOGGER.info("Entered getEmployeeById");

        Employee employee = new Employee();
        String url = baseUrl + "/api/v1/employee/" + id;
        ResponseEntity<EmployeeResponseDTO> responseDTO = restTemplate.exchange(url, HttpMethod.GET, null, EmployeeResponseDTO.class);
        if(responseDTO.getStatusCode().is2xxSuccessful() && responseDTO.getBody()!=null && responseDTO.getBody().getStatus()!=null &&
                responseDTO.getBody().getStatus().equals("success")){
            employee = responseDTO.getBody().getData();
        }

        LOGGER.info("Exited getEmployeeById");
        return employee;
    }

    @Override
    public Integer getHighestSalaryOfEmployees() {
        LOGGER.info("Entered getHighestSalaryOfEmployees");

        Integer highestSalary = 0;
        List<Employee> origEmployeeList = getAllEmployees();
        if(origEmployeeList!=null && !origEmployeeList.isEmpty()) {
            highestSalary = origEmployeeList.stream().mapToInt(Employee::getEmployeeSalary).max().orElse(0);
        }

        LOGGER.info("Exited getHighestSalaryOfEmployees");
        return highestSalary;
    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() {
        LOGGER.info("Entered getTopTenHighestEarningEmployeeNames");

        List<String> topTenHighestEarningEmployeeNamesList = new ArrayList<>();
        List<Employee> origEmployeeList = getAllEmployees();
        if(origEmployeeList!=null && !origEmployeeList.isEmpty()) {
            topTenHighestEarningEmployeeNamesList = origEmployeeList.stream().sorted(Comparator.comparingInt(Employee::getEmployeeSalary).reversed()).limit(10).map(Employee::getEmployeeName).collect(Collectors.toList());
        }

        LOGGER.info("Exited getTopTenHighestEarningEmployeeNames");
        return topTenHighestEarningEmployeeNamesList;
    }

    @Override
    public Employee createEmployee(Map<String, Object> employeeInput) {
        LOGGER.info("Entered createEmployee");

        CreateEmployee createEmployee = new CreateEmployee();
        String url = baseUrl + "/api/v1/create";
        ResponseEntity<CreateEmployeeResponseDTO> responseDTO = restTemplate.exchange(url, HttpMethod.POST, null, CreateEmployeeResponseDTO.class);
        if(responseDTO.getStatusCode().is2xxSuccessful() && responseDTO.getBody()!=null && responseDTO.getBody().getStatus()!=null &&
                responseDTO.getBody().getStatus().equals("success")){
            createEmployee = responseDTO.getBody().getData();
        }

        LOGGER.info("Exited createEmployee");
        return EmployeeAssembler.convertToEmployee(createEmployee);
    }

    @Override
    public String deleteEmployeeById(String id) {
        LOGGER.info("Entered deleteEmployeeById");

        String message = "";
        String url = baseUrl + "/api/v1/delete/" + id;
        ResponseEntity<EmployeeResponseDTO> responseDTO = restTemplate.exchange(url, HttpMethod.DELETE, null, EmployeeResponseDTO.class);
        if(responseDTO.getStatusCode().is2xxSuccessful() && responseDTO.getBody()!=null && responseDTO.getBody().getStatus()!=null &&
                responseDTO.getBody().getStatus().equals("success")){
            message = responseDTO.getBody().getMessage();
        }

        LOGGER.info("Exited deleteEmployeeById");
        return message;
    }
}
