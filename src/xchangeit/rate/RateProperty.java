/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.rate;

import java.sql.Timestamp;
import xchangeit.currency.Currency;
import xchangeit.currency.CurrencyProperty;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Amer
 */
public class RateProperty extends Rate
{
    private final SimpleIntegerProperty pkProperty;
    private final SimpleObjectProperty rateDateProperty;
    private final CurrencyProperty currProperty;
    private final SimpleDoubleProperty rateProperty;
    private final SimpleDoubleProperty sellPriceProperty;
    private final SimpleDoubleProperty buyPriceProperty;
    private final SimpleStringProperty noteProperty;
   
    public RateProperty(Rate r) throws Exception{
        //TODO review the casting here it does not seem correct 
        this(r.getPk(), r.getRateDate(), (CurrencyProperty)r.getCurr(), r.getRate(), r.getSellPrice(), r.getBuyPrice(), r.getNote());
    }

    public RateProperty(int pk, Timestamp rateDate, CurrencyProperty currProp, double rate, double sellPrice, double buyPrice, String note) throws Exception, ExceptionInInitializerError {
        
        super(pk, rateDate, currProp, rate, sellPrice, buyPrice, note);
        this.pkProperty = new SimpleIntegerProperty(pk);
        this.rateDateProperty = new SimpleObjectProperty(rateDate);
        if (currProp == null){
            //TODO error stop the construction
            throw new Exception("error: currency can not be null when you create rate");
        }else{
            this.currProperty = currProp;
        }
        this.rateProperty = new SimpleDoubleProperty(rate);
        this.sellPriceProperty = new SimpleDoubleProperty(sellPrice);
        this.buyPriceProperty = new SimpleDoubleProperty(buyPrice);
        this.noteProperty = new SimpleStringProperty(note);
    }

    public SimpleIntegerProperty getPkProperty()
    {
        return pkProperty;
    }

    public SimpleObjectProperty getRateDateProperty()
    {
        return rateDateProperty;
    }

    public CurrencyProperty getCurrProperty()
    {
        return currProperty;
    }

    public SimpleDoubleProperty getRateProperty()
    {
        return rateProperty;
    }

    public SimpleDoubleProperty getSellPriceProperty()
    {
        return sellPriceProperty;
    }

    public SimpleDoubleProperty getBuyPriceProperty()
    {
        return buyPriceProperty;
    }

    public SimpleStringProperty getNoteProperty()
    {
        return noteProperty;
    }
    
}
