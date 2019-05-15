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
public class Order {
    private SimpleStringProperty id, date, staffId, table, price;

    public SimpleStringProperty getId() {
        return id;
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public SimpleStringProperty getDate() {
        return date;
    }

    public void setDate(SimpleStringProperty date) {
        this.date = date;
    }

    public SimpleStringProperty getStaffId() {
        return staffId;
    }

    public void setStaffId(SimpleStringProperty staffId) {
        this.staffId = staffId;
    }

    public SimpleStringProperty getTable() {
        return table;
    }

    public void setTable(SimpleStringProperty table) {
        this.table = table;
    }

    public SimpleStringProperty getPrice() {
        return price;
    }

    public void setPrice(SimpleStringProperty price) {
        this.price = price;
    }
    
    public Order(String i , String d , String s, String t, String p){
        this.id = new SimpleStringProperty(i);
        this.date = new SimpleStringProperty(d);
        this.staffId = new SimpleStringProperty(s);
        this.table = new SimpleStringProperty(t);
        this.price = new SimpleStringProperty(p);
    }
    
    public Order(){
        this(null, null, null, null, null);
    }
}
