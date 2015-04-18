/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class CurrencyScreenController extends XchController
{

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView currencTable;
    XchangeitDatabase DataBase;
    
    @FXML TableColumn pkCol;
    @FXML TableColumn nameCol;
    @FXML TableColumn isoCol;
    @FXML TableColumn symbolCol;
    @FXML TableColumn noteCol;
    
    ArrayList<Currency> allCureency;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        DataBase = MainScreen.getDatabase();
        allCureency = DataBase.getAllCurrency();
        fillTable();
    }
    
    private void fillTable(){
        //TODO
        for(Currency c:allCureency){
            System.out.println(c.getCurrName());
        }
    }
    
}
