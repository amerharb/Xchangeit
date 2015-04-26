package xchangeit;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CurrencyScreenController extends XchController
{

    XchDatabase DataBase;     

    @FXML TableView<CurrencyProperty> currencyTable;
    
    @FXML TableColumn<CurrencyProperty, Integer> pkCol;
    @FXML TableColumn<CurrencyProperty, String> currNameCol;
    @FXML TableColumn<CurrencyProperty, String> isoCol;
    @FXML TableColumn<CurrencyProperty, String> symbolCol;
    @FXML TableColumn<CurrencyProperty, String> noteCol;
    @FXML TableColumn<CurrencyProperty, String> inactiveCol;
    
    @FXML TextField currNameText;
    @FXML TextField isoSymbolText;
    @FXML TextField symbolText;
    @FXML TextArea noteText;
    @FXML CheckBox inactiveCheck;
    
    @FXML Button updateButton;
    @FXML Button newButton;
    @FXML Button deleteButton;
    
    ArrayList<Currency> allCureency;
    ObservableList<CurrencyProperty> allCurrencyProperty = FXCollections.observableArrayList();

    CurrencyProperty selCurrencyProp;
    
    @FXML
    private void handleAddCurrencyAction(ActionEvent event)
    {
        
        System.out.println("You Click Currency Screen Add Button");
        try{
            //TEST
            allCurrencyProperty.add(new CurrencyProperty(33, "name", "sym", "Sym", "note", false));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void rowClickAction(MouseEvent event)
    {
        
        System.out.println("You Click on Table");
        try{

            fillFields();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void fillFields(){
        try{

            selCurrencyProp = currencyTable.getSelectionModel().getSelectedItem();
            if (selCurrencyProp != null){
                currNameText.setText(selCurrencyProp.getCurrName());
                isoSymbolText.setText(selCurrencyProp.getIsoSymbol());
                symbolText.setText(selCurrencyProp.getSymbol());
                noteText.setText(selCurrencyProp.getNote());
                inactiveCheck.setSelected(selCurrencyProp.isInactive());
                
            }else{
                currNameText.clear();
                isoSymbolText.clear();
                symbolText.clear();
                noteText.clear();
                inactiveCheck.setSelected(false);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void handelDeleteCurrencyAction(ActionEvent event)
    {
        
        System.out.println("You Click delete currency");
        try{
            if (selCurrencyProp != null){
                if (DataBase.delCurrencyByPK(selCurrencyProp.getPk())){
                    fillCurrencyTable();
                }
            }
            fillFields();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handelUpdateCurrencyAction(ActionEvent event)
    {
        
        System.out.println("You Click xxx");
        try{

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void xxxAction(ActionEvent event)
    {
        
        System.out.println("You Click xxx");
        try{

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle rb)
    {
        try{
            fillCurrencyTable();
            fillFields();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void fillCurrencyTable()
    {
        try{
            if (MainScreen != null){
                DataBase = MainScreen.getDatabase();
                allCureency = DataBase.getAllCurrency();

                if (allCureency != null){
                    allCurrencyProperty = FXCollections.observableArrayList();
                    for(Currency c:allCureency){
                        allCurrencyProperty.add(new CurrencyProperty(c));
                    }

                    pkCol.setCellValueFactory(cellData -> cellData.getValue().getPkProperty().asObject());
                    currNameCol.setCellValueFactory(cellData -> cellData.getValue().getCurrNameProperty());
                    isoCol.setCellValueFactory(cellData -> cellData.getValue().getIsoSymbolProperty());
                    symbolCol.setCellValueFactory(cellData -> cellData.getValue().getSymbolProperty());
                    noteCol.setCellValueFactory(cellData -> cellData.getValue().getNoteProperty());

                    currencyTable.setItems(allCurrencyProperty);
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
