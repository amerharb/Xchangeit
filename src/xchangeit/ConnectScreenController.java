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
import xchangeit.XchController;
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

    @FXML private AnchorPane connectPane;
    @FXML private TextField serverNameText;
    
    @FXML private PasswordField rootPasswordText;
    
    @FXML 
    private void handleCreateDatabaseButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Create Database");
        XchDatabase db = MainScreen.getDatabase();
        if (db.getStatus() == XchDatabase.XchConnectionStatusEnum.Connected)
            db.Disconnect();
        
        db.connect(serverNameText.getText(), rootPasswordText.getText());
        db.createDatabase();
        
    }
    
    @FXML 
    private void handleOkButtonAction(ActionEvent event)
    {
        try{
        
        XchDatabase db = MainScreen.getDatabase();
        db.connect(serverNameText.getText(), rootPasswordText.getText(), "Xchangeit");
        //TODO: find more logical way to close window
        serverNameText.getParent().getScene().getWindow().hide();
        System.out.println("You clicked OK");
        //System.out.println("you have connect to database");    
        }catch(Exception e){
            //if(db.connect=null){
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
            
    }    
    
}
