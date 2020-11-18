package ru.itmo.dto;

public enum OrderStatus {
    NEW("NEW"),
    SUMBITTED("SUBMITTED");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
