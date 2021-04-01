package com.redvinck.SpringBootProjectWeekCloud;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*, http://localhost:8080", allowedHeaders = "*", allowCredentials = "true")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/orders")
    public List<Order> getAllProducts() {
        return orderService.findAll();
    }

    @GetMapping("/check")
    public boolean greeting(@RequestParam(value = "name", defaultValue = "World") String name,
                            @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In GET Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");
        System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
        System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString().contains("partner"));
        if (partnerRole) {
            return true;
        } else {
            return false;
        }
    }


    @RequestMapping("/all")
        public List<Order> getAll() {
            return orderService.findAll();
        }

    @RequestMapping(method = RequestMethod.POST, value = "/orders")
    public String addProduct(@RequestBody Order order, @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In POST Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");

        if (partnerRole) {
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString().contains("partner"));
            addProduct();
            return "Product added";
        } else {
            return "Not Authorized to add order";
        }
    }



    @DeleteMapping("/delete/{id}")
    public void deleteTeam(@PathVariable("id") long id) {
        orderService.deleteById(id);
    }

    @RequestMapping("/add")
    public void addProduct(){
        System.out.println("Added mockup");
        //To be modified with updated parameters from front-end
 Order order = new Order(LocalDateTime.now(), null,5,true,2);
        orderService.save(order);

    }
}
