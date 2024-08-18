package org.bvk.response;

public class RestockDto {

    private long id;
    private int addStock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAddStock() {
        return addStock;
    }

    public void setAddStock(int addStock) {
        this.addStock = addStock;
    }
}
