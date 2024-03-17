package com.tma.tma_warehouse_server.Orders.Model;

import com.tma.tma_warehouse_server.Enums.StatusEnum;
import com.tma.tma_warehouse_server.Items.Model.UnitEnum;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String employeeName;        //Required
    private Long itemId;                //Required
    private UnitEnum unitOfMeasurement; //Required
    private int quantity;               //Required
    private int priceUAH;               //Required
    private String comment;
    private StatusEnum status;
    // Request row ID
}
