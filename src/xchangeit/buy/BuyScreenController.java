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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import xchangeit.XchController;
import xchangeit.currIn.CurrIn;
import xchangeit.currency.CurrencyProperty;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class BuyScreenController extends XchController
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
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Buy Screen Add Button");
        try{
            Timestamp st = getTimeStamp(transDateText.getText()); 
            Buy b = new Buy(0, st, noteText.getText(), currChoiceBox.getValue(), 
                    currAmtText.getText(), rateText.getText(), cashText.getText(), SellBuyPriceText.getText());
            database.addTrans(b);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        if (database.getAllCurrencyProperty() == null || database.getAllCurrencyProperty().isEmpty()) {
            database.getAllCurrency();
        }
        currChoiceBox.setItems(database.getAllCurrencyProperty());
    }    
    
}
