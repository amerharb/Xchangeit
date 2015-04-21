/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import java.util.Date;

/**
 *
 * @author Amer
 */
public class Rate
{
    private int pk;
    private Date rateDate;
    private Currency curr;
    private double rate;
    private double sellPrice;
    private double buyPrice;			
    private String note;
    
    public Rate(int pk, Date rateDate, Currency curr, double rate, double sellPrice, double buyPrice, String note){
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

    public Date getRateDate()
    {
        return rateDate;
    }

    public void setRateDate(Date rateDate)
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

    public void setRate(double rate)
    {
        this.rate = rate;
    }

    public double getSellPrice()
    {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice)
    {
        this.sellPrice = sellPrice;
    }

    public double getBuyPrice()
    {
        return buyPrice;
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


}
