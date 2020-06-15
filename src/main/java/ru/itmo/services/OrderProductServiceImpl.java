package ru.itmo.services;

import org.springframework.stereotype.Service;
import ru.itmo.models.user.OrderProduct;
import ru.itmo.repositories.OrderProductRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}
