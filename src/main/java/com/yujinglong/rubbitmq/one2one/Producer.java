package com.yujinglong.rubbitmq.one2one;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Producer {   
	private final static String QUEUE_NAME = "hello"; 
	public static void main(String[] argv) throws Exception {          
		ConnectionFactory factory = new ConnectionFactory();  
		factory.setHost("localhost");  
		Connection connection = factory.newConnection(); 
		Channel channel = connection.createChannel();  
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
		for (int i = 0; i < 10000; i++) {
			String message = "Hello World!-"+i;  
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());  
		}
		channel.close();  
		connection.close(); 
	}
}
