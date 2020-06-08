package ru.itmo.services;

import ru.itmo.models.user.Order;

public interface OrderService {

    public Iterable<Order> getAllOrders();

    public Order create(Order order);

    public void update(Order order);
}
