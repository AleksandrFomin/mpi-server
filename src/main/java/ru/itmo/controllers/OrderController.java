package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itmo.models.user.Order;
import ru.itmo.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

//    @Autowired
//    OrderService orderService;
//
//    @PostMapping
//    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
//        List<OrderProductDto> formDtos = form.getProductOrders();
//        validateProductsExistence(formDtos);
//        // create order logic
//        // populate order with products
//
//        order.setOrderProducts(orderProducts);
//        orderService.update(order);
//
//        String uri = ServletUriComponentsBuilder
//                .fromCurrentServletMapping()
//                .path("/orders/{id}")
//                .buildAndExpand(order.getId())
//                .toString();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Location", uri);
//
//        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
//    }
}
