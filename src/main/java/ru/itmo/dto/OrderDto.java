package ru.itmo.dto;

import java.util.List;

public class OrderDto {
    private Long id;
    private String status;
    private List<OrderAdvertDto> orderProducts;

    public OrderDto() {
    }

    public OrderDto(Long id, String status, List<OrderAdvertDto> orderProducts) {
        this.id = id;
        this.status = status;
        this.orderProducts = orderProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderAdvertDto> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderAdvertDto> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
