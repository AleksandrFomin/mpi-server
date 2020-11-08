package ru.itmo.services;

import org.springframework.stereotype.Service;
import ru.itmo.models.user.OrderAdvert;
import ru.itmo.repositories.OrderProductRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderAdvertServiceImpl implements OrderAdvertService {

    private OrderProductRepository orderProductRepository;

    public OrderAdvertServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderAdvert create(OrderAdvert orderAdvert) {
        return this.orderProductRepository.save(orderAdvert);
    }
}
