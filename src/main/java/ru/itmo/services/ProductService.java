package ru.itmo.services;

import ru.itmo.models.user.Product;

public interface ProductService {

    public Iterable<Product> getAllProducts();

    public Product getProduct(long id);

    public Product save(Product product);

}
