package com.mycompany.pizzapp.web;

import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.service.CustomerService;
import com.mycompany.pizzapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by margarita on 23.08.15.
 */

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Model model) {
        return "registration";
    }



    @RequestMapping(value = "/registration/register", method = RequestMethod.POST)
    public String addNewCustomer(@RequestParam(value="fullname", required=false) String fullName,
                                 @RequestParam(value="login", required=true) String login,
                                 @RequestParam(value="pass", required=true) String password){

        customerService.addNewCustomer(fullName, login, password);

        return "pizza";
    }


}
