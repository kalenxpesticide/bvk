package com.bvk.inventory.service;

import com.bvk.inventory.entity.Inventory;
import com.bvk.inventory.repository.InventoryRepo;
import org.bvk.enumeration.ResponseCode;
import org.bvk.response.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper mapper;

    public ResponseRestfulEntity findByIdList(ListIdDto dto) {
        List<Inventory> inventoryList = inventoryRepo.findByIdList(dto.getIdList());
        if(CollectionUtils.isEmpty(inventoryList)) {
            return new ResponseRestfulEntity(ResponseCode.PRODUCT_NOT_FOUND, null, false);
        }
        List<InventoryDto> list = new ArrayList<>();
        inventoryList.forEach(x -> {
            list.add(mapper.map(x, InventoryDto.class));
        });
        return new ResponseRestfulEntity(ResponseCode.SUCCESS, list, true);
    }

    public ResponseRestfulEntity availability(long id) {
        Optional<Inventory> inventory = inventoryRepo.findById(id);
        return inventory.map(value -> new ResponseRestfulEntity(ResponseCode.SUCCESS, value.getQuantity(), true)).orElseGet(() -> new ResponseRestfulEntity(ResponseCode.PRODUCT_NOT_FOUND, null, false));
    }

    public ResponseRestfulEntity save(InventoryDto dto) {
        Inventory inventory = mapper.map(dto, Inventory.class);
        inventory.setIdCreation(100L);
        inventory.setDateCreation(new Date());
        try {
            inventoryRepo.save(inventory);
            return new ResponseRestfulEntity(ResponseCode.SUCCESS, inventory.getId(), true);
        } catch (Exception e) {
            return new ResponseRestfulEntity(ResponseCode.INTERNAL_SYSTEM_ERROR, true);
        }
    }

    public ResponseRestfulEntity reduceStock(PurchaseStockDto dto) {
        List<Inventory> inventoryList = new ArrayList<>();
        for(InventoryDto o : dto.getCheckout()) {
            Optional<Inventory> inventory = inventoryRepo.findById(o.getId());
            if(inventory.isEmpty()) {
                return new ResponseRestfulEntity(ResponseCode.PRODUCT_NOT_FOUND, null, false);
            } else {
                if (inventory.get().getQuantity() <= 0) {
                    return new ResponseRestfulEntity(ResponseCode.EMPTY_STOCK, null, false);
                } else if (inventory.get().getQuantity() < o.getQuantity()) {
                    return new ResponseRestfulEntity(ResponseCode.INSUFICIENT_STOCK, null, false);
                }
                Inventory newInventory = inventory.get();
                newInventory.setQuantity(newInventory.getQuantity() - o.getQuantity());
                inventoryList.add(newInventory);
            }
        }
        if(CollectionUtils.isEmpty(inventoryList)) {
            return new ResponseRestfulEntity(ResponseCode.PRODUCT_NOT_FOUND, null, false);
        }

        for(Inventory o : inventoryList) {
            try {
                o.setIdUpdate(100L);
                o.setDateUpdate(new Date());
                inventoryRepo.save(o);
            } catch (Exception e) {
                return new ResponseRestfulEntity(ResponseCode.INTERNAL_SYSTEM_ERROR, null, false);
            }
        }
        return new ResponseRestfulEntity(ResponseCode.SUCCESS, true);
    }

    public ResponseRestfulEntity restock(RestockDto dto) {
        Optional<Inventory> optional = inventoryRepo.findById(dto.getId());
        if(optional.isEmpty()) {
            return new ResponseRestfulEntity(ResponseCode.PRODUCT_NOT_FOUND, null, false);
        }
        Inventory inventory = optional.get();
        inventory.setQuantity(inventory.getQuantity() + dto.getAddStock());
        inventory.setIdUpdate(100L);
        inventory.setDateUpdate(new Date());
        try {
            inventoryRepo.save(inventory);
            return new ResponseRestfulEntity(ResponseCode.SUCCESS, true);
        } catch (Exception e) {
            return new ResponseRestfulEntity(ResponseCode.INTERNAL_SYSTEM_ERROR, true);
        }
    }
}
