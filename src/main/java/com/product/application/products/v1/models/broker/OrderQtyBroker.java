package com.product.application.products.v1.models.broker;

public class OrderQtyBroker {

    private Long id;
    
    private Long qty;


    public OrderQtyBroker() {
    }

    public OrderQtyBroker(Long id, Long qty) {
        this.id = id;
        this.qty = qty;
    }


    public Long getId() {
        return id;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
