package app.pizza.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.pizza.persistence.entity.OrderEntity;
import app.pizza.persistence.projection.OrderSummary;
import app.pizza.services.OrderService;
import app.pizza.services.dto.RandomOrderDto;
@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok().body(this.orderService.getAll());
    }
    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getByDate(){
        return ResponseEntity.ok().body(this.orderService.getByDate());
    }
    @GetMapping("/customer-orders/{idCustomer}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String idCustomer){
        try {
            return ResponseEntity.ok().body(this.orderService.getCustomerOrders(idCustomer));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }
    @GetMapping("/summary/{orderId}")
    public ResponseEntity<OrderSummary> getOrderSummary(@PathVariable int orderId){
        try {
            return ResponseEntity.ok().body(this.orderService.getOrderSummary(orderId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }
    @PostMapping("/ramdon")
    public ResponseEntity<Boolean> createRamdonOrder(RandomOrderDto randomOrderDto){
        try {
            return ResponseEntity.ok().body(this.orderService.createRamdonOrder(randomOrderDto));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().build();
        }
        
    }
}
