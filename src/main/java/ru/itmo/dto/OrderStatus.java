package ru.itmo.dto;

public enum OrderStatus {
    NEW("NEW"),
    SUBMITTED("SUBMITTED"),
    DELIVERED("DELIVERED");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
