package ru.itmo.dto;

import ru.itmo.models.user.Product;

public class AdvertDto {
    private Long id;
    private Product product;
    private String seller;

    public AdvertDto(){

    }

    public AdvertDto(Long id, Product product, String seller) {
        this.id = id;
        this.product = product;
        this.seller = seller;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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
