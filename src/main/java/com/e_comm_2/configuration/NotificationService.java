package com.e_comm_2.configuration;

import java.io.File;

import org.eclipse.angus.mail.iap.ByteArray;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.e_comm_2.entities.Order;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotificationService {
	
	private final JavaMailSender mailSender;
	
	@Async
	public void sendCustomeNotification(Order order) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(order.getCustomerEmail());
			message.setSubject("order confirmation "+order.getId());
			message.setText("Dear "+order.getCustomerName()+
					"\n\n"+"your order for "+order.getItems()+
					"\n\n"+"Total : "+order.getTotalAmount()+
					"\n\n"+"is being processed");
			mailSender.send(message);
			System.out.println("Customer email send for order "+order.getId());
		}catch (Exception e) {
			System.err.println("failed to send message"+e.getMessage());
		}
	}
	
	@Async
	public void sendCustomerNotificationWithAttachment(Order order) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setTo(order.getCustomerEmail());
			helper.setSubject("Order confirmation order "+order.getId());
			helper.setText("Dear "+order.getCustomerName()+
					"\n\n"+"Your Order for "+order.getItems()+
					"\n\n"+"Total"+order.getTotalAmount()+
					"\n\n"+"is being processed ");
			String invoiceContent = generateInvoice(order);
			helper.addAttachment("invoice_"+order.getId()+".txt", new ByteArrayResource(invoiceContent.getBytes()));
			
			FileSystemResource file = new FileSystemResource(new File("path/to/invoice.pdf"));
			helper.addAttachment("Invoice.pdf", file);
			
			mailSender.send(message);			
			System.out.println("Customer email send for ordeer "+order.getId());
		}catch (Exception e) {
			System.out.println("failed tp send message"+e.getMessage());
		}
	}
	private String generateInvoice(Order order) {
        return "Invoice for Order #" + order.getId() + "\n"
                + "Customer: " + order.getCustomerName() + "\n"
                + "Items: " + order.getItems() + "\n"
                + "Total: $" + order.getTotalAmount() + "\n"
                + "Date: " + order.getOrderTime();
    }
	
	@Async
	public void notifyRestaurant(Order order) {
		try {
			System.out.println("Notifying Restaurant "+order.getRestaurantMail());
			System.out.println("Order Details "+order.getId());
			Thread.sleep(1000);
			System.out.println("Restaurant notify successfully order "+order.getId());
		}catch (Exception e) {
			Thread.currentThread().interrupt();
		}			
	}
	
}
