package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.dto.OrderDto;
import ru.itmo.dto.OrderProductDto;
import ru.itmo.models.User;
import ru.itmo.models.user.Order;
import ru.itmo.models.user.OrderProduct;
import ru.itmo.repositories.OrderRepository;
import ru.itmo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
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
            List<OrderProductDto> orderProductDtoList = new ArrayList<>();
            for(OrderProduct orderProduct : order.getOrderProducts()) {
                OrderProductDto orderProductDto = new OrderProductDto();
                orderProductDto.setProduct(orderProduct.getProduct());
                orderProductDto.setQuantity(orderProduct.getQuantity());
                orderProductDtoList.add(orderProductDto);
            }
            orderDto.setOrderProducts(orderProductDtoList);
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
}
