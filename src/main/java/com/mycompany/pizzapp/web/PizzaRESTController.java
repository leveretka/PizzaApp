package com.mycompany.pizzapp.web;

import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by margarita on 12.08.15.
 */
@RestController
public class PizzaRESTController extends AbstractPizzaController {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public ResponseEntity<String> hello() {
        //return "Hello from RESTController";
        return new ResponseEntity<String>("Hello from RESTController", HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping(value = "pizza/{pizzaid}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> getPizzabyId(@PathVariable(value = "pizzaid") Pizza pizza) {
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/pizza",
            headers = "Content-Type=application/json")
    public ResponseEntity<?> createNewPizza(
            @RequestBody Pizza pizza,
            UriComponentsBuilder builder) {
        System.out.println(pizza);
        pizzaService.addPizza(pizza);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/pizza/{id}")
                        .buildAndExpand(pizza.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "order/{orderid}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderId(@PathVariable("orderid") String orderid) {
        Order order = orderService.getOrderById(Integer.parseInt(orderid.trim()));
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/order",
            headers = "Content-Type=application/json")
    public ResponseEntity<?> createNewOrder(
            @RequestBody String jsonOrder,
            UriComponentsBuilder builder) {

        Order order = orderService.placeNewOrder(jsonOrder);
        System.out.println(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/order/{id}")
                        .buildAndExpand(order.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
