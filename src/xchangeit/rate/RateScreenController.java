/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.rate;

import java.net.URL;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import xchangeit.XchController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import xchangeit.currency.CurrencyProperty;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class RateScreenController extends XchController{

    @FXML TableView<RateProperty> rateTable;
    
    @FXML private TableColumn<RateProperty, Integer> pkCol;
    @FXML private TableColumn<RateProperty, Timestamp> rateDateCol;
    @FXML private TableColumn<RateProperty, String> currCol;
    @FXML private TableColumn<RateProperty, Double> rateCol;
    @FXML private TableColumn<RateProperty, Double> sellPriceCol;
    @FXML private TableColumn<RateProperty, Double> buyPriceCol;
    @FXML private TableColumn<RateProperty, String> noteCol;
    
    @FXML private TextField rateDateText;
    @FXML private ChoiceBox<CurrencyProperty> currChoiceBox;
    @FXML private TextField rateText;
    @FXML private TextField sellPriceText;
    @FXML private TextField buyPriceText;
    @FXML private TextArea noteText;

    @FXML private Button clearButton;
    
    RateProperty selRateProp;
    
    @FXML
    private void handleNowDateTimeAction(ActionEvent event){
        rateDateText.setText(java.sql.Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    private void fillRateFields(){
        try{

            selRateProp = rateTable.getSelectionModel().getSelectedItem();
            if (selRateProp != null){

                rateDateText.setText(selRateProp.getRateDate().toString());
                currChoiceBox.setValue(selRateProp.getCurrProperty());
                rateText.setText(selRateProp.getRateAsString());
                sellPriceText.setText(selRateProp.getSellPriceAsString());
                buyPriceText.setText(selRateProp.getBuyPriceAsString());
                noteText.setText(selRateProp.getNote());
                
            }else{
                clearFields();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void clearFields()
    {
        rateDateText.clear(); 
        currChoiceBox.setValue(null);
        rateText.clear();
        sellPriceText.clear();
        buyPriceText.clear();
        noteText.clear();
    }
    
    @FXML
    private void clearAction(ActionEvent event){
        
        System.out.println("You Click clear");
        try{
            clearFields();
            showMessage(null);
            System.out.println("all the fields are clreared ");
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void fillRateTable(){
        try{
            if (mainScreen != null){

                pkCol.setCellValueFactory(cellData -> cellData.getValue().getPkProperty().asObject());
                rateDateCol.setCellValueFactory(cellData -> cellData.getValue().getRateDateProperty());
                currCol.setCellValueFactory(cellData -> cellData.getValue().getCurrProperty().getCurrNameProperty());
                rateCol.setCellValueFactory(cellData -> cellData.getValue().getRateProperty().asObject());
                sellPriceCol.setCellValueFactory(cellData -> cellData.getValue().getSellPriceProperty().asObject());
                buyPriceCol.setCellValueFactory(cellData -> cellData.getValue().getBuyPriceProperty().asObject());
                noteCol.setCellValueFactory(cellData -> cellData.getValue().getNoteProperty());

                rateTable.setItems(database.getAllRateProperty());
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleAddRateAction(ActionEvent event){
        
        System.out.println("You Click RateScreen Add Button");
        try{
            //TODO fix the value of PK by retreve it from database later, check the value if string is empty, 
             if(currChoiceBox.getValue()==null) {
                 showMessage("choose currency please!");
                 shakeControl(currChoiceBox);
             } else if(String.valueOf(rateText.getText()).isEmpty() ){
                 showMessage("enter the rate");
                 shakeControl(rateText);
             }
             else if(String.valueOf(sellPriceText.getText()).isEmpty() ){
                 showMessage("selling price");
                 shakeControl(sellPriceText);
             }
             else if(String.valueOf(buyPriceText.getText()).isEmpty() ){
                 showMessage("buying price");
                 shakeControl(buyPriceText);
             }
             else {
                 Timestamp st = getTimeStamp(rateDateText.getText());
                 Rate r = new Rate(0, st, currChoiceBox.getValue(), rateText.getText(), sellPriceText.getText(), buyPriceText.getText(), noteText.getText());
                 if (database.addRate(r) != null){
                     showMessage("rate added",XchMessageType.XchInfo);
                 }
                 //fillRateTable(); no need should be automatic
             } 
            
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("error");
        }
    }
    
    @FXML
    private void handleDeleteRateAction(ActionEvent event){
        try{
            if (selRateProp != null){
                if (database.delRate(selRateProp)){
                    showMessage("deleted",XchMessageType.XchInfo);
                    System.out.println("deleted");
                }
            }
            fillRateFields();
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("error");
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
            currChoiceBox.setItems(database.getAllCurrencyProperty());
        }catch(Exception ex) {
            ex.printStackTrace(); 
        }

    }    

}
