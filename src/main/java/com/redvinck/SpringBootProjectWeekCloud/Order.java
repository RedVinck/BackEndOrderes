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
    private int id;
    private LocalDateTime date;
    @Type(type = "list-array")
    @Column(
            name = "orderid",
            columnDefinition = "text[]"
    )
    private List<Integer> orderid;
    private int totalPrice;
    private boolean paid;
    private int userId;


    public Order() {

    }

    public Order(int id, LocalDateTime date, List<Integer> orderid, int totalPrice, boolean paid, int userId) {
        this.id = id;
        this.date = date;
        this.orderid = orderid;
        this.totalPrice = totalPrice;
        this.paid = paid;
        this.userId = userId;
    }

    public Order(LocalDateTime date, List<Integer> orderid, int totalPrice, boolean paid, int userId) {
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

    public List<Integer> getOrders() {
        return orderid;
    }

    public void setOrders(List<Integer> orderid) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



}
