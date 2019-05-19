/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin;

import com.jfoenix.controls.JFXTextField;
import entity.Account;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ListAccountController implements Initializable {

    @FXML
    private TableView<Account> tbv_Accounts;
    @FXML
    private TableColumn<?, ?> tbCol_ID;
    @FXML
    private TableColumn<?, ?> tbCol_userName;
    @FXML
    private TableColumn<?, ?> tbCol_passWord;
    @FXML
    private TableColumn<?, ?> tbCol_name;
    @FXML
    private Button btn_Add;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_Delete;
    @FXML
    private JFXTextField jtf_ID;
    @FXML
    private JFXTextField jtf_userName;
    @FXML
    private JFXTextField jtf_passWord;
    @FXML
    private JFXTextField jtf_name;
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Account> data; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn = connection.DBConnection.getCon();
        data = FXCollections.observableArrayList();
        setCellTable();
        LoadDataFromDB();
        bindingsFromTableViewtoTextField();
        
    }    
    /*
    Create Method
    */
    private void setCellTable()
    {
        tbCol_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbCol_userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tbCol_passWord.setCellValueFactory(new PropertyValueFactory<>("passWord"));
        tbCol_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        
    }
    private void LoadDataFromDB()
    {
        data.clear();
        try {
            pst = conn.prepareStatement("Select* from Account");
            rs = pst.executeQuery();
            
            while (rs.next())
            {
                data.add(new Account(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbv_Accounts.setItems(data);
    }
    private void bindingsFromTableViewtoTextField()
    {
       tbv_Accounts.setOnMouseClicked((MouseEvent event) -> {
           Account a = tbv_Accounts.getItems().get(tbv_Accounts.getSelectionModel().getSelectedIndex());
           jtf_ID.setText(a.getId());
           jtf_userName.setText(a.getUserName());
           jtf_passWord.setText(a.getPassWord());
           jtf_name.setText(a.getName());
       });
    }
    private void ClearTextFields()
    {
        jtf_ID.clear();
        jtf_userName.clear();
        jtf_passWord.clear();
        jtf_name.clear();
    }
    
    
    
    @FXML
    private void _Add(ActionEvent event) throws SQLException {
        String query = "Insert into Account values (?,?,?,?)";
        String ID = jtf_ID.getText();
        String userName = jtf_userName.getText();
        String passWord = jtf_passWord.getText();
        String name =jtf_name.getText();
        
        try
        {
            pst = conn.prepareStatement(query);
            pst.setString(1, ID);
            pst.setString(2,userName);
            pst.setString(3,passWord);
            pst.setString(4,name);
            
            int i = pst.executeUpdate();
            if (i == 1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cập nhật thông tin");
                alert.setContentText("Successfully!");
                alert.setHeaderText(null);
                alert.showAndWait();
                
                setCellTable();
                LoadDataFromDB();
                ClearTextFields();
            }
        }
        catch(SQLException ex)
        {
        }
        finally 
        {
            pst.close();
        }
    }

    @FXML
    private void _edit(ActionEvent event) {
        String query = "Update Account set em_id = ?, username = ?, pass = ?, name = ? ";
        try
        {
            String ID = jtf_ID.getText();
            String userName = jtf_userName.getText();
            String passWord = jtf_passWord.getText();
            String name = jtf_name.getText();
            
            
            if (ID.isEmpty() || userName.isEmpty() || passWord.isEmpty() || name.isEmpty())
            {
                AlertMaker.AlertMaker.showErrorMessage("Update", "You must enter all textfields");
            }
            pst = conn.prepareStatement(query);
            
            pst.setString(1, ID);
            pst.setString(2, userName);
            pst.setString(3, passWord);
            pst.setString(4, name);
            
            int i = pst.executeUpdate();
            if (i == 1)
            {
                AlertMaker.AlertMaker.showSimpleAlert("Update Product", "Successfully!");
                LoadDataFromDB();
                ClearTextFields();
            }
            
        }
        catch(SQLException ex)
        {
            
        }
    }

    @FXML
    private void _Delete(ActionEvent event) {
    }
    
}
