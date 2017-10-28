package org.sqxww.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/* 获取spring ApplicationContext的工具类
 * @author lizhiwei
 * @date 2017年7月21日
 */

public class ApplicationContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;

	/**
	 * 实现了ApplicationContextAware,则spring在实例化此bean后便会调用此方法
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
	/**
	 * 返回Spring的ApplicationContext
	 * @return {@link ApplicationContext}
	 */
	public static ApplicationContext getApplicationContext() {
		return context;
	}
	
	/**
	 * 根据名字获取spring容器中的bean
	 * @param beanName
	 * @return {@link Object}
	 */
	public static Object getBeanByname(String beanName) {
		return context.getBean(beanName);
	}
	
	/**
	 * 根据类型获取spring容器中的bean
	 * @param clazz
	 * @return T
	 */
	public static <T> T getBeanByClass(Class<T> clazz) {
		return context.getBean(clazz);
	}
	
	/**
	 * 获取DefaultListableBeanFactory
	 * @return {@link DefaultListableBeanFactory}
	 */
	public static DefaultListableBeanFactory getDefaultListableBeanFactory() {
		//将applicationContext转换为ConfigurableApplicationContext 
		ConfigurableApplicationContext configurableApplicationContext = 
				(ConfigurableApplicationContext) context;
		//获取bean工厂并转换为DefaultListableBeanFactory
		return (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
	}

}
