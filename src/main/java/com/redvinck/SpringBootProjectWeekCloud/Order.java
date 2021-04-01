package com.redvinck.SpringBootProjectWeekCloud;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Entity
@Table(name ="orders")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime date;
    @Type(type = "list-array")
    @Column(
            name = "orderid",
            columnDefinition = "Text[]"
    )
    private List<Long> orderid;
    private int totalPrice;
    private boolean paid;
    private String userId;


    public Order() {

    }

    public Order(int id, LocalDateTime date, List<Long> orderid, int totalPrice, boolean paid, String userId) {
        this.id = id;
        this.date = date;
        this.orderid = orderid;
        this.totalPrice = totalPrice;
        this.paid = paid;
        this.userId = userId;
    }

    public Order(LocalDateTime date, List<Long> orderid, int totalPrice, boolean paid, String userId) {
        this.date = date;
        this.orderid = orderid;
        this.totalPrice = totalPrice;
        this.paid = paid;
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Long> getOrders() {
        return orderid;
    }

    public void setOrders(List<Long> orderid) {
        this.orderid = orderid;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



}
