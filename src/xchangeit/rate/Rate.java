/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.rate;

import xchangeit.currency.Currency;
import java.sql.Timestamp;

/**
 *
 * @author Amer
 */
public class Rate
{
    private int pk;
    private Timestamp rateDate;
    private Currency curr;
    private double rate;
    private double sellPrice;
    private double buyPrice;			
    private String note;
    
    public Rate(int pk, Timestamp rateDate, Currency curr, String rate, String sellPrice, String buyPrice, String note){
        this(pk, rateDate, curr, Double.valueOf(rate), Double.valueOf(sellPrice), Double.valueOf(buyPrice), note);
    }

    public Rate(int pk, Timestamp rateDate, Currency curr, double rate, double sellPrice, double buyPrice, String note){
        this.pk = pk;
        this.rateDate = rateDate;
        this.curr = curr;
        this.rate = rate;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.note = note;
    }
    
//    public Currency(){}
    
    public int getPk()
    {
        return pk;
    }

    public void setPK(int pk)
    {
        this.pk = pk;
    }

    public Timestamp getRateDate()
    {
        return rateDate;
    }

    public void setRateDate(Timestamp rateDate)
    {
        this.rateDate = rateDate;
    }

    public Currency getCurr()
    {
        return curr;
    }

    public void setCurr(Currency curr)
    {
        this.curr = curr;
    }

    public double getRate()
    {
        return rate;
    }

    public String getRateAsString()
    {
        return String.valueOf(rate);
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

    public double getSellPrice()
    {
        return sellPrice;
    }

    public String getSellPriceAsString()
    {
        return String.valueOf(sellPrice);
    }

    public void setSellPrice(double sellPrice)
    {
        this.sellPrice = sellPrice;
    }

    public double getBuyPrice()
    {
        return buyPrice;
    }

    public String getBuyPriceAsString()
    {
        return String.valueOf(buyPrice);
    }

    public void setBuyPrice(double buyPrice)
    {
        this.buyPrice = buyPrice;
    }
    
    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getSqlInsertStatment()
    {
        String s; //insert value statment will be stored here
        
        s = "insert into rates(curr, ";
        if (rateDate != null){
            s += "rate_date, ";
        }    
        s += "rate, sell_price, buy_price, note) values ("; 
        s += curr.getPk();
        if (rateDate != null){
            s += ", '" + rateDate.toString() + "'";
        }
        s += ", " + rate;
        s += ", " + sellPrice ;
        s += ", " + buyPrice ;
        if (note == null || note.isEmpty())
            s += ", null";
        else
            s += ", '" + note + "'";

        s += ")";

        return s;
    }


}
