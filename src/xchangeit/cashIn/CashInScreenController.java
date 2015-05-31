/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.cashIn;

import java.sql.Timestamp;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import xchangeit.XchController;
import xchangeit.XchDatabase;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class CashInScreenController extends XchController
{
    @FXML private TextField transDateText;
    @FXML private TextField cashAmtText;
    @FXML private TextArea noteText;
    
    @FXML
    private void handleNowDateTimeAction(ActionEvent event){
        transDateText.setText(java.sql.Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    @FXML
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Cash In Screen Add Button");
        try{
            if(String.valueOf(cashAmtText.getText()).isEmpty() ){
                showMessage("field cannot be empty!");
                shakeControl(cashAmtText);
            }else if(Integer.valueOf(cashAmtText.getText())<=0 ){
                showMessage("enter cash please!");
                shakeControl(cashAmtText);
            }else{
                Timestamp st = getTimeStamp(transDateText.getText());
                CashIn ci = new CashIn(0, st, noteText.getText(), cashAmtText.getText());  
                if (database.addTrans(ci)){
                    clearFields();
                    showMessage(null);
                }    
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("error");
        }
    }
    
    private void clearFields()
    {
        transDateText.clear();
        cashAmtText.clear();
        noteText.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //TODO
    }    
    
}
