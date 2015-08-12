//package com.mycompany.pizzapp.web;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.mycompany.pizzapp.service.PizzaService;
//
//@Controller("helloController")
//public class HelloSpringMVC {
//	
//	@Autowired
//	private PizzaService pizzaService;
//	
//	@RequestMapping("/hello")
//	@ResponseBody
//	public String hello() {
//		return "Hello SpringMVC!";
//	}
//
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	public String handleDefaultRequest(Model model) {
//		model.addAttribute("msg", new Date());
//		return "hello";
//	}
//	
//	@RequestMapping(value="/pizzas", method=RequestMethod.GET)
//	public String viewPizzas(Model model) {
//		model.addAttribute("pizzas", pizzaService.getAllPizzas());
//		return "pizzas";
//	}
//}
