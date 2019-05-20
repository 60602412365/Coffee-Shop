/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class OrderDetail {
    private String orderId, productId ;
    private int quantity;
    

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public OrderDetail( String o, String p, int q){
        this.orderId = o;
        this.productId = p;
        this.quantity = q;
    }
    
    public Vector toVector(){
        Vector v = new Vector();
        v.add(this.orderId);
        v.add(this.productId);
        v.add(this.quantity);
        
        return v;
    }
}
