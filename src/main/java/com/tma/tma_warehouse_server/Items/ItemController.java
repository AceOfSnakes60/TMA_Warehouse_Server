package com.tma.tma_warehouse_server.Items;


import com.tma.tma_warehouse_server.Items.Model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping()
    public ResponseEntity<?> getAllItems(){

        List<Item> results = itemRepository.findAll();
        if(results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(results);
    }


    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody Item newItem){
        HttpStatus status = HttpStatus.OK;
        StringBuilder responseBody = new StringBuilder();

        if(newItem.getItemGroup()==null){
            status = HttpStatus.BAD_REQUEST;
            responseBody.append("Item group cannot be empty\n");
        }
        if(newItem.getUnitOfMeasurement()==null){
            status = HttpStatus.BAD_REQUEST;
            responseBody.append("Unit of Measurement cannot be empty\n");

        }
        if(newItem.getQuantity()<=0){
            System.out.println(newItem.getQuantity());
            status = HttpStatus.BAD_REQUEST;
            responseBody.append("Quantity cannot be empty\n");

        }
        if(newItem.getPriceUAH()==-1){
            status = HttpStatus.BAD_REQUEST;
            responseBody.append("Price UAH cannot be empty\n");

        }

        if(status.is2xxSuccessful()){
            itemRepository.save(newItem);
        }


        return ResponseEntity.status(status).body(responseBody.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeItem(@PathVariable Long id){
        System.out.println(id);
        if (id == null) {
            return ResponseEntity.badRequest().body("No item IDs provided");
        }

        System.out.println("size: " + id);

        itemRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
