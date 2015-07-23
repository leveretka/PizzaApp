package com.mycompany.infrastructure;

import java.lang.reflect.InvocationTargetException;

public interface ApplicationContext {
	
	public Object getBean(String beanName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
}
