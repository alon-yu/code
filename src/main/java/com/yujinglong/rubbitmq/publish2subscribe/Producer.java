package com.yujinglong.rubbitmq.publish2subscribe;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
public class Producer { 
	private static final String EXCHANGE_NAME = "logs"; 
	
	public static void main(String[] argv) throws Exception {  
		ConnectionFactory factory = new ConnectionFactory();  
		factory.setHost("localhost"); 
		Connection connection = factory.newConnection();  
		Channel channel = connection.createChannel();  
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");  
		String message = getMessage(argv);
		for (int i = 0; i < 1000; i++) {
			String mess = message+"-"+i;
			channel.basicPublish(EXCHANGE_NAME, "test", null, mess.getBytes());  
		}
		System.out.println(" [x] Sent '" + message + "'");  
		channel.close();  
		connection.close(); 
	}  
	private static String getMessage(String[] strings){  
		if (strings.length < 1)     
			return "info: Hello World!";  
		return joinStrings(strings, " "); 
	}  
	private static String joinStrings(String[] strings, String delimiter) {  
		int length = strings.length;  
		if (length == 0) 
			return "";  
		StringBuilder words = new StringBuilder(strings[0]);  
		for (int i = 1; i < length; i++) {    
			words.append(delimiter).append(strings[i]);  
		} 
		return words.toString(); 
	}
}
