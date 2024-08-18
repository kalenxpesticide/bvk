package org.bvk.response;

import java.util.ArrayList;
import java.util.List;

public class PurchaseStockDto {

    private List<InventoryDto> checkout = new ArrayList<>();

    public List<InventoryDto> getCheckout() {
        return checkout;
    }

    public void setCheckout(List<InventoryDto> checkout) {
        this.checkout = checkout;
    }
}
