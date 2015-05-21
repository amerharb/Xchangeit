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
    
    @FXML
    private void handleNowDateTimeAction(ActionEvent event){
        transDateText.setText(java.sql.Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    @FXML
    private void handleGetLatestRateAction(ActionEvent event){
        
        Currency c = currChoiceBox.getValue();
        if (c != null){
            Rate r = database.getLatestRate(c);
            if (r != null){
                rateText.setText(r.getRateAsString());
            }
        }
    }
    
    @FXML
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Curr In Screen Add Button");
        try{
            Timestamp st = getTimeStamp(transDateText.getText()); 
            CurrIn ci = new CurrIn(0, st, noteText.getText(), currChoiceBox.getValue(), currAmtText.getText(), rateText.getText());
            if (database.addTrans(ci)){
                clearFields();
            }
            
        }catch(Exception ex){
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
