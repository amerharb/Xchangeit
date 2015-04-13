/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Amer
 */

public class MainScreenController implements Initializable 
{
    Stage CurrencyStage;
    Stage RateStage;
    Stage BuyStage;
    Stage SellStage;
    

    @FXML
    private void handleCurrencyButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Currency");
        try {
            
            if (CurrencyStage == null){
                CurrencyStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("CurrencyScreen.fxml"));
                Scene scene = new Scene(root);
                CurrencyStage.setScene(scene);
            }
            CurrencyStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleRateButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Rate");
        try {
            
            if (RateStage == null){
                RateStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("RateScreen.fxml"));
                Scene scene = new Scene(root);
                RateStage.setScene(scene);
            }
            RateStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    @FXML
    private void handleBuyButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Buy");
        try {
            
            if (BuyStage == null){
                BuyStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("BuyScreen.fxml"));
                Scene scene = new Scene(root);
                BuyStage.setScene(scene);
            }
            BuyStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleSellButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Sell");
        try {
            
            if (SellStage == null){
                SellStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("SellScreen.fxml"));
                Scene scene = new Scene(root);
                SellStage.setScene(scene);
            }
            SellStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
