/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import static xchangeit.XchController.database;
import xchangeit.rate.Rate;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class LatestRatesScreenController implements Initializable
{

    @FXML private TextArea latestRatesText;
    
    @FXML
    public void handleTextAreaAction(ActionEvent event){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        String s="\n";
        for (Rate r:database.getLatestRate()){
            s += ((r.getCurr() == null) ? "   " : r.getCurr().getIsoSymbol()) + " -- " + r.getRate() + " -- " + r.getSellPriceAsString() + " -- " + r.getBuyPriceAsString() + "\n";
        }
        latestRatesText.setText(s);
    }    
    
}
