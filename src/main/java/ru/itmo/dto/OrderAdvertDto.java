package ru.itmo.dto;

public class OrderAdvertDto {

    private AdvertDto advert;
    private Integer quantity;

    public OrderAdvertDto() {
    }

    public OrderAdvertDto(AdvertDto advert, Integer quantity) {
        this.advert = advert;
        this.quantity = quantity;
    }

    public AdvertDto getAdvert() {
        return advert;
    }

    public void setAdvert(AdvertDto advert) {
        this.advert = advert;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

