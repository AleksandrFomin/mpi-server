package ru.itmo.services;

import org.springframework.validation.annotation.Validated;
import ru.itmo.models.user.OrderAdvert;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderAdvertService {
    OrderAdvert create(@NotNull(message = "The products for order cannot be null.") @Valid OrderAdvert orderAdvert);
}
