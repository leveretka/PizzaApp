/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pizzapp.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andrii
 */
public class JavaConfigApplicationContext implements ApplicationContext {

    private final Config config;
    private final Map<String, Object> beans = new HashMap<>();

    public JavaConfigApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        Object bean = beans.get(beanName);
        if (bean != null) {
            return bean;
        }

        BeanBuilder beanBuilder = new BeanBuilder(beanName);
        beanBuilder.createOject();
        beanBuilder.callInitMethod();
        beanBuilder.createProxy();

        return beanBuilder.getObject();

//        Class<?> clazz = config.getImplementation(beanName);
//
//        Constructor<?> constructor = clazz.getConstructors()[0];
//        Class<?>[] parameters = constructor.getParameterTypes();
//        if (parameters.length == 0) {
//            bean = clazz.newInstance();
//            beans.put(beanName, bean);
//            return bean;
//        }
//        Object[] initArgs = new Object[parameters.length];
//        for (int i = 0; i < parameters.length; i++) {
//            Class<?> cl = parameters[i];
//            System.out.println(cl);
//            String paramType = cl.getSimpleName();
//
//            String localBeanName
//                    = Character.toLowerCase(paramType.charAt(0)) + paramType.substring(1);
//            Object obj = getBean(localBeanName);
//            initArgs[i] = obj;
//        }
//
//        bean = constructor.newInstance(initArgs);
//        beans.put(beanName, bean);
//        return bean;
    }

    class BeanBuilder {

        private Object obj;
        private final String beanName;

        public BeanBuilder(String beanName) {
            this.beanName = beanName;
        }

        public void createOject() throws Exception {
            Class<?> clazz = config.getImplementation(beanName);

            Constructor<?> constructor = clazz.getConstructors()[0];
            Class<?>[] parameters = constructor.getParameterTypes();
            if (parameters.length == 0) {
                obj = clazz.newInstance();
            } else {
                Object[] initArgs = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    Class<?> cl = parameters[i];
                    System.out.println(cl);
                    String paramType = cl.getSimpleName();

                    String localBeanName
                            = Character.toLowerCase(paramType.charAt(0)) + paramType.substring(1);

                    initArgs[i] = getBean(localBeanName);
                }

                obj = constructor.newInstance(initArgs);
            }
            beans.put(beanName, obj);
        }

        public void callInitMethod() throws Exception {
            Class<?> clazz = obj.getClass();
            Method method;
            try {
                method = clazz.getMethod("init");
            } catch (NoSuchMethodException ex) {
                return;
            }
            method.invoke(obj);
        }

        private void createProxy() {
             
            ProxyForBenchmarkAnnotation proxyForBenchmarkAnnotation 
                    = new ProxyForBenchmarkAnnotation();
            obj = proxyForBenchmarkAnnotation.checkAndCreateProxyObjForBenchmark(obj);

        }

//        private Object checkAndCreateProxyObjForBenchmark(Object object) throws IllegalArgumentException, SecurityException {
//            Class<?> clazz = obj.getClass();
//
//            for (Method m : clazz.getMethods()) {
//                if (m.isAnnotationPresent(Benchmark.class)) {
//                    return createProxyObj(object);
//                }
//
//            }
//            return object;
//
//        }
//
//        private Object createProxyObj(final Object o) throws IllegalArgumentException {
//            final Class<?> type = o.getClass();
//
//            return Proxy.newProxyInstance(type.getClassLoader(),
//                    type.getInterfaces(),
//                    new InvocationHandler() {
//
//                        @Override
//                        public Object invoke(
//                                Object proxy,
//                                Method method,
//                                Object[] args) throws Throwable {
//                                    if (type.getMethod(method.getName(), method.getParameterTypes())
//                                    .isAnnotationPresent(Benchmark.class)) {
//
//                                        System.out.println("Benchmark start: "
//                                                + method.getName());
//                                        long start = System.nanoTime();
//                                        Object retVal = method.invoke(o, args);
//                                        long result = System.nanoTime() - start;
//                                        System.out.println(result);
//                                        System.out.println("Benchmark finish: "
//                                                + method.getName());
//                                        return retVal;
//                                    } else {
//                                        return method.invoke(o, args);
//                                    }
//                                }
//                    });

//            return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), type.getInterfaces(), new InvocationHandler() {
//                @Override
//                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    if (type.getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Benchmark.class)) {
//                        System.out.println(method.getName() + " BENCHMARK START***********");
//                        long before = System.nanoTime();
//                        Object retVal = method.invoke(o, args);
//                        long after = System.nanoTime();
//                        System.out.println(after - before);
//                        System.out.println(method.getName() + " BENCHMARK END***********");
//                        return retVal;
//                    } else {
//                        return method.invoke(o, args);
//                    }
//                }
//            });
//        }

        public Object getObject() {
            return obj;
        }

    }

}
