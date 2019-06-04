/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Utilities.ChangeScreen;
import entity.Admin;
import gui.DoiPasswordController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminWorkSpaceController implements Initializable {

    @FXML
    private Button btn_quanLyNhanSu;
    @FXML
    private Button btn_quanLyThucDon;
    @FXML
    private Button btn_doanhThu;
    @FXML
    private Button btn_doiMatKhau;
    @FXML
    private Button btn_dangXuat;

    private Admin ad;
    @FXML
    private Button btn_QLhoaDon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_quanLyNhanSu(ActionEvent event) {
        ChangeScreen.loadWindow(getClass().getResource("/gui/admin/ListAccount.fxml"), "Quản lý nhân sự", null);
    }
 



    @FXML
    private void _quanLyThucDon(ActionEvent event) {
        ChangeScreen.loadWindow(getClass().getResource("/gui/admin/ListProduct.fxml"), "Quản lý thực đơn", null);
    }
    
    @FXML
    private void _QLHOADON(ActionEvent event) throws IOException {
        /*
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/gui/admin/ListOrder.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(home_page_scene);
        stage.setTitle("Quản lý thực đơn");
        stage.show();
        */ 
        ChangeScreen.loadWindow(getClass().getResource("/gui/admin/ListOrder.fxml"), "Quản lý hóa đơn", null);
    }
    @FXML
    private void _doanhThu(ActionEvent event) {
    }

    @FXML
    private void _doiMatKhau(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader  loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/DoiPassword.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        DoiPasswordController controller = loader.getController();
        controller.setAdmin(ad);
        stage.setScene(scene);
        stage.show();
      
    }

    @FXML
    private void _dangXuat(ActionEvent event) {
        try
        {
            Stage stage = (Stage) btn_quanLyNhanSu.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
            Parent root = (Parent) loader.load();
 
            
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();   
            
        }
        catch(IOException ex)
        {
        }
    }
     public void setAdmin(Admin admin) {
        ad = admin;
    }



    
   
    
}
