/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.currIn;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import xchangeit.XchController;
import xchangeit.currency.Currency;
import xchangeit.currency.CurrencyProperty;
import xchangeit.rate.Rate;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class CurrInScreenController extends XchController
{

    @FXML private TextField transDateText;
    @FXML private ChoiceBox<CurrencyProperty> currChoiceBox;
    @FXML private TextField currAmtText;
    @FXML private TextField rateText;
    @FXML private TextArea noteText;
    @FXML private Label warningLabel;
    
    @FXML
    private void handleNowDateTimeAction(ActionEvent event){
        transDateText.setText(java.sql.Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    @FXML
    private void handleGetLatestRateAction(ActionEvent event) throws InterruptedException{
        
        Currency c = currChoiceBox.getValue();
        if (c == null){
            warningLabel.setText("choose currency");
            shakeControl(currChoiceBox);
            }
        if (c != null){
            Rate r = database.getLatestRate(c);
            if (r != null){
                rateText.setText(r.getRateAsString());
            }
            warningLabel.setText("added");
        }
    }
    
    @FXML
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Curr In Screen Add Button");
        try{
            if(currChoiceBox.getValue()==null) {
                warningLabel.setText("choose currency please!");
                shakeControl(currChoiceBox);
            } else if(String.valueOf(currAmtText.getText()).isEmpty()){      //|| Integer.valueOf(currAmtText.getText())<=0 ){
                warningLabel.setText("empty field!");
                shakeControl(currAmtText);
            } else if(Integer.valueOf(currAmtText.getText()) <=0){
                warningLabel.setText("enter positive amount");
                shakeControl(currAmtText);
            } else if(String.valueOf(rateText.getText()).isEmpty()){
                 warningLabel.setText("click on R button!");
                 shakeControl(rateText);
            }else{
                Timestamp st = getTimeStamp(transDateText.getText()); 
                CurrIn ci = new CurrIn(0, st, noteText.getText(), currChoiceBox.getValue(), currAmtText.getText(), rateText.getText());
                if (database.addTrans(ci)){
                    clearFields();
                    warningLabel.setText(null);
                }  
            }
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
    }
    
    private void clearFields()
    {
        transDateText.clear();
        currChoiceBox.setValue(null);
        currAmtText.clear();
        rateText.clear();
        noteText.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        currChoiceBox.setItems(database.getAllCurrencyProperty());
    }    
    
}
