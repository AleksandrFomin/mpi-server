package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itmo.dto.OrderDto;
import ru.itmo.dto.OrderAdvertDto;
import ru.itmo.dto.OrderForm;
import ru.itmo.models.user.Order;
import ru.itmo.models.user.OrderAdvert;
import ru.itmo.services.AdvertService;
import ru.itmo.services.OrderAdvertService;
import ru.itmo.services.OrderService;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    AdvertService advertService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderAdvertService orderAdvertService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public @NotNull Iterable<OrderDto> list(Principal principal) {
        return this.orderService.getAllOrders(principal);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Order> create(Principal principal, @RequestBody OrderForm form) {
        List<OrderAdvertDto> formDtos = form.getAdvertOrders();
        validateAdvertsExistence(formDtos);
        Order order = new Order();
//        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order, principal);

        List<OrderAdvert> orderAdverts = new ArrayList<>();
        for (OrderAdvertDto dto : formDtos) {
            orderAdverts.add(orderAdvertService.create(
                    new OrderAdvert(order, advertService.getAdvert(dto.getAdvert().getId()), dto.getQuantity())
            ));
        }

        order.setOrderAdverts(orderAdverts);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    private void validateAdvertsExistence(List<OrderAdvertDto> orderProducts) {
        List<OrderAdvertDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(advertService.getAdvert(op
                        .getAdvert()
                        .getId())))
                .collect(Collectors.toList());

//        if (!CollectionUtils.isEmpty(list)) {
//            new ResourceNotFoundException("Product not found");
//        }
    }

}
