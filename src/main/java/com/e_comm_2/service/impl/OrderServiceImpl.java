package com.e_comm_2.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_comm_2.configuration.NotificationService;
import com.e_comm_2.dto.OrderRequest;
import com.e_comm_2.entities.Order;
import com.e_comm_2.repository.OrderRepository;
import com.e_comm_2.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private NotificationService notificationService;

	@Override
	public Order addCustomer(OrderRequest orderRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrder(OrderRequest LongId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Order placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setCustomerName(orderRequest.getCustomerName());
		order.setCustomerEmail(orderRequest.getCustomerEmail());
		order.setRestaurantMail(orderRequest.getRestaurantMail());
		order.setItems(String.join(",", orderRequest.getItems()));
		order.setOrderTime(LocalDateTime.now());
		order.setTotalAmount(orderRequest.getTotalAmount());
		
		Order save = orderRepository.save(order);
//		notificationService.sendCustomeNotification(save);
		notificationService.sendCustomerNotificationWithAttachment(order);
		notificationService.notifyRestaurant(save);
		
		return save;
	}

}
