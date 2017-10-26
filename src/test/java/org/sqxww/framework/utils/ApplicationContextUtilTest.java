package org.sqxww.framework.utils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * @author lizhiwei
 * @date 2017年7月24日
 */
public class ApplicationContextUtilTest {
	
	private ApplicationContext context;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("conf/spring/applicationContext-*.xml");
	}
	
	@Test
	public void getContextTest(){
		System.out.println(ApplicationContextUtil.getApplicationContext().equals(context));
	}

}
