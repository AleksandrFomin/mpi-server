package ru.itmo.models.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ru.itmo.models.User;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Valid
    private List<OrderAdvert> orderAdverts = new ArrayList<>();

    @ElementCollection
    private List<Long> submitters = new ArrayList<>();

    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        List<OrderAdvert> orderAdverts = getOrderAdverts();
        for (OrderAdvert op : orderAdverts) {
            sum += op.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.orderAdverts.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderAdvert> getOrderAdverts() {
        return orderAdverts;
    }

    public void setOrderAdverts(List<OrderAdvert> orderAdverts) {
        this.orderAdverts = orderAdverts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Long> getSubmitters() {
        return submitters;
    }

    public void setSubmitters(List<Long> submitters) {
        this.submitters = submitters;
    }
}
