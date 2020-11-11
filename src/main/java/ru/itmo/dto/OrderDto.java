package ru.itmo.dto;

import java.util.List;

public class OrderDto {
    private Long id;
    private String status;
    private List<OrderAdvertDto> advertOrders;

    public OrderDto() {
    }

    public OrderDto(Long id, String status, List<OrderAdvertDto> advertOrders) {
        this.id = id;
        this.status = status;
        this.advertOrders = advertOrders;
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

    public List<OrderAdvertDto> getAdvertOrders() {
        return advertOrders;
    }

    public void setAdvertOrders(List<OrderAdvertDto> advertOrders) {
        this.advertOrders = advertOrders;
    }
}
