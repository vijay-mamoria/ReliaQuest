package com.example.rqchallenge.employees.dto;

/**
 * Response DTO Class for Employee data.
 */
public class CreateEmployeeResponseDTO {

    private String status;

    private CreateEmployee data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CreateEmployee getData() {
        return data;
    }

    public void setData(CreateEmployee data) {
        this.data = data;
    }
}
