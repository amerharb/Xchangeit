/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit.buy;

import java.sql.Timestamp;
import xchangeit.Transaction;
import xchangeit.XchTransactionTypeeEnum;
import static xchangeit.XchTransactionTypeeEnum.CashOut;
import xchangeit.cashOut.CashOut;
import xchangeit.currIn.CurrIn;
import xchangeit.currency.Currency;

/**
 *
 * @author Amer
 */
public class Buy extends CurrIn
{
    private double cashAmount;
    private double price;
    
    public double getCashAmount()
    {
        return cashAmount;
    }

    public double getPrice()
    {
        return price;
    }

    @Override
    public double getCash()
    {
        return -cashAmount;
    }

    @Override
    public XchTransactionTypeeEnum getTransType()
    {
        return XchTransactionTypeeEnum.Buy;
    }
    
    public Buy(int pk, Timestamp transDate, String note, Currency currency, 
            double currAmount, double rate, double cashAmount, double price)
    {
        super(pk, transDate, note, currency, currAmount, rate);
        this.cashAmount = Math.abs(cashAmount);
        this.price = Math.abs(price);
    }
    
    @Override
    public double getSellBuyPrice()
    {
        return price;
    }
            
}