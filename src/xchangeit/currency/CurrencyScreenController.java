package xchangeit.currency;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import xchangeit.XchController;
import xchangeit.XchDatabase;

public class CurrencyScreenController extends XchController
{

    @FXML private TableView<CurrencyProperty> currencyTable;
    
    @FXML private TableColumn<CurrencyProperty, Integer> pkCol;
    @FXML private TableColumn<CurrencyProperty, String> currNameCol;
    @FXML private TableColumn<CurrencyProperty, String> isoCol;
    @FXML private TableColumn<CurrencyProperty, String> symbolCol;
    @FXML private TableColumn<CurrencyProperty, String> noteCol;
    @FXML private TableColumn<CurrencyProperty, String> inactiveCol;
    
    @FXML private TextField currNameText;
    @FXML private TextField isoSymbolText;
    @FXML private TextField symbolText;
    @FXML private TextArea noteText;
    @FXML private CheckBox inactiveCheck;
    
    @FXML private Button updateButton;
    @FXML private Button newButton;
    @FXML private Button deleteButton;
    @FXML private Button clearButton;
    
//    ArrayList<Currency> allCureency;
//    ObservableList<CurrencyProperty> allCurrencyProperty = FXCollections.observableArrayList();

    CurrencyProperty selCurrencyProp;
    
    @FXML
    private void handleAddCurrencyAction(ActionEvent event){
        
        System.out.println("You Click Currency Screen Add Button");
        try{
            if(String.valueOf(currNameText.getText()).isEmpty() ){
                showMessage("currency name please!");
                shakeControl(currNameText);
            }
            else if(String.valueOf(isoSymbolText.getText()).isEmpty() ){
                showMessage("iso symbol please");
                shakeControl(isoSymbolText);
            }
            else if(String.valueOf(symbolText.getText()).isEmpty() ){
                showMessage("symbol please");
                shakeControl(symbolText);
            }
            else{
                Currency c = new Currency(0, currNameText.getText(), isoSymbolText.getText(), symbolText.getText(), noteText.getText(), false);
                if (database.addCurrency(c) != null){
                    showMessage("Added",XchMessageType.XchInfo);
                }else{
                    showMessage("Not Added there was an Error");
                }
            }   
        }catch(Exception ex){
            showMessage(ex.toString());
        }
    }

    @FXML
    private void rowClickAction(MouseEvent event){
        
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
                clearFields();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void clearFields()
    {
        currNameText.clear();
        isoSymbolText.clear();
        symbolText.clear();
        noteText.clear();
        inactiveCheck.setSelected(false);
    }

    private void updateCurrencyFromTextFields(CurrencyProperty c){
        try{
            
            c.getCurrNameProperty().set(currNameText.getText());
            c.getIsoSymbolProperty().set(isoSymbolText.getText());
            c.getSymbolProperty().set(symbolText.getText());
            c.getNoteProperty().set(noteText.getText());
            c.setInactive(false); //currency no need to update this value its hidden

        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("system error");
        }
    }
    
    @FXML
    private void handelDeleteCurrencyAction(ActionEvent event)
    {
        
        System.out.println("You Click delete currency");
        try{
            if (selCurrencyProp != null){
                if (database.delCurrency(selCurrencyProp)){
                    showMessage("currency has been deleted",XchMessageType.XchInfo);
                    System.out.println("currency deleted");
                }
            }
            fillFields();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handelUpdateCurrencyAction(ActionEvent event){
        
        System.out.println("You Click update Currency");
        try{
            if(String.valueOf(currNameText.getText()).isEmpty() ){
                showMessage("currency name please!");
                shakeControl(currNameText);
            }
            else if(String.valueOf(isoSymbolText.getText()).isEmpty() ){
                showMessage("iso symbol please");
                shakeControl(isoSymbolText);
            }
            else if(String.valueOf(symbolText.getText()).isEmpty() ){
                showMessage("symbol please");
                shakeControl(symbolText);
            }
            else {
                updateCurrencyFromTextFields(selCurrencyProp);
                if (database.updateCurrency(selCurrencyProp)){
                    showMessage("currency updated",XchMessageType.XchInfo);
                }else{
                    showMessage("not update, there is error");
                }
            }
                
                
            
        }catch(Exception ex){
            showMessage(ex.toString());
            System.out.println(ex.toString());
            
        }
    }

    @FXML
    private void clearAction(ActionEvent event){
        
        System.out.println("You Click clear");
        try{
            clearFields();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle rb){
        try{
            fillCurrencyTable();
            fillFields();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void fillCurrencyTable(){
        try{
            pkCol.setCellValueFactory(cellData -> cellData.getValue().getPkProperty().asObject());
            currNameCol.setCellValueFactory(cellData -> cellData.getValue().getCurrNameProperty());
            isoCol.setCellValueFactory(cellData -> cellData.getValue().getIsoSymbolProperty());
            symbolCol.setCellValueFactory(cellData -> cellData.getValue().getSymbolProperty());
            noteCol.setCellValueFactory(cellData -> cellData.getValue().getNoteProperty());

            currencyTable.setItems(database.getAllCurrencyProperty());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
