package com.e_comm_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_comm_2.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
