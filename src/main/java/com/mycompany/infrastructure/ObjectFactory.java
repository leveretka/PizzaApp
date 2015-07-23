/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infrastructure;

/**
 *
 * @author margarita
 */
public class ObjectFactory {
    
    private static ObjectFactory instance;
    Config config = new JavaConfig();
    
    private ObjectFactory() {
    }
    
    public static ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }
        return instance;
    }

    public Object createObject(String str) throws InstantiationException, IllegalAccessException {
        Class<?> clazz = config.getImplementation(str);
        return clazz.newInstance();
    }
}
