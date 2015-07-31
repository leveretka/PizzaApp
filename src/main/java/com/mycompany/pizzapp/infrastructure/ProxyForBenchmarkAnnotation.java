/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author andrii
 */
class ProxyForBenchmarkAnnotation {


    Object checkAndCreateProxyObjForBenchmark(Object object) throws IllegalArgumentException, SecurityException {
        Class<?> clazz = object.getClass();

        for (Method m : clazz.getMethods()) {
            if (m.isAnnotationPresent(Benchmark.class)) {
                return createProxyObj(object);
            }

        }
        return object;

    }

    private Object createProxyObj(final Object o) throws IllegalArgumentException {
        final Class<?> type = o.getClass();

        return Proxy.newProxyInstance(type.getClassLoader(),
                getInterfaces(type),
                new InvocationHandler() {

                    @Override
                    public Object invoke(
                            Object proxy,
                            Method method,
                            Object[] args) throws Throwable {
                                if (type.getMethod(method.getName(), method.getParameterTypes())
                                .isAnnotationPresent(Benchmark.class)) {

                                    System.out.println("Benchmark start: "
                                            + method.getName());
                                    long start = System.nanoTime();
                                    Object retVal = method.invoke(o, args);
                                    long result = System.nanoTime() - start;
                                    System.out.println(result);
                                    System.out.println("Benchmark finish: "
                                            + method.getName());
                                    return retVal;
                                } else {
                                    return method.invoke(o, args);
                                }
                            }
                });

    }
    
    private Class<?>[] getInterfaces (Class<?> type) {

    	List<Class<?>> allInterfaces = new ArrayList<>();
    	Class<?>[] interfaces = type.getInterfaces();
    	allInterfaces.addAll(Arrays.asList(interfaces));
    	
    	Class<?> superClass = type.getSuperclass();
    	if (superClass != null) {
    		allInterfaces.addAll(Arrays.asList(getInterfaces(superClass)));
    	}
    	
    	Class<?>[] result = new Class<?>[allInterfaces.size()]; 
    	for (int i = 0; i < allInterfaces.size(); ++i) {
    		result[i] = allInterfaces.get(i);
    	}
    	
    	return result;
    }
}
