/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import xchangeit.rate.Rate;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class AllTransScreenController extends XchController
{

    /**
     * Initializes the controller class.
     */
   // @FXML private TextArea allTransText;
    @FXML TableView transTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        String s="\n";
        for (XchTransactoinInterface t:database.getAllTrans()){
            s += t.getPk() + " " + t.getTransType().name() + " " + t.getTransDate().toString() + " " + t.getSellBuyPrice()+ " " + t.getCash() + " " + t.getNote() + "\n";
        }
      //  allTransText.setText(s);
    }    
    
}
