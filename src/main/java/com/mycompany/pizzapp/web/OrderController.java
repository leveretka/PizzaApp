package com.mycompany.pizzapp.web;

import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.service.CustomerService;
import com.mycompany.pizzapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by margarita on 18.08.15.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewOrders(Model model) {
        model.addAttribute("orders",
                orderService.getAllOrders());

        return "orders";
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String viewMyOrders(Model model) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Customer customer = customerService.getCustomerByUserName(name);
        model.addAttribute("orders",
                orderService.getAllCustomerOrders(customer));

        return "orders";
    }

}
