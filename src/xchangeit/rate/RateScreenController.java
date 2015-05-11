/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.rate;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import xchangeit.XchController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import xchangeit.*;
import xchangeit.currency.Currency;
import xchangeit.currency.CurrencyProperty;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class RateScreenController extends XchController{

    XchDatabase database;     

    @FXML TableView<RateProperty> rateTable;
    
    @FXML TableColumn<RateProperty, Integer> pkCol;
    @FXML TableColumn<RateProperty, Date> rateDateCol;
    @FXML TableColumn<RateProperty, String> currCol;
    @FXML TableColumn<RateProperty, Double> rateCol;
    @FXML TableColumn<RateProperty, Double> sellPriceCol;
    @FXML TableColumn<RateProperty, Double> buyPriceCol;
    @FXML TableColumn<RateProperty, String> noteCol;
    
    @FXML DatePicker rateDateDatePicker;
    @FXML ChoiceBox<CurrencyProperty> currChoiceBox;
    @FXML TextField rateText;
    @FXML TextField sellPriceText;
    @FXML TextField buyPriceText;
    @FXML TextArea noteText;

//    ArrayList<Rate> allRate;
    //ArrayList<Currency> allCurrency;
    
    //ObservableList<RateProperty> allRateProperty = FXCollections.observableArrayList();
    //ObservableList<CurrencyProperty> allCurrencyProperty = FXCollections.observableArrayList();

    RateProperty selRateProp;
    
    private void fillRateFields(){
        try{

            selRateProp = rateTable.getSelectionModel().getSelectedItem();
            if (selRateProp != null){
                //TODO fill date and currency
                currChoiceBox.setValue(selRateProp.getCurrProperty());
                rateText.setText(selRateProp.getRateAsString());
                sellPriceText.setText(selRateProp.getSellPriceAsString());
                buyPriceText.setText(selRateProp.getBuyPriceAsString());
                noteText.setText(selRateProp.getNote());
                
            }else{
                
                //rateDateDatePicker.valueProperty() = New ObjectProperty<LocalDateTime.now()>; 
                currChoiceBox.setValue(null);
                rateText.clear();
                sellPriceText.clear();
                buyPriceText.clear();
                noteText.clear();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void fillCurrChoiceBox(){        
        try{
            if (database.getLastGrabedRate() != null) {
                
//                allCurrency = new ArrayList<Currency>();
//                for (Rate r:allRate){
//                    if (!allCurrency.contains(r.getCurr()))
//                        allCurrency.add(r.getCurr());
//                }
                //allCurrency = database.getAlreadyLastCurrency();
                //this proc is now done inside XchDatabase
//                allCurrencyProperty = FXCollections.observableArrayList();
//                for(Currency c:allCurrency){
//                    allCurrencyProperty.add(new CurrencyProperty(c));
//                }
                currChoiceBox.setItems(database.getLastGrabedCurrencyProperty());
           }
            
//            if (MainScreen != null){
//                database = MainScreen.getDatabase();
//                allCurrency = database.getAllCurrency();
//
//                if (allCurrency != null) {
//                    allCurrencyProperty = FXCollections.observableArrayList();
//                    for(Currency c:allCurrency){
//                        allCurrencyProperty.add(new CurrencyProperty(c));
//                    }
//                    
//                    currChoiceBox.setItems(allCurrencyProperty);
//                }
//            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void fillRateTable(){
        try{
            if (MainScreen != null){
                database = MainScreen.getDatabase();
                database.getAllRate();

                if (database.getLastGrabedRate() != null) {
                    //this done from insdie the XchDatabase
//                    allRateProperty = FXCollections.observableArrayList();
//                    for(Rate r:allRate){
//                        allRateProperty.add(new RateProperty(r));
//                    }
                    
                    pkCol.setCellValueFactory(cellData -> cellData.getValue().getPkProperty().asObject());
                    rateDateCol.setCellValueFactory(cellData -> cellData.getValue().getRateDateProperty());
                    currCol.setCellValueFactory(cellData -> cellData.getValue().getCurrProperty().getCurrNameProperty());
                    rateCol.setCellValueFactory(cellData -> cellData.getValue().getRateProperty().asObject());
                    sellPriceCol.setCellValueFactory(cellData -> cellData.getValue().getSellPriceProperty().asObject());
                    buyPriceCol.setCellValueFactory(cellData -> cellData.getValue().getBuyPriceProperty().asObject());
                    noteCol.setCellValueFactory(cellData -> cellData.getValue().getNoteProperty());

                    rateTable.setItems(database.getLastGrabedRateProperty());
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleAddRateAction(ActionEvent event){
        //TEST
        System.out.println("You Click RateScreen Add Button");
        try{
            //TODO fix the value of PK by retreve it from database later, check the value if string is empty, 
            //Rate r = new Rate(0, rateDateDatePicker.getValue(), currComboBox.getValue(), rateText.getText(), sellPriceText.getText(), buyPriceText.getText(), noteText.getText());
            Rate r = new Rate(0, new Date(), database.getCurrencyByPK(1), Double.valueOf(rateText.getText()), Double.valueOf(sellPriceText.getText()), Double.valueOf(buyPriceText.getText()), noteText.getText());
            database.addRate(r);
            fillRateTable();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @FXML
    private void handleDeleteRateAction(ActionEvent event){
        //TEST
        System.out.println("You Click RateScreen Delete Button");
        try{
            if (selRateProp != null){
                if (database.delRateByPK(selRateProp.getPk())){
                    fillRateTable();
                }
            }
            fillRateFields();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void rowClickAction(MouseEvent event){
        
        System.out.println("You Click on Table");
        try{
            fillRateFields();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try{
            fillRateTable();
            fillCurrChoiceBox();
        }catch(Exception ex) {
            ex.printStackTrace(); 
        }

    }    
    
}
