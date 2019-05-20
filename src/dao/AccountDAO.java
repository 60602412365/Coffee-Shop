/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.DBConnection;
import entity.Account;
import entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class AccountDAO {
    
//SELF EDITING
      
    public static Account check(String username, String pass)
    {
        
        Account empresult = null;
        String sql = "SELECT * FROM Account WHERE username = ? AND pass = ?";
        
        try(Connection cn = new DBConnection().getCon();
                PreparedStatement st = cn.prepareStatement(sql);){
        
            st.setString(1, username);
            st.setString(2, pass);
            
            try(ResultSet rs = st.executeQuery();){
                if(rs.next()){
                    empresult = new Account();
                    empresult.setId(rs.getString(1));
                    empresult.setUserName(rs.getString(2));
                    empresult.setPassWord(rs.getString(3));
                    empresult.setName(rs.getString(4));
                    return empresult;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return empresult;
    }
     
      public static int changePass(Account old_emp, String newpass)
    {
        String sql = "UPDATE tbEmployee SET pass = ? WHERE em_id = ?, username = ?, pass = ?";
        
        try(Connection cn = new DBConnection().getCon();
                PreparedStatement st = cn.prepareStatement(sql);){
            
            st.setString(1, newpass);
            st.setString(2, old_emp.getId());
            st.setString(3, old_emp.getUserName());
            st.setString(4, old_emp.getPassWord());
            
            return st.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
   
    
}
