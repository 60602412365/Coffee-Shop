/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.DBConnection;
import entity.Order;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class OrderDAO {
    
      public static List<Order> getList()
    {
        List<Order> ds = new ArrayList<>();
        String sql = "SELECT * FROM tbOrder";
                
        try(Connection cn = new DBConnection().getCon();
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet rs = st.executeQuery())
        {
            while(rs.next()){
                Order newitem = new Order();
            
                
                newitem.setId(rs.getString(1));
                newitem.setAccountId(rs.getString(2));
                newitem.setOrdertime(rs.getDate(3));
                newitem.setPrice(rs.getInt(4));
                newitem.setCustomerpay(rs.getInt(5));
                newitem.setPayback(rs.getInt(6));
                ds.add(newitem);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return ds;
    }
    
}
