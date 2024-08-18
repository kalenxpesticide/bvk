package com.bvk.inventory.controller;

import com.bvk.inventory.entity.Inventory;
import com.bvk.inventory.service.InventoryService;
import org.bvk.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(value = { "/findByIdList" })
    public ResponseEntity<List<InventoryDto>> find(@RequestBody ListIdDto dto) {
        ResponseRestfulEntity response = inventoryService.findByIdList(dto);
        return ResponseEntity.status(HttpStatus.OK).body((List<InventoryDto>) response.getData());
    }

    @PostMapping(value = { "/add" })
    public ResponseEntity<ResponseRestfulEntity> add(@RequestBody InventoryDto dto) {
        ResponseRestfulEntity response = inventoryService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = { "/restock" })
    public ResponseEntity<ResponseRestfulEntity> restock(@RequestBody RestockDto dto) {
        ResponseRestfulEntity response = inventoryService.restock(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = { "/reduce" })
    public ResponseEntity<Integer> reduce(@RequestBody PurchaseStockDto dto) {
        ResponseRestfulEntity response = inventoryService.reduceStock(dto);
        if(response.isSuccessed()) {
            return ResponseEntity.status(HttpStatus.OK).body(0);
        }
        return ResponseEntity.status(HttpStatus.OK).body(9999);
    }

    @GetMapping("/availability/{id}")
    public ResponseEntity<ResponseRestfulEntity> availability(@PathVariable("id") long id) {
        ResponseRestfulEntity response = inventoryService.availability(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
