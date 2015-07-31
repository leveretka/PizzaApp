/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
 * @author andrii
 */
public class CustomAnnotationBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessAfterInitialization(Object bean, String beanName) {
    	return new ProxyForBenchmarkAnnotation().checkAndCreateProxyObjForBenchmark(bean);
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
    	return bean;
    }
}
