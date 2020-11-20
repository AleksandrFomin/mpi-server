package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.dto.AdvertDto;
import ru.itmo.dto.OrderDto;
import ru.itmo.dto.OrderAdvertDto;
import ru.itmo.dto.OrderStatus;
import ru.itmo.models.User;
import ru.itmo.models.user.Order;
import ru.itmo.models.user.OrderAdvert;
import ru.itmo.repositories.OrderRepository;
import ru.itmo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<OrderDto> getAllOrders(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orderList = this.orderRepository.findAllByUser(user);
        for(Order order : orderList) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setStatus(order.getStatus());
            List<OrderAdvertDto> orderAdvertDtoList = new ArrayList<>();
            for(OrderAdvert orderAdvert : order.getOrderAdverts()) {
                OrderAdvertDto orderAdvertDto = new OrderAdvertDto();
                orderAdvertDto.setAdvert(new AdvertDto(
                        orderAdvert.getAdvert().getId(), orderAdvert.getAdvert().getProduct(),
                        orderAdvert.getAdvert().getUser().getUsername()
                ));
                orderAdvertDto.setQuantity(orderAdvert.getQuantity());
                orderAdvertDtoList.add(orderAdvertDto);
            }
            orderDto.setAdvertOrders(orderAdvertDtoList);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public Order create(Order order, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        order.setUser(user);
        order.setDateCreated(LocalDate.now());
        return this.orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }

    @Override
    public Iterable<OrderDto> getAllOrdersBySeller(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orderList = this.orderRepository.findAll();
        for(Order order : orderList) {
            if (!order.getStatus().equals(OrderStatus.NEW.getStatus())) {
                continue;
            }
            if (!order.getSubmitters().contains(user.getId())) {
                continue;
            }

            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setStatus(order.getStatus());
            List<OrderAdvertDto> orderAdvertDtoList = new ArrayList<>();
            for(OrderAdvert orderAdvert : order.getOrderAdverts()) {
                if (!orderAdvert.getAdvert().getUser().getId().equals(user.getId())) {
                    continue;
                }
                OrderAdvertDto orderAdvertDto = new OrderAdvertDto();
                orderAdvertDto.setAdvert(new AdvertDto(
                        orderAdvert.getAdvert().getId(), orderAdvert.getAdvert().getProduct(),
                        orderAdvert.getAdvert().getUser().getUsername()
                ));
                orderAdvertDto.setQuantity(orderAdvert.getQuantity());
                orderAdvertDtoList.add(orderAdvertDto);
            }
            if (!orderAdvertDtoList.isEmpty()) {
                orderDto.setAdvertOrders(orderAdvertDtoList);
                orderDtos.add(orderDto);
            }
        }
        return orderDtos;
    }

    @Override
    public void submitOrder(OrderDto order, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        Optional<Order> opt = this.orderRepository.findById(order.getId());
        if (!opt.isPresent()) {
            return;
        }

        Order submitted = opt.get();
        List<Long> submitters = submitted.getSubmitters();
        List<Long> newList = new ArrayList<>();
        for (Long id : submitters) {
            if (!id.equals(user.getId())) {
                newList.add(id);
            }
        }
        submitted.setSubmitters(newList);
        if (newList.isEmpty()) {
            submitted.setStatus(OrderStatus.SUMBITTED.getStatus());
        }
        orderRepository.save(submitted);
    }
}
