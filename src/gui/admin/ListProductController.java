/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin;

import com.jfoenix.controls.JFXTextField;
import entity.Product;
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
public class ListProductController implements Initializable {

    @FXML
    private TableView<Product> tbv_Product;
    @FXML
    private TableColumn<Product, String> tbCol_ID;
    @FXML
    private TableColumn<Product, String> tbCol_Name;
    @FXML
    private TableColumn<Product, Float> tbCol_Price;
    @FXML
    private TableColumn<Product, String> tbCol_categoryID;
    @FXML
    private Button btn_Add;
    @FXML
    private JFXTextField jtf_search;
    @FXML
    private Button btn_timKiem;
    @FXML
    private Button btn_Delete;
    @FXML
    private Button btn_Edit;
    @FXML
    private JFXTextField jtf_ID;
    @FXML
    private JFXTextField jtf_Name;
    @FXML
    private JFXTextField jtf_Price;
    @FXML
    private JFXTextField jtf_categoryID;
    
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Product> data; 

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

    @FXML
    private void _Add(ActionEvent event) throws SQLException {
        String query = "Insert into Product values (?,?,?,?)";
        String ID = jtf_ID.getText();
        String name = jtf_Name.getText();
        String price = jtf_Price.getText();
        String categoryID =jtf_categoryID.getText();
        
        if (ID.isEmpty() || name.isEmpty() || price.isEmpty() || categoryID.isEmpty())
        {
            AlertMaker.AlertMaker.showErrorMessage("Cảnh báo", "You must enter all textfields");
        }
        try
        {
            pst = conn.prepareStatement(query);
            pst.setString(1, ID);
            pst.setString(2, name);
            pst.setString(3,price);
            pst.setString(4,categoryID);
            
            int i = pst.executeUpdate();
            if (i == 1)
            {
                AlertMaker.AlertMaker.showSimpleAlert("Thêm Sản phẩm", "Thành Công");
                
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
    private void _timKiem(ActionEvent event) {
    }

    @FXML
    private void _Delete(ActionEvent event) throws SQLException {
        String query = "Delete from Product where product_id = ?";
        try
        {
            pst = conn.prepareStatement(query);
            pst.setString(1,jtf_ID.getText());
            int i = pst.executeUpdate();
            if (i == 1)
            {
                AlertMaker.AlertMaker.showSimpleAlert("Xóa thông tin Sản phẩm", "Thành công");
                
                LoadDataFromDB();
                ClearTextFields();
            }
            
        }
        catch(SQLException ex)
        {
            
        }
    }

    @FXML
    private void _Edit(ActionEvent event) {
        String query = "Update Product set product_id = ?, name = ?, price = ?, category_id = ? ";
        try
        {
            String ID = jtf_ID.getText();
            String name = jtf_Name.getText();
            String price = jtf_Price.getText();
            String categoryID = jtf_categoryID.getText();
            
            
            if (ID.isEmpty() || name.isEmpty() || price.isEmpty() || categoryID.isEmpty())
            {
                AlertMaker.AlertMaker.showErrorMessage("Cảnh báo", "You must enter all textfields");
            }
            pst = conn.prepareStatement(query);
            
            pst.setString(1, ID);
            pst.setString(2, name);
            pst.setString(3, price);
            pst.setString(4, categoryID);
            
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

    private void setCellTable() {
        tbCol_ID.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        tbCol_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbCol_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        tbCol_categoryID.setCellValueFactory(new PropertyValueFactory<>("category_id"));
    }

    private void LoadDataFromDB() {
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from Product");
            rs = pst.executeQuery();
            
            while (rs.next())
            {
                data.add(new Product(rs.getString(1),rs.getString(2), +rs.getFloat(3),rs.getString(4)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbv_Product.setItems(data);
    }

    private void bindingsFromTableViewtoTextField() {
        tbv_Product.setOnMouseClicked((MouseEvent event) -> {
           Product p = tbv_Product.getItems().get(tbv_Product.getSelectionModel().getSelectedIndex());
           jtf_ID.setText(p.getProduct_id());
           jtf_Name.setText(p.getName());
           jtf_Price.setText(Float.toString(p.getPrice()));
           jtf_categoryID.setText(p.getCategory_id());
           
       });
    }
    private void ClearTextFields()
    {
        jtf_ID.clear();
        jtf_Name.clear();
        jtf_Price.clear();
        jtf_categoryID.clear();
    }
    
}
