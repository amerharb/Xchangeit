/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.sell;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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
public class SellScreenController extends XchController
{

    @FXML private TextField transDateText;
    @FXML private TextField cashText;
    @FXML private ChoiceBox<CurrencyProperty> currChoiceBox;
    @FXML private TextField currAmtText;
    @FXML private TextField rateText;
    @FXML private TextField SellBuyPriceText;
    @FXML private TextArea noteText;
    
    @FXML
    private void handleNowDateTimeAction(ActionEvent event){
        transDateText.setText(java.sql.Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    @FXML
    private void handleGetLatestRateAction(ActionEvent event){
        
        Currency c = currChoiceBox.getValue();
        if (c != null){
            Rate r = database.getLatesRate(c);
            if (r != null){
                rateText.setText(r.getRateAsString());
                SellBuyPriceText.setText(r.getSellPriceAsString());
            }
        }
    }
    
    @FXML
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Buy Screen Add Button");
        try{
            Timestamp st = getTimeStamp(transDateText.getText()); 
            Sell s = new Sell(0, st, noteText.getText(), currChoiceBox.getValue(),
                    currAmtText.getText(), rateText.getText(), cashText.getText(), SellBuyPriceText.getText());
            if (database.addTrans(s)){
                clearFields();
            }
            
        }catch(Exception ex){
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
