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
import javafx.stage.Stage;

/**
 *
 * @author Amer
 */

public class MainScreenController implements Initializable 
{

    private Stage connectStage;
    private Stage currencyStage;
    private Stage rateStage;
    private Stage buyStage;
    private Stage sellStage;
    private Stage transCashInStage;
    private Stage transCashOutStage;
    private Stage transCurrInStage;
    private Stage transCurrOutStage;
    
    private XchDatabase Database = new XchDatabase();
    
    @FXML
    private void handleExitAction(ActionEvent event)
    {
        System.out.println("You clicked Exit");
        System.exit(0);
    }

    @FXML
    private void handleConnectAction(ActionEvent event)
    {
        System.out.println("You clicked Connect");
        try {
            showScreen(connectStage, "ConnectScreen.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            connectStage = null;
        }

    }
    
    @FXML
    private void handleDisconnectAction(ActionEvent event)
    {
        System.out.println("You clicked Disconnect");
        try {
            Database.Disconnect();
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            connectStage = null;
        }

    }
    
    @FXML
    private void handleCurrencyAction(ActionEvent event)
    {
        System.out.println("You clicked Currency");
        try {
            showScreen(currencyStage, "currency/CurrencyScreen.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void handleRateAction(ActionEvent event)
    {
        System.out.println("You clicked Rate");
        try {
            showScreen(rateStage, "rate/RateScreen.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    @FXML
    private void handleBuyAction(ActionEvent event)
    {
        System.out.println("You clicked Buy");
        try {
            showScreen(buyStage, "buy/BuyScreen.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleSellAction(ActionEvent event)
    {
        System.out.println("You clicked Sell");
        try {
            showScreen(sellStage, "sell/SellScreen.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCashInAction(ActionEvent event)
    {
        System.out.println("You clicked TransCashIn");
        try {
            showScreen(transCashInStage, "cashIn/CashInScreen.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCashOutAction(ActionEvent event)
    {
        System.out.println("You clicked TransCashOut");
        try {
            showScreen(transCashOutStage, "cashOut/CashOutScreen.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCurrInAction(ActionEvent event){
        System.out.println("You clicked TransCurrIn");
        try {
            showScreen(transCurrInStage, "currIn/CurrInScreen.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCurrOutAction(ActionEvent event){
        System.out.println("You clicked TransCurrOut");
        try {
            showScreen(transCurrOutStage, "currOut/CurrOutScreen.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void showScreen(Stage stage, String fxmlFile) throws IOException{
        try{
            if (stage == null){
                FXMLLoader loader = new FXMLLoader();
                stage = new Stage();
                
                Parent root = loader.load(getClass().getResource(fxmlFile));
                //NOTE: no need to list line as it has been made a static and added to the initionlization method below
                //XchController.setMainScreen(this);
                //XchController cnt = loader.getController();
                //cnt.setMainScreen(this);
                
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }
            stage.show();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        XchController.setMainScreen(this);
    }    

    public XchDatabase getDatabase(){
        return Database;
    }
    
}
