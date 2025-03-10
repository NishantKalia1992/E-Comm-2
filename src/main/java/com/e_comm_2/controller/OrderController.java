package com.e_comm_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_comm_2.dto.OrderRequest;
import com.e_comm_2.dto.OrderResponse;
import com.e_comm_2.entities.Order;
import com.e_comm_2.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/make")
	public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request){
		Order order = orderService.placeOrder(request);
		return ResponseEntity.ok(new OrderResponse(
	            order.getId(),
	            "Order received! Confirmation emails are being sent.",
	            order.getOrderTime()
	        ));
	}
}
