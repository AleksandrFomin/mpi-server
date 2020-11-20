package ru.itmo.services;

import ru.itmo.dto.OrderDto;
import ru.itmo.dto.OrderForm;
import ru.itmo.models.user.Order;

import java.security.Principal;

public interface OrderService {

    public Iterable<OrderDto> getAllOrders(Principal principal);

    public Order create(Order order, Principal principal);

    public void update(Order order);

    Iterable<OrderDto> getAllOrdersBySeller(Principal principal);

    void submitOrder(OrderDto orderDto, Principal principal);
}
