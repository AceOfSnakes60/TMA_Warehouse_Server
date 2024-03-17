package com.tma.tma_warehouse_server.Orders;

import com.tma.tma_warehouse_server.Enums.StatusEnum;
import com.tma.tma_warehouse_server.Items.ItemRepository;
import com.tma.tma_warehouse_server.Items.Model.Item;
import com.tma.tma_warehouse_server.Orders.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists/orders")
public class OrdersController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;


    @GetMapping()
    public ResponseEntity<?> getAllOrders(){
        List<Order> results = orderRepository.findAll();
        if(results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }

    @PostMapping()
    public ResponseEntity<?> addOrder(@RequestBody Order newOrder){

        HttpStatus status = HttpStatus.OK;
        StringBuilder responseBody = new StringBuilder();
        if(newOrder.getEmployeeName().isEmpty()){
            status = HttpStatus.BAD_REQUEST;
            responseBody.append("Employee Name cannot be empty\n");
        }
        if(newOrder.getItemId()==null){
            status = HttpStatus.BAD_REQUEST;
            responseBody.append("Item Id cannot be empty\n");

        }
        if(newOrder.getUnitOfMeasurement()==null){
            status = HttpStatus.BAD_REQUEST;
            responseBody.append("IUnit of measurement cannot be empty\n");
        }
        if(newOrder.getQuantity()<=0){
            status = HttpStatus.BAD_REQUEST;
            responseBody.append("Quantity cannot be empty\n");

        }
        if(newOrder.getPriceUAH()==-1){
            status = HttpStatus.BAD_REQUEST;
            responseBody.append("Price UAH cannot be empty\n");

        }

        if(status.is2xxSuccessful()){
            newOrder.setStatus(StatusEnum.NEW);
            orderRepository.save(newOrder);
        }


        return ResponseEntity.status(status).body(responseBody.toString());
    }

    @DeleteMapping()
    public ResponseEntity<?> removeOrder(@RequestParam Long id){

        orderRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }
    @PostMapping("/accept")
    public ResponseEntity<?> confirmOrder(@RequestBody Order order){

        Optional<Item> foundItem = itemRepository.findById(order.getItemId());
        if(foundItem.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Item toConfirmItem = foundItem.get();
        if(toConfirmItem.getQuantity()<order.getQuantity()){
            return ResponseEntity.badRequest().build();
        }

        toConfirmItem.setQuantity(toConfirmItem.getQuantity() - order.getQuantity());
        itemRepository.save(toConfirmItem);
        order.setStatus(StatusEnum.APPROVED);
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reject")
    public ResponseEntity<?> rejectOrder(@RequestBody Order order){
        order.setStatus(StatusEnum.REJECTED);

        orderRepository.save(order);

        return ResponseEntity.ok().build();
    }

}
