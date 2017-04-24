package com.haocs.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 说明：
 * 
 * @author xajava
 * @version 创建时间：2012-10-24 下午2:06:48
 */
public class Consumer {


	private static final String SUBJECT = "FirstQueue";

	// 初始化

	 public static void main(String[] args) throws JMSException {

         // Create a ConnectionFactory  
         ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");  

         // Create a Connection  
         Connection connection = connectionFactory.createConnection();  
         connection.start();  


         // Create a Session  
         Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  

         // Create the destination (Topic or Queue)  
         Destination destination = session.createQueue(SUBJECT);  

         // Create a MessageConsumer from the Session to the Topic or Queue  
         MessageConsumer consumer = session.createConsumer(destination);  

         // Wait for a message  
         Message message = consumer.receive(10000);  
         for (int i = 1; i <= 3; i++) {
         if (message instanceof TextMessage) {  
             TextMessage textMessage = (TextMessage) message;  
             String text = textMessage.getText();  
             System.out.println("Received: " + text);  
         } else {  
             System.out.println("Received: " + message);  
         }  
         }
         consumer.close();  
         session.close();  
         connection.close(); 
	}

}