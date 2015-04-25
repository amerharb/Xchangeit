package xchangeit;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CurrencyScreenController extends XchController
{

    XchDatabase DataBase;     

    @FXML TableView<CurrencyProperty> currencyTable;
    
    @FXML TableColumn<CurrencyProperty, Integer> pkCol;
    @FXML TableColumn<CurrencyProperty, String> nameCol;
    @FXML TableColumn<CurrencyProperty, String> isoCol;
    @FXML TableColumn<CurrencyProperty, String> symbolCol;
    @FXML TableColumn<CurrencyProperty, String> noteCol;
    
    ArrayList<Currency> allCureency;
    ObservableList<CurrencyProperty> allCurrencyProperty = FXCollections.observableArrayList();

    @FXML
    private void handleAddCurrencyAction(ActionEvent event)
    {
        
        System.out.println("You Click Currency Screen Add Button");
        try{
            allCurrencyProperty.add(new CurrencyProperty(33, "name", "sym", "Sym", "note", false));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try{
            if (MainScreen != null){
                DataBase = MainScreen.getDatabase();
                allCureency = DataBase.getAllCurrency();

                for(Currency c:allCureency){
                    allCurrencyProperty.add(new CurrencyProperty(c));
                }

                pkCol.setCellValueFactory(cellData -> cellData.getValue().getPkProperty().asObject());
                nameCol.setCellValueFactory(cellData -> cellData.getValue().getCurrNameProperty());
                isoCol.setCellValueFactory(cellData -> cellData.getValue().getIsoSymbolProperty());
                symbolCol.setCellValueFactory(cellData -> cellData.getValue().getSymbolProperty());
                noteCol.setCellValueFactory(cellData -> cellData.getValue().getNoteProperty());

                currencyTable.setItems(allCurrencyProperty);
            }
            
        }catch(Exception ex) {

        }
    }
    
}
