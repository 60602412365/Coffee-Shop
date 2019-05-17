/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import AlertMaker.AlertMaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField jtf_userName;
    @FXML
    private JFXPasswordField jpf_passWord;
    @FXML
    private JFXButton btn_cancel;
    
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private JFXButton btn_Login;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con = connection.DBConnection.getCon();
        
    }    
    
    private String getUsernameAdmin(){
         String username="";
        try {
            pst = con.prepareStatement("Select username from admin where username = ?");
            pst.setString(1, jtf_userName.getText());
            rs = pst.executeQuery();
            if(rs.next())
                username = rs.getString(1);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return username;
    }
     private String getPasswordAdmin(){
         String password="";
        try {
           
            pst = con.prepareStatement("Select pass from admin where username = ?");
            pst.setString(1, jtf_userName.getText());
            rs = pst.executeQuery();
            if(rs.next())
                password = rs.getString(1);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return password;
    }
     
     private String getUsernameAccount(){
         String username="";
        try {
            pst = con.prepareStatement("Select username from Account where username = ?");
            pst.setString(1, jtf_userName.getText());
            rs = pst.executeQuery();
            if(rs.next())
                username = rs.getString(1);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return username;
    }
     private String getPasswordAccount(){
         String password="";
        try {
           
            pst = con.prepareStatement("Select pass from Account where username = ?");
            pst.setString(1, jtf_userName.getText());
            rs = pst.executeQuery();
            if(rs.next())
                password = rs.getString(1);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return password;
    }


    @FXML
    private void _cancel(ActionEvent event) {
    }

    @FXML
    private void _login(ActionEvent event) throws IOException {
        if(jtf_userName.getText().equals(getUsernameAdmin()) && jpf_passWord.getText().equals(getPasswordAdmin())){
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/admin/AdminWorkSpace.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        
        else if(jtf_userName.getText().equals(getUsernameAccount()) && jpf_passWord.getText().equals(getPasswordAccount())){
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/account/WorkSpace.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            AlertMaker alert = new AlertMaker();
            alert.showSimpleAlert("Invalid Username of password","Invalid Username of password");
        }
    }
    
}
