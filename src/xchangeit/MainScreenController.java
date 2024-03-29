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
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

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
    private Stage allTransStage;
    private Stage latestRatesStage;
    private Stage aboutStage;
    
    private final XchDatabase database = new XchDatabase();
    private final XchSettings settings = new XchSettings();
    
    @FXML private Button cashInButton;
    @FXML private Button cashOutButton;
    @FXML private Button currInButton;
    @FXML private Button currOutButton;
    @FXML private Button buyButton;
    @FXML private Button sellButton;
    @FXML private Button currencyButton;
    @FXML private Button rateButton;
    
    @FXML private MenuItem cashInMenu;
    @FXML private MenuItem cashOutMenu;
    @FXML private MenuItem currInMenu;
    @FXML private MenuItem currOutMenu;
    @FXML private MenuItem buyMenu;
    @FXML private MenuItem sellMenu;
    @FXML private MenuItem currencyMenu;
    @FXML private MenuItem rateMenu;
    @FXML private MenuItem allTransMenu;
    @FXML private MenuItem latestRatesMenu;
    @FXML private MenuItem aboutMenu;
    
    @FXML
    private void handleExitAction(ActionEvent event)
    {
        System.out.println("You clicked Exit");
        closeAllWindows();
        System.exit(0);
    }

    @FXML
    private void handleConnectAction(ActionEvent event)
    {
        System.out.println("You clicked Connect");
        try {
            if (connectStage == null){ //for first time
                connectStage  = new Stage();
                connectStage.setScene(GetScene("ConnectScreen.fxml"));
                connectStage.setTitle("Connect To Database");
                connectStage.setResizable(false);
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
            if (database.disconnect()){
                setButtonsDisable(true);
                closeAllWindows();
            }
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
            if (currencyStage == null){ //for first time
                currencyStage  = new Stage();
                currencyStage.setScene(GetScene("currency/CurrencyScreen.fxml"));
                currencyStage.setTitle("Currencies");
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
            if (rateStage == null){ //for first time
                rateStage  = new Stage();
                rateStage.setScene(GetScene("rate/RateScreen.fxml"));
                rateStage.setTitle("Rates");
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
            if (buyStage == null){ //for first time
                buyStage  = new Stage();
                buyStage.setScene(GetScene("buy/BuyScreen.fxml"));
                buyStage.setTitle("Buy");
                buyStage.setResizable(false);
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
            if (sellStage == null){ //for first time
                sellStage  = new Stage();
                sellStage.setScene(GetScene("sell/SellScreen.fxml"));
                sellStage.setTitle("Sell");
                sellStage.setResizable(false);
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
            if (transCashInStage == null){ //for first time
                transCashInStage  = new Stage();
                transCashInStage.setScene(GetScene("cashIn/CashInScreen.fxml"));
                transCashInStage.setTitle("Cash In");
                transCashInStage.setResizable(false);
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
            if (transCashOutStage == null){ //for first time
                transCashOutStage  = new Stage();
                transCashOutStage.setScene(GetScene("cashOut/CashOutScreen.fxml"));
                transCashOutStage.setTitle("Cash Out");
                transCashOutStage.setResizable(false);
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
            if (transCurrInStage == null){ //for first time
                transCurrInStage  = new Stage();
                transCurrInStage.setScene(GetScene("currIn/CurrInScreen.fxml"));
                transCurrInStage.setTitle("Currency In");
                transCurrInStage.setResizable(false);
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
            if (transCurrOutStage == null){ //for first time
                transCurrOutStage  = new Stage();
                transCurrOutStage.setScene(GetScene("currOut/CurrOutScreen.fxml"));
                transCurrOutStage.setTitle("Currency Out");
                transCurrOutStage.setResizable(false);
            }
            transCurrOutStage.show();
            transCurrOutStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleAllTransAction(ActionEvent event){
        System.out.println("You clicked All transaction");
        try {
            if (allTransStage == null){ //for first time
                allTransStage  = new Stage();
                allTransStage.setScene(GetScene("AllTransScreen.fxml"));
                allTransStage.setTitle("All Transaction");
            }
            allTransStage.show();
            allTransStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleLatestRatesAction(ActionEvent event){
        System.out.println("You clicked Latest Rates");
        try {
            if (latestRatesStage == null){ //for first time
                latestRatesStage  = new Stage();
                latestRatesStage.setScene(GetScene("LatestRatesScreen.fxml"));
                latestRatesStage.setTitle("Latest Rates");
            }
            latestRatesStage.show();
            latestRatesStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void handleAboutAction(ActionEvent event){
        System.out.println("You clicked About");
        try {
            if (aboutStage == null){ //for first time
                aboutStage  = new Stage();
                aboutStage.setScene(GetScene("AboutScreen.fxml"));
                aboutStage.setTitle("About");
            }
            aboutStage.show();
            aboutStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private Scene GetScene(String fxmlFile) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root);
        return scene;
        
    }
    
    //TODEL to delete this method not in use any more 
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
        XchController.mainScreen = this;
        XchController.database = this.database;
        XchController.settings = this.settings;
        
        if (settings.isAutoConnect()){
            if (database.connect(settings.getDefaultDatabaseServerAddress(), settings.getDefaultRootPassword(), settings.getDefaultDatabaseName())){
                setButtonsDisable(false);
            }
        }
        
    }    

    public void setButtonsDisable(boolean en)
    {
        cashInButton.disableProperty().set(en);
        cashOutButton.disableProperty().set(en);
        currInButton.disableProperty().set(en);
        currOutButton.disableProperty().set(en);
        buyButton.disableProperty().set(en);
        sellButton.disableProperty().set(en);
        currencyButton.disableProperty().set(en);
        rateButton.disableProperty().set(en);

        cashInMenu.disableProperty().set(en);
        cashOutMenu.disableProperty().set(en);
        currInMenu.disableProperty().set(en);
        currOutMenu.disableProperty().set(en);
        buyMenu.disableProperty().set(en);
        sellMenu.disableProperty().set(en);
        currencyMenu.disableProperty().set(en);
        rateMenu.disableProperty().set(en);
        allTransMenu.disableProperty().set(en);
        latestRatesMenu.disableProperty().set(en);
    }

    protected void closeAllWindows()
    {
        if (connectStage != null){
            connectStage.close();
            connectStage = null;
        }
        if (currencyStage != null){
            currencyStage.close();
            currencyStage = null;
        }
        if (rateStage != null){
            rateStage.close();
            rateStage = null;
        }
        if (buyStage != null){
            buyStage.close();
            buyStage = null;
        }
        if (sellStage != null){
            sellStage.close();
            sellStage = null;
        }
        if (transCashInStage != null){
            transCashInStage.close();
            transCashInStage = null;
        }
        if (transCashOutStage != null){
            transCashOutStage.close();
            transCashOutStage = null;
        }
        if (transCurrInStage != null){
            transCurrInStage.close();
            transCurrInStage = null;
        }
        if (transCurrOutStage != null){
            transCurrOutStage.close();
            transCurrOutStage = null;
        }
        if (allTransStage != null){
            allTransStage.close();
            allTransStage = null;
        }
        if (latestRatesStage != null){
            latestRatesStage.close();
            latestRatesStage = null;
        }
        if (aboutStage != null){
            aboutStage.close();
            aboutStage = null;
        }
        
    }
}
