package com.bvk.inventory.repository;


import com.bvk.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    @Query("SELECT a FROM Inventory a WHERE a.id IN :idList")
    List<Inventory> findByIdList(List<Long> idList);
}
