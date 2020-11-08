package ru.itmo.dto;

import ru.itmo.models.user.Product;

public class AdvertDto {
    private Long id;
    private Product product;

    public AdvertDto(){

    }

    public AdvertDto(Long id, Product product) {
        this.id = id;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
