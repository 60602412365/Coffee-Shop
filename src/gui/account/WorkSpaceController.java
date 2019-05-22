/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.account;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Utilities.ChangeScreen;
import entity.Account;
import entity.Product;
import gui.DoiPasswordController;
import gui.admin.ListProductController;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class WorkSpaceController implements Initializable {

    @FXML
    private Button btn_Menu;
    @FXML
    private Button btn_Settings;
    @FXML
    private JFXTextField jtf_search;
    @FXML
    private Button btn_timKiem;
    
    Account ac;
    @FXML
    private TableView<Product> tbv_Product;
    @FXML
    private TableColumn<?, ?> tbCol_productID;
    @FXML
    private TableColumn<?, ?> tbCol_name;
    @FXML
    private TableColumn<?, ?> tbCol_price;
    @FXML
    private TableColumn<?, ?> tbCol_categoryID;
    
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
      
    }    
    private void setCellTable()
    {
        tbCol_productID.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        tbCol_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbCol_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        tbCol_categoryID.setCellValueFactory(new PropertyValueFactory<>("category_id"));
    }
    @FXML
    private void _Menu(ActionEvent event) throws IOException {
        
        setCellTable();
        LoadDataFromDB();
    }

    @FXML
    private void _Settings(ActionEvent event) throws IOException {
        
        Stage stage = new Stage();
        FXMLLoader  loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/DoiPassword.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        DoiPasswordController controller = loader.getController();
        controller.setAccount(ac);
        stage.setScene(scene);
        stage.show();
        
        
    }
    private void LoadDataFromDB()
    {
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
    @FXML
    private void _timKiem(ActionEvent event) {
    }
    
    public void setAccount(Account account) {
       ac = account;
    }
}
