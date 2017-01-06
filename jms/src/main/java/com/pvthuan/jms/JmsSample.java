package com.pvthuan.jms;

import org.springframework.context.support.GenericXmlApplicationContext;

public class JmsSample {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/jms-sender-app-context.xml",
				"classpath:META-INF/spring/jms-listener-app-context.xml");
		ctx.refresh();

		MessageSender messageSender = ctx.getBean("messageSender", MessageSender.class);

		for (int i = 0; i < 10; i++) {
			messageSender.sendMessage("Test message: " + i);
		}
	}
}