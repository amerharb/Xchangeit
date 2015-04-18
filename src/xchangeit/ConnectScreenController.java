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
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class ConnectScreenController implements Initializable
{
    
    /**
     * Initializes the controller class.
     */
    private static MainScreenController MainScreen;
    public static void setMainScreen(MainScreenController MS){
        MainScreen = MS;
    }
    
    @FXML
    TextField serverNameText;
    
    @FXML
    PasswordField rootPasswordText;
    
    @FXML
    private void handleOkButtonAction(ActionEvent event)
    {
        System.out.println("You clicked OK");
        XchangeitDatabase db = MainScreen.getDatabase();
        db.connect(serverNameText.getText(), rootPasswordText.getText());
        serverNameText.getParent().getScene().getWindow().hide();
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Cancel");
        serverNameText.getParent().getScene().getWindow().hide();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        Parent p = okButton.getParent();
//        XchScene s = (XchScene) p.getScene();
//        
//        if (s!=null){
//            MainScreen = s.getMainScreen();
//            MainScreen.Database = null; //TEST
//        }    
    }    
    
    
}
