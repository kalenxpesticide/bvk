package com.bvk.order.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders_inventory")
public class OrderInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_inventory")
    private long id;

    @Column(name = "id_order")
    private long orderId;

    @Column(name = "id_inventory")
    private long inventoryId;

    @Column(name = "price")
    private Double price;

    @Column(name = "id_creation")
    private Long idCreation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "id_update")
    private Long idUpdate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getIdCreation() {
        return idCreation;
    }

    public void setIdCreation(Long idCreation) {
        this.idCreation = idCreation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getIdUpdate() {
        return idUpdate;
    }

    public void setIdUpdate(Long idUpdate) {
        this.idUpdate = idUpdate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}

