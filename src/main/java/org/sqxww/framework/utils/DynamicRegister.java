package org.sqxww.framework.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/* 动态注册bean工具类
 * @author lizhiwei
 * @date 2017年7月21日
 */

public class DynamicRegister {
	
	private static final Logger logger = LogManager.getLogger();
	
	/**
	 * 动态注入bean属性并注册
	 * @param beanClass
	 * @param properties
	 * @param beanId
	 */
	public static <T> void registerBean(Class<T> beanClass, Map<String, Object> properties, String beanId) {
		logger.info("开始注册类为 " + beanClass.getName() + "的bean, beanId 为:" + beanId);
		DefaultListableBeanFactory beanFactory = ApplicationContextUtil.getDefaultListableBeanFactory();
		//构建bean定义器
		BeanDefinitionBuilder beanDefinitionBuilder = 
				BeanDefinitionBuilder.genericBeanDefinition(beanClass);
		//注入bean属性
		if(properties != null)
			for(Entry<String, Object> property : properties.entrySet()){
				beanDefinitionBuilder.addPropertyValue(property.getKey(), property.getValue());
			}
		//beanId为空时默认使用类名首字母小写
		if(beanId == null || "".equals(beanId)){
			String className = beanClass.getSimpleName();
			beanId = className.substring(0,1).toLowerCase() 
					+ className.substring(1,className.length());
		}
		if(ApplicationContextUtil.getApplicationContext().containsBean(beanId))
			logger.warn("id 为 " + beanId + "的bean,已存在,此bean的注册将覆盖先前的bean");
		//注册bean
		beanFactory.registerBeanDefinition(beanId, beanDefinitionBuilder.getBeanDefinition());
	}
}
