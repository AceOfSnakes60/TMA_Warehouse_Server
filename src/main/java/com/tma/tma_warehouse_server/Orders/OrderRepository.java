package com.tma.tma_warehouse_server.Orders;

import com.tma.tma_warehouse_server.Items.Model.Item;
import com.tma.tma_warehouse_server.Orders.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
