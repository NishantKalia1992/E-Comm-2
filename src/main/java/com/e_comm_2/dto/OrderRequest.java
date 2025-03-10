package com.e_comm_2.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrderRequest {
	private String customerName;
	private String customerEmail;
	private String restaurantMail;
	private List<String> items;
	private Double totalAmount;
}
