package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.assembler.EmployeeAssembler;
import com.example.rqchallenge.employees.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MockEmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockEmployeeServiceImpl.class);
    private static final String THIS_COMPONENT_NAME = MockEmployeeServiceImpl.class.getName();

    private static final String baseUrl =  "https://dummy.restapiexample.com";

    private final RestTemplate restTemplate;

    @Autowired
    public MockEmployeeServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private static List<Employee> employeeList;

    @PostConstruct
    public void loadEmployeesFromJson() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/mock-employees.json");
        EmployeeListResponseDTO responseDTO = new ObjectMapper().readValue(inputStream, EmployeeListResponseDTO.class);
        employeeList = new ArrayList<>(responseDTO.getData());
    }

    @Override
    public List<Employee> getAllEmployees() {
        LOGGER.info("Entered getAllEmployees");

//        List<Employee> employeeList = new ArrayList<>();
//        String url = baseUrl + "/api/v1/employees";
//        ResponseEntity<EmployeeListResponseDTO> responseDTO = restTemplate.exchange(url, HttpMethod.GET, null, EmployeeListResponseDTO.class);
//        if(responseDTO.getStatusCode().is2xxSuccessful() && responseDTO.getBody()!=null && responseDTO.getBody().getStatus()!=null &&
//                responseDTO.getBody().getStatus().equals("success")){
//            employeeList = responseDTO.getBody().getData();
//        }

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

//        List<Employee> employeeList = new ArrayList<>();
//        List<Employee> origEmployeeList = getAllEmployees();
//        if(origEmployeeList!=null && !origEmployeeList.isEmpty()) {
//            employeeList = origEmployeeList.stream().filter(e -> e.getEmployeeName().equalsIgnoreCase(searchString)).collect(Collectors.toList());
//        }
        List<Employee> filteredEmployeeList = employeeList.stream().filter(e -> e.getEmployeeName().toLowerCase().contains(searchString.toLowerCase())).collect(Collectors.toList());
                LOGGER.info("Exited getEmployeesByNameSearch");
        return filteredEmployeeList;
    }

    @Override
    public Employee getEmployeeById(String id) {
        LOGGER.info("Entered getEmployeeById");

//        Employee employee = new Employee();
//        String url = baseUrl + "/api/v1/employee/" + id;
//        ResponseEntity<EmployeeResponseDTO> responseDTO = restTemplate.exchange(url, HttpMethod.GET, null, EmployeeResponseDTO.class);
//        if(responseDTO.getStatusCode().is2xxSuccessful() && responseDTO.getBody()!=null && responseDTO.getBody().getStatus()!=null &&
//                responseDTO.getBody().getStatus().equals("success")){
//            employee = responseDTO.getBody().getData();
//        }

        LOGGER.info("Exited getEmployeeById");
        return employeeList.stream().filter(e -> e.getId() == Integer.parseInt(id)).findFirst().orElse(new Employee());
    }

    @Override
    public Integer getHighestSalaryOfEmployees() {
        LOGGER.info("Entered getHighestSalaryOfEmployees");

//        Integer highestSalary = 0;
//        List<Employee> origEmployeeList = getAllEmployees();
//        if(origEmployeeList!=null && !origEmployeeList.isEmpty()) {
//            highestSalary = origEmployeeList.stream().mapToInt(Employee::getEmployeeSalary).max().orElse(0);
//        }

        LOGGER.info("Exited getHighestSalaryOfEmployees");
        return employeeList.stream().mapToInt(Employee::getEmployeeSalary).max().orElse(0);
    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() {
        LOGGER.info("Entered getTopTenHighestEarningEmployeeNames");

//        List<String> topTenHighestEarningEmployeeNamesList = new ArrayList<>();
//        List<Employee> origEmployeeList = getAllEmployees();
//        if(origEmployeeList!=null && !origEmployeeList.isEmpty()) {
//            topTenHighestEarningEmployeeNamesList = origEmployeeList.stream().sorted(Comparator.comparingInt(Employee::getEmployeeSalary).reversed()).limit(10).map(Employee::getEmployeeName).collect(Collectors.toList());
//        }

        LOGGER.info("Exited getTopTenHighestEarningEmployeeNames");
        return employeeList.stream().sorted(Comparator.comparingInt(Employee::getEmployeeSalary).reversed()).limit(10).map(Employee::getEmployeeName).collect(Collectors.toList());
    }

    /**
     * Employee is added In Memory only.
     * @param id
     * @return
     */
    @Override
    public Employee createEmployee(Map<String, Object> employeeInput) {
        LOGGER.info("Entered createEmployee");

//        CreateEmployee createEmployee = new CreateEmployee();
//        String url = baseUrl + "/api/v1/create";
//        ResponseEntity<CreateEmployeeResponseDTO> responseDTO = restTemplate.exchange(url, HttpMethod.POST, null, CreateEmployeeResponseDTO.class);
//        if(responseDTO.getStatusCode().is2xxSuccessful() && responseDTO.getBody()!=null && responseDTO.getBody().getStatus()!=null &&
//                responseDTO.getBody().getStatus().equals("success")){
//            createEmployee = responseDTO.getBody().getData();
//        }

        Employee employee = new Employee();
        if(employeeInput!=null && !employeeInput.isEmpty()) {
            employee.setId((Integer)employeeInput.get("id"));
            employee.setEmployeeName((String)employeeInput.get("employeeName"));
            employee.setEmployeeSalary((Integer)employeeInput.get("employeeSalary"));
            employee.setEmployeeAge((Integer)employeeInput.get("employeeAge"));
            employeeList.add(employee);
        }

        LOGGER.info("Exited createEmployee");
        return employee;
    }

    /**
     * Employee is deleted In Memory only.
     * @param id
     * @return
     */
    @Override
    public String deleteEmployeeById(String id) {
        LOGGER.info("Entered deleteEmployeeById");

        String message = "successfully! deleted Record";
//        String url = baseUrl + "/api/v1/delete/" + id;
//        ResponseEntity<EmployeeResponseDTO> responseDTO = restTemplate.exchange(url, HttpMethod.DELETE, null, EmployeeResponseDTO.class);
//        if(responseDTO.getStatusCode().is2xxSuccessful() && responseDTO.getBody()!=null && responseDTO.getBody().getStatus()!=null &&
//                responseDTO.getBody().getStatus().equals("success")){
//            message = responseDTO.getBody().getMessage();
//        }

        boolean isDeleted = employeeList.removeIf(e -> e.getId() == Integer.parseInt(id));

        if(!isDeleted){
            message = "Employee Id doesn't exist";
        }

        LOGGER.info("Exited deleteEmployeeById");
        return message;
    }
}
