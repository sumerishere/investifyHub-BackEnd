//package com.Investify.mail;
//
//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
//import java.util.Properties;
//import jakarta.mail.Message;
//import jakarta.mail.Session;
//import jakarta.mail.Transport;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//
//public class MailSender {
//	
//	public static void main(String[] args) {
//			
//		String name = "";
//		
//		System.out.println("preparing to send message ...");
//		
//		String message = "Hii,"+name;
//		String subject = "";
//		String to = "";
//		String from = "";
//			
//	//		sendEmail(message,subject,to,from);
//			sendMail(message,subject,to,from);
//	}
//
//	private static void sendMail(String message, String subject, String to, String from) {
//		
//		String host = "smtp.gmail.com";
//		
//		//get system properties 
//		Properties properties = System.getProperties();
//		System.out.println("Properties"+properties);
//		
//		//setting important information to properties object 
//		
//		//host set
//		properties.put("mail.smtp.host",host);
//		properties.put("mail.smtp.port","587"); 
//	    properties.put("mail.smtp.ssl.enable", "true");
//	    properties.put("mail.smtp.auth", "true");
//	    
//	    //step 1: to get the session object
//	    
//	    Session session = Session.getInstance(properties, new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				// TODO Auto-generated method stub
//				return new PasswordAuthentication("investifyhub.in@gmail.com","investifyHub#2024");
//			}
//	    	
//	    });
//		
//	    session.setDebug(true);
//	    
//		//Step 2 : compose the message [text,multi media]
//		MimeMessage m = new MimeMessage(session);
//		
//		try {
//		
//		//from email
//		m.setFrom(from);
//		
//		//adding recipient to message
//		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//		
//		//adding subject to message
//		m.setSubject(subject);
//	
//		
//		//adding text to message
//		m.setText(message);
//		
//		//send 
//		
//		//Step 3 : send the message using Transport class
//		Transport.send(m);
//		
//		System.out.println("Sent success...................");
//		
//		
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
//
//
//
//
//
//
//
//
//
