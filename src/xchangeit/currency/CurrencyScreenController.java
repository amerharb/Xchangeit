package xchangeit.currency;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    @FXML Label  label;
    
    ArrayList<Currency> allCureency;
    ObservableList<CurrencyProperty> allCurrencyProperty = FXCollections.observableArrayList();

    CurrencyProperty selCurrencyProp;
    
    @FXML
    private void handleAddCurrencyAction(ActionEvent event){
        
        System.out.println("You Click Currency Screen Add Button");
        try{
            //TODO fix the value of PK by retreve it from database later, check the value if string is empty, 
            //inactive will be false becuase its a new value
            if(String.valueOf(currNameText.getText()).isEmpty() ){
            label.setText("currency name please");
            }
            else if(String.valueOf(isoSymbolText.getText()).isEmpty() ){
            label.setText("iso symbol please");
            }
//            else if(isoSymbolText.getText() == isoSymbolText){
//            label.setText("duplicate entry");    
//            }
            else if(String.valueOf(symbolText.getText()).isEmpty() ){
            label.setText("symbol please");
            }
            else{
            Currency c = new Currency(0, currNameText.getText(), isoSymbolText.getText(), symbolText.getText(),noteText.getText(),false);
            DataBase.addCurrency(c);
            fillCurrencyTable();
            //System.out.println("registered successfully");
            label.setText(null);
            }   
                     
            
            
          
        }catch(Exception ex){
            //ex.printStackTrace();
        label.setText("system error");
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
    
    private void updateCurrencyFromTextFields(Currency c){
        try{

            c.setCurrName(currNameText.getText());
            c.setIsoSymbol(isoSymbolText.getText());
            c.setSymbol(symbolText.getText());
            c.setNote(noteText.getText());
            c.setInactive(inactiveCheck.isSelected());

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
    private void handelUpdateCurrencyAction(ActionEvent event){
        
        System.out.println("You Click xxx");
        try{
            updateCurrencyFromTextFields(selCurrencyProp);
            DataBase.updateCurrency(selCurrencyProp);
            fillCurrencyTable();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //TEMP: this method is just a template for any other method
    @FXML
    private void xxxAction(ActionEvent event){
        
        System.out.println("You Click xxx");
        try{

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
