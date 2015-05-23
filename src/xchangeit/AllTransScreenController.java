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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import xchangeit.currency.CurrencyProperty;
import xchangeit.XchTransactoinInterface;
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
    @FXML private TextArea allTransText;
    @FXML TableView<XchTransactoinInterface> transTable;
    //@FXML private TableView<CurrencyProperty> transTable;
    
    @FXML private TableColumn<XchTransactoinInterface, Integer> pkCol;
    @FXML private TableColumn<XchTransactoinInterface , String> transCol;
    private Object XchTransactionInterface;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        String s="\n";
//        for (XchTransactoinInterface t:database.getAllTrans()){
//            s += t.getPk() + "-- " + t.getTransType().name() + "--" + t.getTransDate().toString() + "--" +
//                 t.getSellBuyPrice()+ "--" + t.getCash() + "--" + t.getNote() + "\n";
//
//        }
//        allTransText.setText(s);
        // i didn,t remove the text area still we do it later maybe when table view will work
    }
    
     private void fillCurrencyTable(){
        try{
           
            pkCol.setCellValueFactory(cellData -> cellData.getValue().getPk().asObject());
            transCol.setCellValueFactory(cellData -> cellData.getValue().getTransType().name());
//            isoCol.setCellValueFactory(cellData -> cellData.getValue().getIsoSymbolProperty());
//            symbolCol.setCellValueFactory(cellData -> cellData.getValue().getSymbolProperty());
//            noteCol.setCellValueFactory(cellData -> cellData.getValue().getNoteProperty());

            transTable.setItems(database.getAllTrans);
             //currencyTable.setItems(database.getAllCurrencyProperty());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
