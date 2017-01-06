package com.pvthuan.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvthuan.jms.model.Student;

@Component("messageSender")
public class SimpleMessageSender implements MessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    ObjectMapper mapper = new ObjectMapper();
    @Override
    public void sendMessage(final String message) {
    	
        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
    
    public String message() {
    	String msg = "";
		try {
			msg = mapper.writeValueAsString(new Student(1,"Thuan Pham",24));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return msg;
    }
}
