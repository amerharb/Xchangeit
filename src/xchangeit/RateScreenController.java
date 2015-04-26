/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static xchangeit.XchController.MainScreen;

/**
 * FXML Controller class
 *
 * @author Amer
 */
public class RateScreenController implements Initializable
{

    XchDatabase DataBase;     

    @FXML TableView<RateProperty> rateTable;
    
    @FXML TableColumn<RateProperty, Integer> pkCol;
    @FXML TableColumn<RateProperty, Date> rateDateCol;
    @FXML TableColumn<RateProperty, String> currCol;
    @FXML TableColumn<RateProperty, Double> rateCol;
    @FXML TableColumn<RateProperty, Double> sellPriceCol;
    @FXML TableColumn<RateProperty, Double> buyPriceCol;
    @FXML TableColumn<RateProperty, String> noteCol;
    
    ArrayList<Rate> allRate;
    ObservableList<RateProperty> allRateProperty = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try{
            if (MainScreen != null){
                DataBase = MainScreen.getDatabase();
                allRate = DataBase.getAllRate();

                if (allRate != null) {
                for(Rate r:allRate){
                    allRateProperty.add(new RateProperty(r));
                }
                    pkCol.setCellValueFactory(cellData -> cellData.getValue().getPkProperty().asObject());
                    rateDateCol.setCellValueFactory(cellData -> cellData.getValue().getRateDateProperty());
                    currCol.setCellValueFactory(cellData -> cellData.getValue().getCurrProperty().getCurrNameProperty());
                    rateCol.setCellValueFactory(cellData -> cellData.getValue().getRateProperty().asObject());
                    sellPriceCol.setCellValueFactory(cellData -> cellData.getValue().getSellPriceProperty().asObject());
                    buyPriceCol.setCellValueFactory(cellData -> cellData.getValue().getBuyPriceProperty().asObject());
                    noteCol.setCellValueFactory(cellData -> cellData.getValue().getNoteProperty());

                    rateTable.setItems(allRateProperty);
                }
            }
            
        }catch(Exception ex) {
            ex.printStackTrace();
        }

    }    
    
}
