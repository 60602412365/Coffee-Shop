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
import gui.DoiPasswordController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private AnchorPane anchorpane1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void _Menu(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/admin/ListProduct.fxml"));
        anchorpane1.getChildren().setAll(pane);
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

    @FXML
    private void _timKiem(ActionEvent event) {
    }
    
    public void setAccount(Account account) {
       ac = account;
    }
}
