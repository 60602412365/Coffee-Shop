/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class OrderDetail {
    private SimpleStringProperty id, orderId, productId, quantity;

    public SimpleStringProperty getId() {
        return id;
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public SimpleStringProperty getOrderId() {
        return orderId;
    }

    public void setOrderId(SimpleStringProperty orderId) {
        this.orderId = orderId;
    }

    public SimpleStringProperty getProductId() {
        return productId;
    }

    public void setProductId(SimpleStringProperty productId) {
        this.productId = productId;
    }

    public SimpleStringProperty getQuantity() {
        return quantity;
    }

    public void setQuantity(SimpleStringProperty quantity) {
        this.quantity = quantity;
    }
    
    public OrderDetail(String i, String o, String p, String q){
        this.id = new SimpleStringProperty(i);
        this.orderId = new SimpleStringProperty(o);
        this.productId = new SimpleStringProperty(p);
        this.quantity = new SimpleStringProperty(q);
    }
    
    public OrderDetail(){
        this(null, null, null, null);
    }
    
}
