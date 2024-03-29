/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.currOut;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import xchangeit.XchController;
import xchangeit.XchDatabase;
import xchangeit.currency.Currency;
import xchangeit.currency.CurrencyProperty;
import xchangeit.rate.Rate;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class CurrOutScreenController extends XchController
{

    @FXML private TextField transDateText;
    @FXML private ChoiceBox<CurrencyProperty> currChoiceBox;
    @FXML private TextField currAmtText;
    @FXML private TextField rateText;
    @FXML private TextArea noteText;
    @FXML private Button lr;
    
    @FXML
    private void handleNowDateTimeAction(ActionEvent event){
        transDateText.setText(java.sql.Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    @FXML
    private void handleGetLatestRateAction(ActionEvent event) throws InterruptedException{
        Currency c = currChoiceBox.getValue();
        if (c == null){
            showMessage("choose currency");
            shakeControl(currChoiceBox);
        }else{
            Rate r = database.getLatestRate(c);
            if (r != null){
                rateText.setText(r.getRateAsString());
            }
            showMessage(null); 
        }   
    }

    @FXML
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Curr Out Screen Add Button");
        try{
            if(currChoiceBox.getValue()==null) {
                showMessage("choose currency please!");
                shakeControl(currChoiceBox);
            } else if(String.valueOf(currAmtText.getText()).isEmpty() || Integer.valueOf(currAmtText.getText())<=0 ){
                showMessage("amount please!");
                shakeControl(currAmtText);
            } else if(String.valueOf(rateText.getText()).isEmpty()){
                showMessage("enter rate, try click R button!");
                shakeControl(rateText);
            }else{
                Timestamp st = getTimeStamp(transDateText.getText()); 
                CurrOut co = new CurrOut(0, st, noteText.getText(), currChoiceBox.getValue(), currAmtText.getText(), rateText.getText());
                if (database.addTrans(co)){
                    clearFields();
                    showMessage("Currency Out added", XchMessageType.XchInfo);
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