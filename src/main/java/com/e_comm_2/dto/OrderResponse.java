package com.e_comm_2.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
	private Long orderId;
	private String message;
	private LocalDateTime orderTime;
}
