/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.buy;

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
import xchangeit.XchNumberField;
import xchangeit.currency.Currency;
import xchangeit.currency.CurrencyProperty;
import xchangeit.rate.Rate;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class BuyScreenController extends XchController
{
    @FXML private TextField transDateText;
    @FXML private XchNumberField cashText;
    @FXML private ChoiceBox<CurrencyProperty> currChoiceBox;
    @FXML private XchNumberField currAmtText;
    @FXML private XchNumberField rateText;
    @FXML private XchNumberField SellBuyPriceText;
    @FXML private TextArea noteText;
    
    @FXML
    private void handleNowDateTimeAction(ActionEvent event){
        transDateText.setText(java.sql.Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    @FXML
    private void handleGetLatestRateAction(ActionEvent event) throws InterruptedException{
        
        Currency c = currChoiceBox.getValue();
        if (c == null){
            showMessage("choose currency",XchMessageType.XchWarrning);
            shakeControl(currChoiceBox);
        }else{
            showMessage(null);
        }
        
        if (c != null){
            Rate r = database.getLatestRate(c);
            if (r != null){
                rateText.setText(r.getRateAsString());
                SellBuyPriceText.setText(r.getBuyPriceAsString());
                if (cashText.getText().isEmpty()){
                    try {
                        cashText.setText(String.valueOf(r.getBuyPrice() * Double.valueOf(currAmtText.getText())));
                    }catch(Exception ex){
                        //do nothing
                    }
                }
            }
        }
    }
    
    @FXML
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Buy Screen Add Button");
        try{
            if(currChoiceBox.getValue()==null ){
                showMessage("choose currency please!", XchMessageType.XchWarrning);
                shakeControl(currChoiceBox);
            }
            else if(String.valueOf(currAmtText.getText()).isEmpty() ){
                showMessage("currency amount",XchMessageType.XchWarrning);
                shakeControl(currAmtText);
            }
            else if(String.valueOf(cashText.getText()).isEmpty() ){
                showMessage("symbol please",XchMessageType.XchWarrning);
                shakeControl(cashText);
            }
            else if(String.valueOf(rateText.getText()).isEmpty()){
                showMessage("Enter Rate... try click the R button",XchMessageType.XchWarrning);
                shakeControl(rateText);
            }
            else if(String.valueOf(SellBuyPriceText.getText()).isEmpty()){
                showMessage("click the R button",XchMessageType.XchWarrning);
                shakeControl(SellBuyPriceText);
            }else{
                Timestamp st = getTimeStamp(transDateText.getText()); 
                Buy b = new Buy(0, st, noteText.getText(), currChoiceBox.getValue(), 
                currAmtText.getText(), rateText.getText(), cashText.getText(), SellBuyPriceText.getText());
                if (database.addTrans(b)){
                    clearFields();
                    showMessage("Buy added",XchMessageType.XchInfo);
                }   
            }
        }catch(Exception ex){
            System.out.println("system error");
            ex.printStackTrace();
        }
    }

    private void clearFields()
    {
        transDateText.clear();
        cashText.clear();
        currChoiceBox.setValue(null);
        currAmtText.clear();
        rateText.clear();
        SellBuyPriceText.clear();
        noteText.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        currChoiceBox.setItems(database.getAllCurrencyProperty());
    }    
    
}
