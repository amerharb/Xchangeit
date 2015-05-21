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

    @FXML private AnchorPane connectPane;
    @FXML private TextField serverNameText;
    
    @FXML private PasswordField rootPasswordText;
    
    @FXML 
    private void handleCreateDatabaseButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Create Database");
        //XchDatabase db = mainScreen.getDatabase();
        if (database.getStatus() == XchDatabase.XchConnectionStatusEnum.Connected)
            database.disconnect();
        
        database.connect(serverNameText.getText(), rootPasswordText.getText());
        database.createDatabase();
        
    }
    
    @FXML 
    private void handleOkButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Connect");
        try{
        
            if (database.connect(serverNameText.getText(), rootPasswordText.getText(), "Xchangeit")){
                mainScreen.setButtonsDisable(false);
                settings.setDefaultDatabaseServerName(serverNameText.getText());
                settings.setDefaultRootPassword(rootPasswordText.getText());
                settings.saveSettings();
                //TODO: find more logical way to close window
                serverNameText.getParent().getScene().getWindow().hide();
            };
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("error");    
        }
            
        
        

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
        
        serverNameText.setText(settings.getDefaultDatabaseServerName());
        rootPasswordText.setText(settings.getDefaultRootPassword());
        
    }    
    
}
