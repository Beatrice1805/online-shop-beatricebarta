package org.fastrackit.online.shop.transfer.cart;

import java.util.List;

public class AddProductsToCartRequest {
    private long customerId;
    private List<Long> productIds;
    public long getCustomerId(){
        return customerId;
    }
    public void setCustomerId(long customerId){
        this.customerId = customerId;
    }
    public List<Long> getProductIds(){
        return productIds;
    }
}
