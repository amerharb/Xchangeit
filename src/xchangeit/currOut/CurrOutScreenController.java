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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import xchangeit.XchController;
import xchangeit.XchDatabase;
import xchangeit.currency.CurrencyProperty;

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
    
    @FXML
    private void handleNowDateTimeAction(ActionEvent event){
        transDateText.setText(java.sql.Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    @FXML
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Curr Out Screen Add Button");
        try{
            Timestamp st = getTimeStamp(transDateText.getText()); 
            CurrOut co = new CurrOut(0, st, noteText.getText(), currChoiceBox.getValue(), currAmtText.getText(), rateText.getText());
            database.addTrans(co);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        currChoiceBox.setItems(database.getAllCurrencyProperty());
    }    
    
}
