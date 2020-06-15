package ru.itmo.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.models.user.OrderProduct;
import ru.itmo.models.user.OrderProductPK;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
