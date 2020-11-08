package ru.itmo.dto;

import java.util.List;

public class OrderForm {
    private List<OrderAdvertDto> advertOrders;

    public OrderForm() {
    }

    public OrderForm(List<OrderAdvertDto> advertOrders) {
        this.advertOrders = advertOrders;
    }

    public List<OrderAdvertDto> getAdvertOrders() {
        return advertOrders;
    }

    public void setAdvertOrders(List<OrderAdvertDto> advertOrders) {
        this.advertOrders = advertOrders;
    }
}
