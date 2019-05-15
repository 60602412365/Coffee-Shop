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
public class Product {
    private SimpleStringProperty id, name, price, categoryId;

    public SimpleStringProperty getId() {
        return id;
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public SimpleStringProperty getPrice() {
        return price;
    }

    public void setPrice(SimpleStringProperty price) {
        this.price = price;
    }

    public SimpleStringProperty getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(SimpleStringProperty categoryId) {
        this.categoryId = categoryId;
    }
    
    public Product(String i, String n, String p, String c){
        this.id = new SimpleStringProperty(i);
        this.name = new SimpleStringProperty(n);
        this.price = new SimpleStringProperty(p);
        this.categoryId = new SimpleStringProperty(c);
    }
    
    public Product(){
        this(null, null, null, null);
    }
}
