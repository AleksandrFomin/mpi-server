package ru.itmo.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.models.user.OrderAdvert;
import ru.itmo.models.user.OrderAdvertPK;

public interface OrderProductRepository extends CrudRepository<OrderAdvert, OrderAdvertPK> {
}
