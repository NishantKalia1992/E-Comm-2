package com.e_comm_2.service;

import com.e_comm_2.dto.OrderRequest;
import com.e_comm_2.entities.Order;

public interface OrderService {
	 Order addCustomer(OrderRequest orderRequest);
	 Order getOrder(OrderRequest LongId);
	 Order placeOrder(OrderRequest orderRequest);
}
