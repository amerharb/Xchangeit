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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import static xchangeit.XchController.database;
import static xchangeit.XchController.mainScreen;
import xchangeit.rate.Rate;
import xchangeit.rate.RateProperty;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class LatestRatesScreenController implements Initializable
{

    @FXML private TextArea latestRatesText;
    @FXML TableView<RateProperty> latestRatesTable;
    
    
    @FXML private TableColumn<RateProperty , String> isoCol;
    @FXML private TableColumn<RateProperty , Double> ratesCol;
    @FXML private TableColumn<RateProperty , Double> sellCol;
    @FXML private TableColumn<RateProperty , Double> buyCol;
    @FXML private TableColumn<RateProperty , String> notCol;
    
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
        fillRateTable();
    }
    
    private void fillRateTable(){
        try{
            //if (mainScreen != null){

              //  pkCol.setCellValueFactory(cellData -> cellData.getValue().getPkProperty().asObject());
                isoCol.setCellValueFactory(cellData -> cellData.getValue().getCurrProperty().getIsoSymbolProperty());
//                currCol.setCellValueFactory(cellData -> cellData.getValue().getCurrProperty().getCurrNameProperty());
                ratesCol.setCellValueFactory(cellData -> cellData.getValue().getRateProperty().asObject());
                sellCol.setCellValueFactory(cellData -> cellData.getValue().getSellPriceProperty().asObject());
                buyCol.setCellValueFactory(cellData -> cellData.getValue().getBuyPriceProperty().asObject());
                notCol.setCellValueFactory(cellData -> cellData.getValue().getNoteProperty());

                latestRatesTable.setItems(database.getLatestRateProperty());
            //}
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
