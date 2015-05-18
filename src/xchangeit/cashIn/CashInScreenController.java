/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.cashIn;

import java.sql.Timestamp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Cash In Screen Add Button");
        try{
            Timestamp st = getTimeStamp(transDateText.getText());
            CashIn ci = new CashIn(0, st, noteText.getText(), cashAmtText.getText());
            database.addTrans(ci);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //TODO
    }    
    
}
