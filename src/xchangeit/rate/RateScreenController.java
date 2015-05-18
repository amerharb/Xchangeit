/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.rate;

import java.net.URL;

import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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

    RateProperty selRateProp;
    
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
                
                rateDateText.clear(); 
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
                currChoiceBox.setItems(database.getLastGrabedCurrencyProperty());
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void fillRateTable(){
        try{
            if (MainScreen != null){
                database.getAllRate();

                if (database.getLastGrabedRate() != null) {

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
        
        System.out.println("You Click RateScreen Add Button");
        try{
            //TODO fix the value of PK by retreve it from database later, check the value if string is empty, 
            Timestamp st = getTimeStamp(rateDateText.getText());
            Rate r = new Rate(0, st, currChoiceBox.getValue(), rateText.getText(), sellPriceText.getText(), buyPriceText.getText(), noteText.getText());
            database.addRate(r);
            fillRateTable();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void handleDeleteRateAction(ActionEvent event){
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
