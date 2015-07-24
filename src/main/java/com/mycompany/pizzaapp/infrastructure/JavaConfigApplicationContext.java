package com.mycompany.pizzaapp.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.infrastructure.ApplicationContext;
import com.mycompany.infrastructure.Config;

public class JavaConfigApplicationContext implements ApplicationContext {

	private Config config;
	private Map<String, Object> beans = new HashMap<>();
	
	public JavaConfigApplicationContext(Config config) {
		this.config = config;
	}

	@Override
	public Object getBean(String beanName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object bean = beans.get(beanName);
		
		if (bean != null) {
			return bean;
		}
		
		BeanBuilder beanBuilder = new BeanBuilder(beanName);
		beanBuilder.createObject();
		beanBuilder.createProxy();
		beanBuilder.callInitMethod();
		
		return  beanBuilder.getObject();
		
//		Class<?> clazz = config.getImplementation(beanName);
//		
//		Constructor<?> constructor = clazz.getConstructors()[0];
//		Class<?>[] parameters = constructor.getParameterTypes();
//		
//		if (parameters.length == 0) {
//			bean = clazz.newInstance();
//			beans.put(beanName, bean);
//			return bean;
//		}
//		
//		Object[] initArgs = new Object[parameters.length];
//		
//		for (int i = 0; i < parameters.length; ++i) {
//			String name = parameters[i].getSimpleName();
//			name = name.replace("" + name.charAt(0), ("" + name.charAt(0)).toLowerCase());
//			initArgs[i] = getBean(name); 
//		}
//		
//		return constructor.newInstance(initArgs);
		
	}
	
	class BeanBuilder {
		
		private	Object obj;
		private String beanName; 
		
		
		public BeanBuilder(String beanName) {
			this.beanName = beanName;
		}
		
		public void createProxy() {
		}

		public void createObject() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Class<?> clazz = config.getImplementation(beanName);
			
			Constructor<?> constructor = clazz.getConstructors()[0];
			Class<?>[] parameters = constructor.getParameterTypes();
			
			if (parameters.length == 0) {
				obj = clazz.newInstance();
				beans.put(beanName, obj);
				return;
			}
			
			Object[] initArgs = new Object[parameters.length];
			
			for (int i = 0; i < parameters.length; ++i) {
				String name = parameters[i].getSimpleName();
				name = name.replace("" + name.charAt(0), ("" + name.charAt(0)).toLowerCase());
				initArgs[i] = getBean(name); 
			}
			
			obj = constructor.newInstance(initArgs);
			beans.put(beanName, obj);
			return;

		}
		
		public void callInitMethod() throws SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			
			Class<?> clazz = obj.getClass();
			Method method;
			
			try {
				method = clazz.getMethod("init");
			} catch (NoSuchMethodException e) {
				return;
			}
			
			method.invoke(obj);
			
		}
		
		public Object getObject() {
			return obj;
		}
		
	}

}
