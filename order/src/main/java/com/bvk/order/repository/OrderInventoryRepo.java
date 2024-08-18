package com.bvk.order.repository;


import com.bvk.order.entity.Order;
import com.bvk.order.entity.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInventoryRepo extends JpaRepository<OrderInventory, Long> {
}
