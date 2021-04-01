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
        /*return Arrays.asList(
=======
       /* return Arrays.asList(
>>>>>>> origin/master
            new Product(
                "1",
                "macbook Retina 13.3' ME662 (2013)",
                "3.0GHz Dual-core Haswell Intel Core i5 Turbo Boost up to 3.2 GHz, 3MB L3 cache 8GB (two 4GB SO-DIMMs) of 1600MHz DDR3 SDRAM",
                "https://www.dropbox.com/s/swg9bdr0ejcbtrl/img9.jpg?raw=1",
                10,
                2399
            ),
            new Product(
                "2",
                "Macbook Pro 13.3' Retina MF841LL/A",
                "Macbook Pro 13.3' Retina MF841LL/A Model 2015 Option Ram Care 12/2016",
                "https://www.dropbox.com/s/6tqcep7rk29l59e/img2.jpeg?raw=1",
                15,
                1199
            ),
            new Product(
                "3",
                "Macbook Pro 15.4' Retina MC975LL/A Model 2012",
                "3.0GHz Dual-core Haswell Intel Core i5 Turbo Boost up to 3.2 GHz, 3MB L3 cache 8GB (two 4GB SO-DIMMs) of 1600MHz DDR3 SDRAM",
                "https://www.dropbox.com/s/78fot6w894stu3n/img3.jpg?raw=1",
                1,
                1800
            )
        );*/
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
