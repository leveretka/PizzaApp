/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.infrastructure;

/**
 *
 * @author margarita
 */
public interface Config {
    Class<?> getImplementation(String beanName);
}
