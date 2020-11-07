package ru.itmo.dto;

import ru.itmo.models.user.Product;

public class AdvertDto {
    private Product product;

    public AdvertDto(){

    }

    public AdvertDto(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
