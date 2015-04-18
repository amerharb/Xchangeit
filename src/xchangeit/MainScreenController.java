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

    Stage ConnectStage;
    Stage CurrencyStage;
    Stage RateStage;
    Stage BuyStage;
    Stage SellStage;
    Stage TransCashInStage;
    Stage TransCashOutStage;
    Stage TransCurrInStage;
    Stage TransCurrOutStage;
    
    private XchangeitDatabase Database = new XchangeitDatabase();
    
    @FXML
    private void handleExitButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Exit");
        System.exit(0);
    }

    @FXML
    private void handleConnectButtonAction(ActionEvent event)
    {
        System.out.println("You clicked Connect");
        try {

            if (ConnectStage == null){
                ConnectStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("ConnectScreen.fxml"));
                Scene scene = new Scene(root);
                ConnectStage.setScene(scene);
                ConnectScreenController.setMainScreen(this);
            }
            ConnectStage.show();            
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            ConnectStage = null;
        }

    }
    
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
    
    @FXML
    private void handleTransCashInButtonAction(ActionEvent event)
    {
        System.out.println("You clicked TransCashIn");
        try {
            
            if (TransCashInStage == null){
                TransCashInStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("TransCashInScreen.fxml"));
                Scene scene = new Scene(root);
                TransCashInStage.setScene(scene);
            }
            TransCashInStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCashOutButtonAction(ActionEvent event)
    {
        System.out.println("You clicked TransCashOut");
        try {
            
            if (TransCashOutStage == null){
                TransCashOutStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("TransCashOutScreen.fxml"));
                Scene scene = new Scene(root);
                TransCashOutStage.setScene(scene);
            }
            TransCashOutStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCurrInButtonAction(ActionEvent event)
    {
        System.out.println("You clicked TransCurrIn");
        try {
            
            if (TransCurrInStage == null){
                TransCurrInStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("TransCurrInScreen.fxml"));
                Scene scene = new Scene(root);
                TransCurrInStage.setScene(scene);
            }
            TransCurrInStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCurrOutButtonAction(ActionEvent event)
    {
        System.out.println("You clicked TransCurrOut");
        try {
            
            if (TransCurrOutStage == null){
                TransCurrOutStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("TransCurrOutScreen.fxml"));
                Scene scene = new Scene(root);
                TransCurrOutStage.setScene(scene);
            }
            TransCurrOutStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    public XchangeitDatabase getDatabase()
    {
        return Database;
    }
    
}
