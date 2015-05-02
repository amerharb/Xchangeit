/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit.currIn;

import java.util.Date;
import xchangeit.Transaction;
import xchangeit.XchTransactionTypeeEnum;
import xchangeit.currency.Currency;

/**
 *
 * @author Amer
 */
public class CurrIn extends Transaction
{
    private Currency currency;
    private double currAmount;
    private double rate;

    public CurrIn(int pk, Date transDate, String note, Currency currency, double currAmount, double rate)
    {
        super(pk, transDate, note);
        this.currency = currency;
        this.currAmount = Math.abs(currAmount);
        this.rate = rate;
        
    }

    public Currency getCurrency()
    {
        return currency;
    }

    public double getCurrAmount()
    {
        return currAmount;
    }

    public double getRate()
    {
        return rate;
    }

    @Override
    public XchTransactionTypeeEnum getTransType()
    {
        return XchTransactionTypeeEnum.CurrIn;
    }
    
    @Override
    public double getCash()
    {
        return 0;
    }

    @Override
    public Currency getCurr()
    {
        return currency;
    }

    @Override
    public double getCurrAmt()
    {
        return currAmount;
    }

    @Override
    public double getSellBuyPrice()
    {
        return 0;
    }
            
}
