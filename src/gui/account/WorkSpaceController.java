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
import com.jfoenix.controls.JFXButton;
import dao.OrderDetailsDAO;
import dao.OrdersDAO;
import entity.Account;
import entity.OrderDetails;
import entity.Orders;
import entity.Product;
import gui.DoiPasswordController;
import gui.admin.ListProductController;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.*;

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
    
    Account ac;
    @FXML
    private TableView<Product> tbv_Product;
    @FXML
    private TableColumn<?, ?> tbCol_productID;
    @FXML
    private TableColumn<?, ?> tbCol_Name;
    @FXML
    private TableColumn<?, ?> tbCol_Category;
    @FXML
    private TableColumn<?, ?> tbCol_Price;

    Entry<Orders,ArrayList<OrderDetails>> entry;
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Product> data; 
    private ObservableList<OrderDetails> data2;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<OrderDetails> tbv_Orders;
    @FXML
    private TableColumn<?, ?> tbCol_OrderID;
    @FXML
    private TableColumn<?, ?> tbCol_ProductID;
    @FXML
    private TableColumn<?, ?> tbCol_quantity;
    @FXML
    private TableColumn<?, ?> tbCol_detailPrice;

    @FXML
    private JFXButton jBtn_Add;
    @FXML
    private JFXButton jBtn_Edit;
    @FXML
    private JFXButton jBtn_Delete;

    @FXML
    private Label label_ofProductID;
    @FXML
    private Label label_ofName;
    @FXML
    private Label label_ofPrice;
    @FXML
    private JFXTextField jtf_TotalPrice;
    @FXML
    private Label label_ofOrderID;
    @FXML
    private Label label_ofOrderTime;
    @FXML
    private Label label_ofAccountID;
    @FXML
    private JFXTextField jtf_quantity;
    @FXML
    private JFXTextField jtf_customerPay;
    @FXML
    private JFXTextField jtf_payBack;
   
    
    HashMap<Integer, ArrayList<String>> ordernote_list = new HashMap<>();       // danh sách note của order hiện tại              mỗi bàn tương ứng với một danh sách note
    HashMap<Orders, ArrayList<OrderDetails>> order_list = new HashMap<>();       // danh sách order hiện tại
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn = connection.DBConnection.getCon();
        data = FXCollections.observableArrayList();
        data2 = FXCollections.observableArrayList();
        setCellProductTable();
        LoadProductFromDB();
        bindingsProductfromTableView();
      
    }    
    private void bindingsProductfromTableView()
    {
       tbv_Product.setOnMouseClicked((MouseEvent event) -> {
           Product p = tbv_Product.getItems().get(tbv_Product.getSelectionModel().getSelectedIndex());
           label_ofProductID.setText(p.getProduct_id());
           label_ofName.setText(p.getName());
           label_ofPrice.setText(String.valueOf(p.getPrice()));
       });
    }
    private void setCellProductTable()
    {
        tbCol_productID.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        tbCol_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbCol_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        tbCol_Category.setCellValueFactory(new PropertyValueFactory<>("category_id"));
    }
    

    @FXML
    private void _Menu(ActionEvent event) throws IOException {
        
        
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
    private void LoadProductFromDB()
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
    
    public void setAccount(Account account) {
       ac = account;
    }

    public Entry<Orders, ArrayList<OrderDetails>> getOrderofTable(int tablenumber){
        for(Entry<Orders, ArrayList<OrderDetails>> iter : this.order_list.entrySet()){
        }
        
        return null;
    }
    
    private void loadOrdersDetailFromDB()
    {
        data2.clear();
            
    }
    private String autoOrderID()
    {
        String orderID = "oID00000";
        try {
            pst = conn.prepareStatement("select max(order_id from Orders");
            rs = pst.executeQuery();
            if (rs.next())
            {
                int n = Integer.parseInt(orderID.substring(3)) + 1;
                int m = String.valueOf(n).length();
                orderID = orderID.substring(0, 8 - m) + String.valueOf(n);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(WorkSpaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderID;
    }
    private String getRealTimeDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy//MM//dd HH:mm:ss" );
        Date date = new Date();
        return dateFormat.format(date);
    }
    @FXML
    private void _Add(ActionEvent event) throws SQLException {
        
        //OrdersDAO.insert(entry);
        String query = "Insert into Orders values (?,?,?,?,?,?)";
        String query2 = "Insert into OrderDetails values (?,?,?)";
        
        String orderid = autoOrderID();
        float quantity = Float.valueOf(jtf_quantity.getText()); 
        String productID = label_ofProductID.getText();
        String price = label_ofPrice.getText();
        String date = getRealTimeDate();
        String accountID = ac.getAccount_id();
        
        String Quantity = String.valueOf(quantity);
        
        
        if ( Quantity.isEmpty())
        {
            AlertMaker.AlertMaker.showErrorMessage("Errors", "Please fills in quantity text field");
        }
        
        try
        {
            pst = conn.prepareStatement(query);
            pst.setString(1, orderid);
            pst.setString(2, accountID);
            pst.setString(3,date);
            pst.setString(4, price);
            pst.setFloat(5, 0);
            pst.setFloat(6, 0);
            
            int i = pst.executeUpdate();
            if (i == 1)
            {
                AlertMaker.AlertMaker.showSimpleAlert("Add", "Successfully");
            }
        }
        catch(SQLException ex)
        {
            
        }
        finally
        {
            pst.close();
        }
        
        try
        {
            pst = conn.prepareStatement(query2);
            pst.setString(1, orderid);
            pst.setString(2, productID);
            pst.setFloat(3, quantity);
            int i = pst.executeUpdate();
            if (i == 1)
            {
                setCell();
                LoadData(orderid);
            }
        }
        catch(SQLException ex)
        {
            
        }
        /*try 
        {
            pst = conn.prepareStatement(query);
            pst.setString(1, orderid);
            pst.setString(2, date);
            pst.setString(3, accountID);
            pst.setString(4, date);
            pst.setString(5, orderid);
            pst.setString(6, date);
            int i = pst.executeUpdate();
            if (i == 1)
            {
                AlertMaker.AlertMaker.showSimpleAlert("Add", "Successful!");
                
                clearQuantityfield();
            }
        }
        catch(SQLException ex)
        {
            
        }
        */
        
      
        
        //data2.add(e)
        
    }
    private void setCell()
    {
        tbCol_OrderID.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        tbCol_ProductID.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        tbCol_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                
    }
    private void LoadData(String orderid) throws SQLException
    {
        data2.clear();
        
        try 
        {
            String query = "Select* from OrderDetails"
                    + "Where order_id = ?";
                            
            pst = conn.prepareStatement(query);
            pst.setString(1, orderid);
            rs = pst.executeQuery();
            while(rs.next())
            {
                data2.add(new OrderDetails(rs.getString(1),rs.getString(2),rs.getInt(3)));
            }
            tbv_Orders.setItems(data2);
        }
        catch(SQLException ex)
        {
            
        }
        finally 
        {
            pst.close();
        }
    }
    private void clearQuantityfield()
        {
            jtf_quantity.setText("");
        }
    @FXML
    private void _Edit(ActionEvent event) {
    }

    @FXML
    private void _Delete(ActionEvent event) {
    }
}
