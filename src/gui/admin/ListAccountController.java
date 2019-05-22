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
import java.sql.Date;
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
    
    @FXML
    private TableColumn<?, ?> tbCol_birthday;
    @FXML
    private TableColumn<?, ?> tbCol_Address;
    @FXML
    private TableColumn<?, ?> tbCol_email;
    @FXML
    private TableColumn<?, ?> tbCol_phone;
    @FXML
    private JFXTextField jtf_birthday;
    @FXML
    private JFXTextField jtf_address;
    @FXML
    private JFXTextField jtf_email;
    @FXML
    private JFXTextField jtf_phone;
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
        tbCol_ID.setCellValueFactory(new PropertyValueFactory<>("account_id"));
        tbCol_userName.setCellValueFactory(new PropertyValueFactory<>("username"));
        tbCol_passWord.setCellValueFactory(new PropertyValueFactory<>("password"));
        tbCol_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbCol_birthday.setCellValueFactory(new PropertyValueFactory<>("birth_day"));
        tbCol_Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tbCol_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbCol_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }
    private void LoadDataFromDB()
    {
      
        data.clear();
        try {
            pst = conn.prepareStatement("Select* from Account");
            rs = pst.executeQuery();
            
            while (rs.next())
            {
                data.add(new Account(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8)));
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
           jtf_ID.setText(a.getAccount_id());
           jtf_userName.setText(a.getUsername());
           jtf_passWord.setText(a.getPassword());
           jtf_name.setText(a.getName());
           jtf_birthday.setText(a.getBirth_day().toString());
           jtf_address.setText(a.getAddress());
           jtf_email.setText(a.getEmail());
           jtf_phone.setText(a.getPhone());
       });
    }
    private void ClearTextFields()
    {
        jtf_ID.clear();
        jtf_userName.clear();
        jtf_passWord.clear();
        jtf_name.clear();
        jtf_birthday.clear();
        jtf_address.clear();
        jtf_email.clear();
        jtf_phone.clear();
    }
    
    
    
    @FXML
    private void _Add(ActionEvent event) throws SQLException {
        String query = "Insert into Account values (?,?,?,?)";
        String ID = jtf_ID.getText();
        String userName = jtf_userName.getText();
        String passWord = jtf_passWord.getText();
        String name =jtf_name.getText();
        String birthday = jtf_birthday.getText();
        String address = jtf_address.getText();
        String email = jtf_email.getText();
        String phone = jtf_phone.getText();
        try
        {
            pst = conn.prepareStatement(query);
            pst.setString(1, ID);
            pst.setString(2,userName);
            pst.setString(3,passWord);
            pst.setString(4,name);
            pst.setString(5, birthday);
            pst.setString(6,address);
            pst.setString(7,email);
            pst.setString(8,phone);
            
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
            String birthday = jtf_birthday.getText();
            String address = jtf_address.getText();
            String email = jtf_email.getText();
            String phone = jtf_phone.getText();
            
            if (ID.isEmpty() || userName.isEmpty() || passWord.isEmpty() || name.isEmpty())
            {
                AlertMaker.AlertMaker.showErrorMessage("Update", "You must enter all textfields");
            }
            pst = conn.prepareStatement(query);
            
            pst.setString(1, ID);
            pst.setString(2, userName);
            pst.setString(3, passWord);
            pst.setString(4, name);
            pst.setString(5, birthday);
            pst.setString(6,address);
            pst.setString(7,email);
            pst.setString(8,phone);
            
            int i = pst.executeUpdate();
            if (i == 1)
            {
                AlertMaker.AlertMaker.showSimpleAlert("Update Account", "Successfully!");
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
        String query = "Delete from Account where em_id = ?";
        try
        {
            pst = conn.prepareStatement(query);
            pst.setString(1,jtf_ID.getText());
            int i = pst.executeUpdate();
            if (i == 1)
            {
                AlertMaker.AlertMaker.showSimpleAlert("Xóa thông tin Account", "Thành công");
                
                LoadDataFromDB();
                ClearTextFields();
            }
            
        }
        catch(SQLException ex)
        {
            
        }
    }
    
}
