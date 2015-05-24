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
    @FXML TableView<TransactionProperty> allTransTable;
    
    @FXML private TableColumn<TransactionProperty, Integer> pkCol;
    @FXML private TableColumn<TransactionProperty , String> transDateCol;
    @FXML private TableColumn<TransactionProperty , String> transTypeCol;
    @FXML private TableColumn<TransactionProperty , Double> cashCol;
    @FXML private TableColumn<TransactionProperty , String> currCol;
    @FXML private TableColumn<TransactionProperty , Double> currAmtCol;
    @FXML private TableColumn<TransactionProperty , Double> rateCol;
    @FXML private TableColumn<TransactionProperty , Double> sellBuyPriceCol;
    @FXML private TableColumn<TransactionProperty , String> noteCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        String s="\n";
        for (XchTransactoinInterface t:database.getAllTrans()){
            s += t.getPk() + "-- " + t.getTransType().name() + "--" + t.getTransDate().toString() + "--" +
                 t.getSellBuyPrice()+ "--" + t.getCash() + "--" + t.getNote() + "\n";

        }
        allTransText.setText(s);
        fillCurrencyTable();
    }
    
     private void fillCurrencyTable(){
        try{
            pkCol.setCellValueFactory(cellData -> cellData.getValue().getPkProperty().asObject());
            transDateCol.setCellValueFactory(cellData -> cellData.getValue().getTransDateProperty());
            transTypeCol.setCellValueFactory(cellData -> cellData.getValue().getTransTypeProperty());
            cashCol.setCellValueFactory(cellData -> cellData.getValue().getCashProperty().asObject());
            
            currCol.setCellValueFactory(cellData -> (cellData.getValue().getCurrProperty() != null) ? cellData.getValue().getCurrProperty().getCurrNameProperty():null );
            currAmtCol.setCellValueFactory(cellData -> cellData.getValue().getCurrAmtProperty().asObject());
            rateCol.setCellValueFactory(cellData -> cellData.getValue().getRateProperty().asObject());
            sellBuyPriceCol.setCellValueFactory(cellData -> cellData.getValue().getSellBuyPriceProperty().asObject());
            noteCol.setCellValueFactory(cellData -> cellData.getValue().getNoteProperty());

            allTransTable.setItems(database.getAllTransProperty());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
