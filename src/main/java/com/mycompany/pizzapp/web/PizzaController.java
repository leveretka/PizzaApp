package com.mycompany.pizzapp.web;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.pizzapp.domain.Customer;
import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.service.CustomerService;
import com.mycompany.pizzapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.mycompany.pizzapp.domain.Pizza;
import com.mycompany.pizzapp.service.PizzaService;

import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes({"pizzasInOrder"})
@RequestMapping("/pizza")
public class PizzaController extends AbstractPizzaController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzas(Model model) {
        model.addAttribute("pizzas",
                pizzaService.getAllPizzas());

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getName();
        Customer customer = customerService.getCustomerByUserName(name);
        Double sum = customer == null ? 0 : customer.getAccumulativeCard().getTotalSum();

        model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("name", name);
        model.addAttribute("ballance", sum);
        return "pizzas";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "newpizza";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
    public String addNewPizza(@ModelAttribute Pizza newPizza) {
        pizzaService.addPizza(newPizza);
        return "redirect:";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("pizzaid") Pizza pizza,
            Model model) {
        model.addAttribute("pizza", pizza);
        return "newpizza";
    }

//    @RequestMapping(value = "/makeorder", method = RequestMethod.GET)
//    public String makeOrder(Model model) {
//        model.addAttribute("pizzas", pizzaService.getAllPizzas());
//        model.addAttribute("pizzasInOrder", new HashMap<Pizza, Integer>());
//        model.addAttribute("pizzasQnt", new ArrayList<Integer>());
//
//        return "order";
//    }
//
//    @RequestMapping(value = "addToOrder", method = RequestMethod.POST)
//    public String addManyPizzasToOrder(@RequestParam String pizza, @RequestParam Integer qnt, Model model) {
//        String[] idStr = pizza.split(",");
//        Integer[] ids = new Integer[idStr.length];
//        for (int i = 0; i < ids.length; ++i) {
//            ids[i] = Integer.parseInt(idStr[i].trim());
//        }
//        Order order = orderService.placeNewOrder(null, ids);
//
//        model.addAttribute("totalPrice", orderService.calculateTotalPrice(order));
//
//
//        return "price";
//    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addPizzaToOrder(@RequestParam("pizzaid") Pizza pizza, HttpSession httpSession, Model model) {

        Map<Pizza, Integer> pizzasInOrder = (Map<Pizza, Integer>) httpSession.getAttribute("pizzasInOrder");

        if  (pizzasInOrder == null) {
            pizzasInOrder = new HashMap<>();
        }
        if (pizzasInOrder.containsKey(pizza)) {
            pizzasInOrder.replace(pizza, pizzasInOrder.get(pizza) + 1);
        } else {
            pizzasInOrder.put(pizza, 1);
        }

        model.addAttribute("pizzasInOrder", pizzasInOrder);

        return "redirect:";
    }

    @RequestMapping(value = "previewOrder", method = RequestMethod.GET)
    public String previewOrder(HttpSession httpSession, Model model) {

        Map<Pizza, Integer> pizzasInOrder = (Map<Pizza, Integer>) httpSession.getAttribute("pizzasInOrder");

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Customer customer = customerService.getCustomerByUserName(name);

        //Order order = orderService.placeNewOrder(customer, pizzasInOrder);
        Order order = new Order(customer, pizzasInOrder);
        order.calcTotalCost();

        model.addAttribute("totalPrice", order.getTotalCost());
        //model.addAttribute("pizzasToShow", pizzasInOrder);
        //model.addAttribute("pizzasInOrder", new HashMap<>());
        return "preview";

    }

    @RequestMapping(value = "removePizza", method = RequestMethod.POST)
    public String removePizzaFromOrder(HttpSession httpSession, Model model, @RequestParam("pizzaid") Pizza pizza) {

        Map<Pizza, Integer> pizzasInOrder = (Map<Pizza, Integer>) httpSession.getAttribute("pizzasInOrder");

        if (pizzasInOrder.containsKey(pizza)) {
            if (pizzasInOrder.get(pizza) > 1) {
                pizzasInOrder.replace(pizza, pizzasInOrder.get(pizza) - 1);
            } else {
                pizzasInOrder.remove(pizza);
            }
        }

        model.addAttribute("pizzasInOrder", pizzasInOrder);
        return "redirect:previewOrder";

    }

    @RequestMapping(value = "submitOrder", method = RequestMethod.POST)
    public String submitOrder(HttpSession httpSession, Model model) {

        Map<Pizza, Integer> pizzasInOrder = (Map<Pizza, Integer>) httpSession.getAttribute("pizzasInOrder");

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Customer customer = customerService.getCustomerByUserName(name);

        Order order = orderService.placeNewOrder(customer, pizzasInOrder);


        model.addAttribute("totalPrice", order.getTotalCost());
        model.addAttribute("pizzasToShow", pizzasInOrder);
        model.addAttribute("pizzasInOrder", new HashMap<>());
        return "price";

    }


}
