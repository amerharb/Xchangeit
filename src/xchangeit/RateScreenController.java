/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class RateScreenController implements Initializable
{

    @FXML TableView<CurrencyProperty> rateTable;
    
    @FXML TableColumn<CurrencyProperty, Integer> pkCol;
    @FXML TableColumn<CurrencyProperty, String> nameCol;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        ObservableList<CurrencyProperty> o = FXCollections.observableArrayList();

        CurrencyProperty scp;
        
        scp = new CurrencyProperty(1, "name", "iso", "sym", "note", false);
        o.add(scp);
        
        scp = new CurrencyProperty(2, "name2", "iso2", "sym2", "note2", false);
        o.add(scp);
        SimpleStringProperty s;
        
        for (CurrencyProperty c:o){
            System.out.println("test:" + c.toString());
        }
        
        //pkCol.setCellValueFactory(new PropertyValueFactory<CurrencyProperty, Integer>("pkCol"));
        //nameCol.setCellValueFactory(new PropertyValueFactory<CurrencyProperty, String>("nameCol"));
        pkCol.setCellValueFactory(cellData -> cellData.getValue().getPkProperty().asObject());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().getCurrNameProperty());
        rateTable.setItems(o);

    }    
    
}
