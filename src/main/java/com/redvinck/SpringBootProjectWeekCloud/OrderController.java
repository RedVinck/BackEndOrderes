package com.redvinck.SpringBootProjectWeekCloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://frontend-ucllteam02.ocp-ucll-40cb0df2b03969eabb3fac6e80373775-0000.eu-de.containers.appdomain.cloud", allowedHeaders = "*", allowCredentials = "true")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/orders")
    public List<Order> getAllProducts(@AuthenticationPrincipal Jwt accessToken) throws IllegalAccessException {
        return orderService.findAll();
/*        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("admin");
        System.out.println("Contains sequence 'admin': " + accessToken.getClaims().get("scope").toString());
        System.out.println("Contains sequence 'admin': " + accessToken.getClaims().get("scope").toString().contains("admin"));
        if (partnerRole) {
            return orderService.findAll();
        } else {
            throw new IllegalAccessException("Your privileges do not allow you to view the orders, contact an admin if you believe this is in error.");
        }*/
    }

    @RequestMapping("/orders/{userId}")
    public List<Order> getAllProducts(@PathVariable("userId") String id, @AuthenticationPrincipal Jwt accessToken) throws IllegalAccessException {
        return orderService.findAllByUserId(id);
/*        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("admin");
        System.out.println("Contains sequence 'admin': " + accessToken.getClaims().get("scope").toString());
        System.out.println("Contains sequence 'admin': " + accessToken.getClaims().get("scope").toString().contains("admin"));
        if (partnerRole) {
            return orderService.findAllByUserId(id);
        }
        else{
            throw new IllegalAccessException("Your privileges do not allow you to view the orders, contact an admin if you believe this is in error.");
        }*/
    }

    @GetMapping("/check")
    public boolean greeting(@RequestParam(value = "name", defaultValue = "World") String name,
                            @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In GET Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("admin");
        System.out.println("Contains sequence 'admin': " + accessToken.getClaims().get("scope").toString());
        System.out.println("Contains sequence 'admin': " + accessToken.getClaims().get("scope").toString().contains("admin"));
        if (partnerRole) {
            return true;
        } else {
            return false;
        }
    }

/*    @RequestMapping(method = RequestMethod.POST, value = "/orders")
    public String addProduct(@RequestBody Order order) {
        System.out.println("In POST Request");
        if (addProduct()) {
            return "Product added";
        } else throw new IllegalArgumentException("Product was not added");
    }*/

    @DeleteMapping(value = "/delete/{id}")
    public void deleteTeam(@PathVariable("id") long id, @AuthenticationPrincipal Jwt accessToken) throws IllegalAccessException {
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("admin");
        System.out.println("Contains sequence 'admin': " + accessToken.getClaims().get("scope").toString());
        System.out.println("Contains sequence 'admin': " + accessToken.getClaims().get("scope").toString().contains("admin"));
        if (partnerRole) {
            orderService.deleteById(id);
        } else {
            throw new IllegalAccessException("Your privileges do not allow you to delete an order, contact an admin if you believe this is in error");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public boolean addOrder(@RequestBody Order order) {
        System.out.println("Added mockup");
        orderService.save(order);
        return true;
    }


    @RequestMapping("/mockup")
    public boolean addMockup() {
        System.out.println("Added mockup");
        //To be modified with updated parameters from front-end
        Order order = new Order(LocalDateTime.now(), null, 5, true, "9bd0892a-e88c-44fe-b939-10e3f8b0b0dd");
        orderService.save(order);
        return true;
    }

}
