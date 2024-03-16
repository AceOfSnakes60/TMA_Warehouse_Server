package com.tma.tma_warehouse_server.Items.Model;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ItemGroup itemGroup;
    private UnitEnum unitOfMeasurement;
    private int quantity;
    private int priceUAH;
    private String status;
    private String storageLocation;
    private String contactPerson;

}
