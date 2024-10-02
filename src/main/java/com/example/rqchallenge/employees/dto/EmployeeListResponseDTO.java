package com.example.rqchallenge.employees.dto;

import java.util.List;

/**
 * Response DTO Class for Employee data.
 */
public class EmployeeListResponseDTO {

    private String status;

    private List<Employee> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getData() {
        return data;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }
}
