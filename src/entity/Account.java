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
public class Account {
    private SimpleStringProperty id, userName, passWord, name;

    public SimpleStringProperty getId() {
        return id;
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
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

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }
    
    public Account(String i, String username, String pass, String ten){
        this.id = new SimpleStringProperty(i);
        this.userName = new SimpleStringProperty(username);
        this.passWord = new SimpleStringProperty(pass);
        this.name = new SimpleStringProperty(ten);
    }
    
    public Account(){
        this(null, null, null, null);
    }
            
}
