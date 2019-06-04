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
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
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
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

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
    private Label label_ofAccountID;
    @FXML
    private JFXTextField jtf_customerPay;
    @FXML
    private JFXTextField jtf_payBack;
    
    LocalDate today = LocalDate.now( ZoneId.of( "Asia/Ho_Chi_Minh" ) );
   
    
    HashMap<Integer, ArrayList<String>> ordernote_list = new HashMap<>();       // danh sách note của order hiện tại              mỗi bàn tương ứng với một danh sách note
    HashMap<Orders, ArrayList<OrderDetails>> order_list = new HashMap<>(); 
    
// danh sách order hiện tại
    @FXML
    private Spinner<Integer> spinner = new Spinner<Integer>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn = connection.DBConnection.getCon();
        data = FXCollections.observableArrayList();
        data2 = FXCollections.observableArrayList();
        
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 2);
 
        this.spinner.setValueFactory(valueFactory);
         
        
         
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
    
    public void LoadInfoOrder(){
        Orders orders =  OrdersDAO.insert(label_ofAccountID.getText(), java.sql.Date.valueOf(this.today));
        label_ofOrderID.setText(orders.getOrder_id());
        
    }
    
    public void setAccount(Account account) {
       ac = account;
       label_ofAccountID.setText(ac.getAccount_id());
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
            pst = conn.prepareStatement("SELECT max(order_id) from Orders");
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
       

        
        String orderid = label_ofOrderID.getText();
        
        String productID = label_ofProductID.getText();
        int quantity = spinner.getValue();
        

        String Quantity = String.valueOf(quantity);
        if (Quantity.isEmpty())
        {
            AlertMaker.AlertMaker.showErrorMessage("Erroes", "Please fills in quantity text field");
        }
        
        int i = OrderDetailsDAO.insert(orderid, productID, quantity);
        
           
            
            if (i == 1)
            {
                
                AlertMaker.AlertMaker.showSimpleAlert("Add", "Succesfully!");
                setCell();
                LoadData(label_ofOrderID.getText());
            }
             
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
            String query = "SELECT * FROM OrderDetails "
                    + "WHERE order_id = ?";
                            
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
    
    @FXML
    private void _Edit(ActionEvent event) {
    }

    @FXML
    private void _Delete(ActionEvent event) {
    }

    @FXML
    private void _TaoHoaDon(ActionEvent event) {
        
        LoadInfoOrder();
    }

    @FXML
    private void _Payment(ActionEvent event) {
        Float totalprice = Float.valueOf(jtf_TotalPrice.toString());
        Float customerpay = Float.valueOf(jtf_customerPay.toString());
        Float payback = Float.valueOf(jtf_payBack.getText());
        
        OrdersDAO.Update(label_ofOrderID.getText(), totalprice, customerpay, payback);
    }
}
