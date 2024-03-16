package com.tma.tma_warehouse_server.Items;

import com.tma.tma_warehouse_server.Items.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
