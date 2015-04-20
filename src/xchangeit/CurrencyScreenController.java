/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class CurrencyScreenController extends XchController
{

    XchangeitDatabase DataBase;     

    @FXML TableView<CurrencyProperty> currencyTable;
    
    @FXML TableColumn<CurrencyProperty, Integer> pkCol;
    @FXML TableColumn<CurrencyProperty, String> nameCol;
    @FXML TableColumn<CurrencyProperty, String> isoCol;
    @FXML TableColumn<CurrencyProperty, String> symbolCol;
    @FXML TableColumn<CurrencyProperty, String> noteCol;
    
    ArrayList<Currency> allCureency;
    ObservableList<CurrencyProperty> allCurrencyProperty = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try{
            if (MainScreen != null){
                DataBase = MainScreen.getDatabase();
                allCureency = DataBase.getAllCurrency();

        //TODEL
    //            allCurrencyProperty.add(new CurrencyProperty(1,"c name","AED","#","",false));
    //            allCurrencyProperty.add(new CurrencyProperty(2,"dollar","USD","$","note $",false));
    //            allCurrencyProperty.add(new CurrencyProperty(3,"euro","EUR","€","note £",false));

                for(Currency c:allCureency){
                    allCurrencyProperty.add(new CurrencyProperty(c));
                }

                pkCol.setCellValueFactory(cellData -> cellData.getValue().getPkProperty().asObject());
                nameCol.setCellValueFactory(cellData -> cellData.getValue().getCurrNameProperty());
                isoCol.setCellValueFactory(cellData -> cellData.getValue().getIsoSymbolProperty());
                symbolCol.setCellValueFactory(cellData -> cellData.getValue().getSymbolProperty());
                noteCol.setCellValueFactory(cellData -> cellData.getValue().getNoteProperty());
//TODEL
                //        pkCol.setCellValueFactory(new PropertyValueFactory<CurrencyProperty, Integer>("pkCol"));
        //        nameCol.setCellValueFactory(new PropertyValueFactory<CurrencyProperty, String>("nameCol"));
        //        isoCol.setCellValueFactory(new PropertyValueFactory<CurrencyProperty, String>("isoCol"));
        //        symbolCol.setCellValueFactory(new PropertyValueFactory<CurrencyProperty, String>("symbolCol"));
        //        noteCol.setCellValueFactory(new PropertyValueFactory<CurrencyProperty, String>("noteCol"));

                currencyTable.setItems(allCurrencyProperty);
            }
            
        }catch(Exception ex) {
            
        }
    }
    
}
