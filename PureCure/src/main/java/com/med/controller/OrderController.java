package com.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.med.exception.CartException;
import com.med.exception.CustomerException;
import com.med.model.Orders;
import com.med.serviceinetrface.OrderInterface;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderInterface orderService; // Assuming you have a service class for orders

    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrderForCustomer(@RequestParam("customerId") int customerId) {
        try {
            Orders placedOrder = orderService.placeOrderForCustomer(customerId);
            return ResponseEntity.ok("Order placed successfully. Order ID: " + placedOrder.getOrderId());
        } catch (CustomerException e) {
            return ResponseEntity.badRequest().body("Error placing order: " + e.getMessage());
        } catch (CartException e) {
            return ResponseEntity.badRequest().body("Error placing order: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
}
