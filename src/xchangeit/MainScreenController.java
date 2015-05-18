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
    
    private final XchDatabase database = new XchDatabase();
    
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
            if  (connectStage == null){ //for first time
                connectStage  = new Stage();
                connectStage.setScene(GetScene("ConnectScreen.fxml"));
            }
            connectStage.show();
            connectStage.toFront();
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
            database.Disconnect();
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
            if  (currencyStage == null){ //for first time
                currencyStage  = new Stage();
                currencyStage.setScene(GetScene("currency/CurrencyScreen.fxml"));
            }
            currencyStage.show();
            currencyStage.toFront();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void handleRateAction(ActionEvent event)
    {
        System.out.println("You clicked Rate");
        try {
            if  (rateStage == null){ //for first time
                rateStage  = new Stage();
                rateStage.setScene(GetScene("rate/RateScreen.fxml"));
            }
            rateStage.show();
            rateStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    @FXML
    private void handleBuyAction(ActionEvent event)
    {
        System.out.println("You clicked Buy");
        try {
            if  (buyStage == null){ //for first time
                buyStage  = new Stage();
                buyStage.setScene(GetScene("buy/BuyScreen.fxml"));
            }
            buyStage.show();
            buyStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleSellAction(ActionEvent event)
    {
        System.out.println("You clicked Sell");
        try {
            if  (sellStage == null){ //for first time
                sellStage  = new Stage();
                sellStage.setScene(GetScene("sell/SellScreen.fxml"));
            }
            sellStage.show();
            sellStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCashInAction(ActionEvent event)
    {
        System.out.println("You clicked TransCashIn");
        try {
            if  (transCashInStage == null){ //for first time
                transCashInStage  = new Stage();
                transCashInStage.setScene(GetScene("cashIn/CashInScreen.fxml"));
            }
            transCashInStage.show();
            transCashInStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCashOutAction(ActionEvent event)
    {
        System.out.println("You clicked TransCashOut");
        try {
            if  (transCashOutStage == null){ //for first time
                transCashOutStage  = new Stage();
                transCashOutStage.setScene(GetScene("cashOut/CashOutScreen.fxml"));
            }
            transCashOutStage.show();
            transCashOutStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCurrInAction(ActionEvent event){
        System.out.println("You clicked TransCurrIn");
        try {
            if  (transCurrInStage == null){ //for first time
                transCurrInStage  = new Stage();
                transCurrInStage.setScene(GetScene("currIn/CurrInScreen.fxml"));
            }
            transCurrInStage.show();
            transCurrInStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleTransCurrOutAction(ActionEvent event){
        System.out.println("You clicked TransCurrOut");
        try {
            if  (transCurrOutStage == null){ //for first time
                transCurrOutStage  = new Stage();
                transCurrOutStage.setScene(GetScene("currOut/CurrOutScreen.fxml"));
            }
            transCurrOutStage.show();
            transCurrOutStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private Scene GetScene(String fxmlFile) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root);
        return scene;
        
    }
    
    //TODEL to delete this method 
    private void showScreen(Stage stage, String fxmlFile) throws IOException{
        try{
            if (stage == null){
                FXMLLoader loader = new FXMLLoader();
                stage = new Stage();
                
                Parent root = loader.load(getClass().getResource(fxmlFile));
                
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
        //XchController.setMainScreen(this);
        XchController.MainScreen = this;
        XchController.database = this.database;
    }    

//    public XchDatabase getDatabase(){
//        return database;
//    }
//    
}
