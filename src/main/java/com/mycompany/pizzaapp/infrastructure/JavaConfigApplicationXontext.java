package com.mycompany.pizzaapp.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.infrastructure.ApplicationContext;
import com.mycompany.infrastructure.Config;

public class JavaConfigApplicationXontext implements ApplicationContext {

	private Config config; 
	
	public JavaConfigApplicationXontext(Config config) {
		this.config = config;
	}

	@Override
	public Object getBean(String beanName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<?> clazz = config.getImplementation(beanName);
		
		Constructor<?> constructor = clazz.getConstructors()[0];
		Class<?>[] parameters = constructor.getParameterTypes();
		
		if (parameters.length == 0) {
			return clazz.newInstance();
		}
		
		List<Object> params = new ArrayList<>();
		
		for (Class p: parameters) {
			String name = p.getSimpleName();
			name = name.replace("" + name.charAt(0), ("" + name.charAt(0)).toLowerCase());
			params.add(getBean(name));
		}
		
		return constructor.newInstance(params.toArray());
		
	}

}
