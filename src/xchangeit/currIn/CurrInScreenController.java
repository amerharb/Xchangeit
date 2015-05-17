/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.currIn;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import xchangeit.XchController;
import xchangeit.XchDatabase;
import xchangeit.currIn.CurrIn;
import xchangeit.currency.CurrencyProperty;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class CurrInScreenController extends XchController
{
    private XchDatabase database;
    
    @FXML private TextField transDateText;
    @FXML private ChoiceBox<CurrencyProperty> currChoiceBox;
    @FXML private TextField currAmtText;
    @FXML private TextField rateText;
    @FXML private TextArea noteText;
    
    @FXML
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Curr In Screen Add Button");
        try{
            Timestamp st = getTimeStamp(transDateText.getText()); 
            CurrIn ci = new CurrIn(0, st, noteText.getText(), currChoiceBox.getValue(), currAmtText.getText(), rateText.getText());
            database.addTrans(ci);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        database = MainScreen.getDatabase();
        if (database.getLastGrabedCurrencyProperty() == null || database.getLastGrabedCurrencyProperty().isEmpty()) {
            database.getAllCurrency();
        }
        currChoiceBox.setItems(database.getLastGrabedCurrencyProperty());
    }    
    
}
