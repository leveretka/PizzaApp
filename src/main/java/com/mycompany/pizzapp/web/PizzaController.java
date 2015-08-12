package com.mycompany.pizzapp.web;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.pizzapp.domain.Order;
import com.mycompany.pizzapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzas(Model model) {
        model.addAttribute("pizzas",
                pizzaService.getAllPizzas());
        return "pizzas";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "newpizza";
    }

    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
    public String addNewPizza(@ModelAttribute Pizza newPizza) {
        pizzaService.addPizza(newPizza);
        return "redirect:";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("pizzaid") Pizza pizza,
            Model model) {
        model.addAttribute("pizza", pizza);
        return "newpizza";
    }

    protected Pizza getPizzabyId(Integer id) {
        if (id<=0) throw new IllegalArgumentException("ID<0");
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null)
            throw new NotFoundPizzaException("Pizza id" + id + " not found" );
        return pizza;

    }

    @InitBinder
    private void pizzaBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Pizza.class,
                new PropertyEditorSupport() {

                    @Override
                    public void setAsText(String pizzasId) {
                        Pizza pizza = null;
                        if (pizzasId != null && !pizzasId.trim().isEmpty()) {
                            Integer id = Integer.parseInt(pizzasId);
                            pizza = getPizzabyId(id);
                        }
                        setValue(pizza);
                    }
                });
    }

    @RequestMapping(value = "/makeorder", method = RequestMethod.GET)
    public String makeOrder(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        model.addAttribute("pizzasInOrder", new HashMap<Pizza, Integer>());
        model.addAttribute("pizzasQnt", new ArrayList<Integer>());

        return "order";
    }

    @RequestMapping(value = "addToOrder", method = RequestMethod.POST)
    public String addManyPizzasToOrder(@RequestParam String pizza, @RequestParam Integer qnt, Model model) {
        String[] idStr = pizza.split(",");
        Integer[] ids = new Integer[idStr.length];
        for (int i = 0; i < ids.length; ++i) {
            ids[i] = Integer.parseInt(idStr[i].trim());
        }
        Order order = orderService.placeNewOrder(null, ids);

        model.addAttribute("totalPrice", orderService.calculateTotalPrice(order));


        return "price";
    }

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

    @RequestMapping(value = "submitOrder", method = RequestMethod.POST)
    public String submitOrder(HttpSession httpSession, Model model) {

        Map<Pizza, Integer> pizzasInOrder = (Map<Pizza, Integer>) httpSession.getAttribute("pizzasInOrder");

        Order order = orderService.placeNewOrder(null, pizzasInOrder);

        model.addAttribute("totalPrice", orderService.calculateTotalPrice(order));
        model.addAttribute("pizzasToShow", pizzasInOrder);
        model.addAttribute("pizzasInOrder", new HashMap<>());
        return "price";

    }


}
