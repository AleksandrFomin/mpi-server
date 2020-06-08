package ru.itmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.models.user.Order;
import ru.itmo.models.user.Product;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
