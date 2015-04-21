/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class ConnectScreenController extends XchController
{

    @FXML AnchorPane connectPane;
    @FXML TextField serverNameText;
    
    @FXML PasswordField rootPasswordText;
    
    @FXML 
    private void handleOkButtonAction(ActionEvent event)
    {
        System.out.println("You clicked OK");
        XchDatabase db = MainScreen.getDatabase();
        db.connect(serverNameText.getText(), rootPasswordText.getText(), "CurEx");
        //TODO: find more logical way to close window
        serverNameText.getParent().getScene().getWindow().hide();

    }
    
    
    
    @FXML
    private void handleCancelButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Cancel");
        //TODO: find more logical way to close window
        serverNameText.getParent().getScene().getWindow().hide();
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }    
    
}
