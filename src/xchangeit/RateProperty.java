/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import java.util.Date;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Amer
 */
public class RateProperty extends Rate
{
    private final SimpleIntegerProperty pkProperty;
    //TODO
    private final SimpleObjectProperty rateDateProperty;
    private final CurrencyProperty currProperty;
    private final SimpleDoubleProperty rateProperty;
    private final SimpleDoubleProperty sellPriceProperty;
    private final SimpleDoubleProperty buyPriceProperty;
    
    public RateProperty(Rate r){
        this(r.getPk(), r.getRateDate(), r.getCurr(), r.getRate(), r.getSellPrice(), r.getBuyPrice());
    }

    public RateProperty(int pk, Date rateDate, Currency curr, double rate, double sellPrice, double buyPrice){
        
        super(pk, rateDate, curr, rate, sellPrice, buyPrice);
        this.pkProperty = new SimpleIntegerProperty(pk);
        this.rateDateProperty = new SimpleObjectProperty(rateDate);
        this.currProperty = new CurrencyProperty(curr);
        this.rateProperty = new SimpleDoubleProperty(rate);
        this.sellPriceProperty = new SimpleDoubleProperty(sellPrice);
        this.buyPriceProperty = new SimpleDoubleProperty(buyPrice);

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
    
}
