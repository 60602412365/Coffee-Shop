/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author PC
 */


public class Account {
    private String id, userName, passWord, name, phone, email, address ;
    private java.sql.Date birth_day;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(Date birth_day) {
        this.birth_day = birth_day;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Account(String i, String username, String pass, String ten, Date birth, String add, String em, String pho ){
        this.id = i;
        this.userName = username;
        this.passWord = pass;
        this.name = ten;
        this.birth_day = birth;
        this.address = add;
        this.email = em;
        this.phone = pho;
    }
    
    public Vector toVector()
    {
        Vector v = new Vector();
        v.add(this.id);
        v.add(this.userName);
        v.add(this.passWord);
        v.add(this.name);
        v.add(this.birth_day);
        v.add(this.address);
        v.add(this.email);
        v.add(this.phone);
        
        return v;
    }
    
    public Account(){
        
    }
            
}
