/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.infrastructure;

import com.mycompany.pizzapp.repository.TestOrderRepository;
import com.mycompany.pizzapp.repository.TestPizzaRepository;
import com.mycompany.pizzapp.service.SimpleOrderService;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author margarita
 */
public class JavaConfig implements Config{

    private Map<String, Class<?>> map = new HashMap<>();
    
    {
        map.put("pizzaRepository", TestPizzaRepository.class);
        map.put("orderRepository", TestOrderRepository.class);
        map.put("orderService", SimpleOrderService.class);
    }
    
    @Override
    public Class<?> getImplementation(String beanName) {
        return map.get(beanName);
    }
    
}
