/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit;

import java.sql.Timestamp;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import xchangeit.currency.CurrencyProperty;

/**
 *
 * @author Amer
 */
public class TransactionProperty 
{
    private final SimpleIntegerProperty pkProperty;
    private final SimpleObjectProperty transDateProperty;
    private final SimpleStringProperty transTypeProperty;
    private final SimpleDoubleProperty cashProperty;
    private final CurrencyProperty currProperty;
    private final SimpleDoubleProperty currAmtProperty;
    private final SimpleDoubleProperty rateProperty;
    private final SimpleDoubleProperty sellBuyPriceProperty;
    private final SimpleStringProperty noteProperty;

    public TransactionProperty(XchTransactoinInterface trans){
        this(trans.getPk(), trans.getTransDate(), trans.getTransType(), trans.getCash(),
                trans.getCurr() == null? null :  new CurrencyProperty(trans.getCurr()), trans.getCurrAmt(), trans.getRate(), 
                trans.getSellBuyPrice(), trans.getNote());
    }
    
    public TransactionProperty(int pk, Timestamp transDate, XchTransactionTypeeEnum transType, double cash, 
                CurrencyProperty currProp, double currAmt, double rate, 
                double sellBuyPrice, String note) {
        this.pkProperty = new SimpleIntegerProperty(pk);
        this.transDateProperty = new SimpleObjectProperty(transDate);
        this.transTypeProperty = new SimpleStringProperty(transType.name());
        this.cashProperty = new SimpleDoubleProperty(cash);
        this.currProperty = currProp;
        this.currAmtProperty = new SimpleDoubleProperty(currAmt);
        this.rateProperty = new SimpleDoubleProperty(rate);
        this.sellBuyPriceProperty = new SimpleDoubleProperty(sellBuyPrice);
        this.noteProperty = new SimpleStringProperty(note);
    }
    
    public SimpleIntegerProperty getPkProperty()
    {
        return pkProperty;
    }

    public SimpleObjectProperty getTransDateProperty()
    {
        return transDateProperty;
    }

    public SimpleStringProperty getNoteProperty()
    {
        return noteProperty;
    }

    public SimpleDoubleProperty getCashProperty()
    {
        return cashProperty;
    }

    public CurrencyProperty getCurrProperty()
    {
        return currProperty;
    }

    public SimpleDoubleProperty getCurrAmtProperty()
    {
        return currAmtProperty;
    }

    public SimpleDoubleProperty getRateProperty()
    {
        return rateProperty;
    }

    public SimpleDoubleProperty getSellBuyPriceProperty()
    {
        return sellBuyPriceProperty;
    }

    public SimpleStringProperty getTransTypeProperty()
    {
        return transTypeProperty;
    }
    
}
