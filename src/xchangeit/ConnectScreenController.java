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
import javafx.scene.control.CheckBox;
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
    @FXML private TextField serverAddressText;
    @FXML private TextField databaseNameText;
    @FXML private PasswordField rootPasswordText;
    @FXML private CheckBox savePasswordCheckBox;
    @FXML private CheckBox autoConnectCheckBox;
    
    @FXML 
    private void handleCreateDatabaseButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Create Database");

        if (database.getStatus() == XchDatabase.XchConnectionStatusEnum.Connected)
            database.disconnect();
        
        database.connect(serverAddressText.getText(), rootPasswordText.getText());
        database.createDatabase(databaseNameText.getText());
        
    }
    
    @FXML 
    private void handleOkButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Connect");
        try{
        
            if (database.getStatus() == XchDatabase.XchConnectionStatusEnum.Connected)
                database.disconnect();

            if (database.connect(serverAddressText.getText(), rootPasswordText.getText(), databaseNameText.getText())){
                mainScreen.setButtonsDisable(false);
                settings.setDefaultDatabaseServerAddress(serverAddressText.getText());
                if (savePasswordCheckBox.isSelected()){
                    settings.setDefaultRootPassword(rootPasswordText.getText());
                }else{
                    settings.setDefaultRootPassword("");
                }
                settings.setDefaultDatabaseName(databaseNameText.getText());
                settings.setAutoConnect(autoConnectCheckBox.isSelected());
                settings.setSavePassword(savePasswordCheckBox.isSelected());

                settings.saveSettings();
                //TODO: find more logical way to close window
                serverAddressText.getParent().getScene().getWindow().hide();
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
        serverAddressText.getParent().getScene().getWindow().hide();
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        serverAddressText.setText(settings.getDefaultDatabaseServerAddress());
        rootPasswordText.setText(settings.getDefaultRootPassword());
        databaseNameText.setText(settings.getDefaultDatabaseName());
        autoConnectCheckBox.setSelected(settings.isAutoConnect());
        savePasswordCheckBox.setSelected(settings.isSavePassword());
        
    }    
    
}
