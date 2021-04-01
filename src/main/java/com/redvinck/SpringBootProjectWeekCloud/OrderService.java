package com.redvinck.SpringBootProjectWeekCloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }



    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findAllByUserId(long id) {
        return orderRepository.findAllByUserId(id);
    }
}
