/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class Order {
    private String id, accountid;
    private java.sql.Date ordertime;
    private float price, customerpay, payback;

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public float getCustomerpay() {
        return customerpay;
    }

    public void setCustomerpay(float customerpay) {
        this.customerpay = customerpay;
    }

    public float getPayback() {
        return payback;
    }

    public void setPayback(float payback) {
        this.payback = payback;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return ordertime;
    }

    public void setDate(Date date) {
        this.ordertime = date;
    }

    public String getAccountId() {
        return accountid;
    }

    public void setAccountId(String account) {
        this.accountid = account;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public Order(String i , Date d , String a, float p , float cb, float pb ){
        this.id = i;
        this.ordertime = d;
        this.accountid = a;
        this.price = p;
        this.customerpay = cb;
        this.payback = pb;
    }
    
     public Vector toVector(){
        Vector v = new Vector();
        v.add(this.id);
        v.add(this.ordertime);
        v.add(this.accountid);
        v.add(this.price);
        v.add(this.customerpay);
        v.add(this.payback);
        
        return v;
    }
     
    public Order() {
    }
    
}
