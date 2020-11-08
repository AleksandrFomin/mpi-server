package ru.itmo.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.itmo.models.seller.Advert;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
public class OrderAdvert {

    @EmbeddedId
    @JsonIgnore
    private OrderAdvertPK pk;

    @Column(nullable = false)
    private Integer quantity;

    public OrderAdvert() {
    }

    public OrderAdvert(Order order, Advert advert, Integer quantity) {
        pk = new OrderAdvertPK();
        pk.setOrder(order);
        pk.setAdvert(advert);
        this.quantity = quantity;
    }

    @Transient
    public Advert getAdvert() {
        return this.pk.getAdvert();
    }

    @Transient
    public Double getTotalPrice() {
        return getAdvert().getProduct().getPrice() * getQuantity();
    }

    public OrderAdvertPK getPk() {
        return pk;
    }

    public void setPk(OrderAdvertPK pk) {
        this.pk = pk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderAdvert that = (OrderAdvert) o;
        return Objects.equals(pk, that.pk) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, quantity);
    }
}
