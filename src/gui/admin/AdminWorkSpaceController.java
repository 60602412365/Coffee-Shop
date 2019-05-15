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
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AdminWorkSpaceController implements Initializable {

    @FXML
    private Button btn_quanLyNhanSu;
    @FXML
    private Button btn_quanLyBan;
    @FXML
    private Button btn_quanLyThucDon;
    @FXML
    private Button btn_doanhThu;
    @FXML
    private Button btn_doiMatKhau;
    @FXML
    private Button btn_dangXuat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void _quanLyNhanSu(ActionEvent event) 
    {
        ChangeScreen.loadWindowforAdminWorkSpace(getClass().getResource("/gui/admin/ListAccount.fxml"), "Quản lý nhân sự", null);
    }

    @FXML
    private void _quanLyBan(ActionEvent event) 
    {
        ChangeScreen.loadWindowforAdminWorkSpace(getClass().getResource("/gui/admin/ListTable.fxml"), "Quản lý Bàn", null);
    }

    @FXML
    private void _quanLyThucDon(ActionEvent event) 
    {
        ChangeScreen.loadWindowforAdminWorkSpace(getClass().getResource("/gui/admin/ListProduct.fxml"), "Quản lý thực đơn", null);
    }

    @FXML
    private void _doanhThu(ActionEvent event) {
        
    }

    @FXML
    private void _doiMatKhau(ActionEvent event) {
        ChangeScreen.loadWindowforAdminWorkSpace(getClass().getResource("/gui/DoiPassword.fxml"), "Đổi mật khẩu", null);
    }

    @FXML
    private void _dangXuat(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Đăng Xuất");
        alert.setHeaderText("Cảnh báo đăng xuất");
        alert.setContentText("Bạn có chắc chắn muôn đăng xuất không ?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            ChangeScreen.loadWindowforAdminWorkSpace(getClass().getResource("/gui/Login.fxml"), "Login", null);
        }
        else
        {
            System.exit(0);
        }
        
    }
    
}
