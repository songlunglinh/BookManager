package com.pvthuan.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SimpleMessageListener implements MessageListener {
    //private static final Logger logger = LoggerFactory.getLogger(SimpleMessageListener.class);

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
        	System.out.println(textMessage.getText());
           // logger.info("Message received: " + textMessage.getText());
        } catch (JMSException ex) {
         //   logger.error("JMS error", ex);
        }
    }
}