package org.sqxww.framework.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.sqxww.framework.pojo.Field;

/*
 * @author lizhiwei
 * @date 2017年7月24日
 */
public class DynamicRegisterTest {
	
	private ApplicationContext context;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("conf/spring/applicationContext-*.xml");
	}
	
	@Test
	public void registerTest(){
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("fieldName", "helloRegister");
		properties.put("sqlType", "string");
		DynamicRegister.registerBean(Field.class, properties, "helloField");
		System.out.println(((Field)context.getBean("helloField")).getFieldName());
		properties.put("fieldName", "dlfsdf");
		DynamicRegister.registerBean(Field.class, properties, "helloField");
		System.out.println(((Field)context.getBean("helloField")).getFieldName());
	}

}
