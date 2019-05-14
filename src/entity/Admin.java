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
public class Admin {
    private SimpleStringProperty adId, userName, passWord;

    public SimpleStringProperty getAdId() {
        return adId;
    }

    public void setAd_id(SimpleStringProperty ad_id) {
        this.adId = ad_id;
    }

    public SimpleStringProperty getUserName() {
        return userName;
    }

    public void setUserName(SimpleStringProperty userName) {
        this.userName = userName;
    }

    public SimpleStringProperty getPassWord() {
        return passWord;
    }

    public void setPassWord(SimpleStringProperty passWord) {
        this.passWord = passWord;
    }
    
    public Admin(String id, String username, String pass) {
        this.adId = new SimpleStringProperty(id);
        this.userName = new SimpleStringProperty(username);
        this.passWord = new SimpleStringProperty(pass);
    }
    
    public Admin(){
        this(null, null, null);
    }
    
}
