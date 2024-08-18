package com.bvk.order.controller;

import com.bvk.order.service.OrderService;
import org.bvk.response.PurchaseStockDto;
import org.bvk.response.ResponseRestfulEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/find/all")
    public ResponseEntity<ResponseRestfulEntity> findAll() {
        ResponseRestfulEntity response = orderService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = { "/purchases" })
    public ResponseEntity<ResponseRestfulEntity> checkout(@RequestBody PurchaseStockDto orderDto) {
        orderService.purchases(orderDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseRestfulEntity(0, "Success", null, true));
    }

}
