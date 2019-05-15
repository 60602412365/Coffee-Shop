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
public class Admin implements java.io.Serializable{
    private String adId, userName, passWord;

  public Vector toVector()
    {
        Vector v = new Vector();
        v.add(adId);
        v.add(userName);
        v.add(passWord);
        
        return v;
    }

    public String getAdId() {
        return adId;
    }

    public void setAd_id(String ad_id) {
        this.adId = ad_id;
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
    
    public Admin(String id, String username, String pass) {
        this.adId = id;
        this.userName = username;
        this.passWord = pass;
    }
    
    public Admin(){
        this(null, null, null);
    }
    
}
