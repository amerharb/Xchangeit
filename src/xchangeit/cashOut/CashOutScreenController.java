/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.cashOut;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import xchangeit.XchController;
import xchangeit.XchDatabase;
import xchangeit.cashIn.CashIn;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class CashOutScreenController extends XchController
{
    private XchDatabase database;
    
    @FXML private TextField transDateText;
    @FXML private TextField cashAmtText;
    @FXML private TextArea noteText;
    
    @FXML
    private void handleAddAction(ActionEvent event){
        
        System.out.println("You Click Cash Out Screen Add Button");
        try{
            Timestamp st = getTimeStamp(transDateText.getText());
            CashOut co = new CashOut(0, st, noteText.getText(), cashAmtText.getText());
            database.addTrans(co);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        database = MainScreen.getDatabase();
    }    
    
}
