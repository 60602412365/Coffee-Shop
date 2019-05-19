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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void _Menu(ActionEvent event) {
    }

    @FXML
    private void _Settings(ActionEvent event) {
        ChangeScreen.loadWindow(getClass().getResource("/gui/DoiPassword.fxml"), "Đổi mật khẩu", null);
      
    }

    @FXML
    private void _timKiem(ActionEvent event) {
    }
    
}
