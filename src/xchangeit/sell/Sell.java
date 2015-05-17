/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit.sell;

import java.sql.Timestamp;
import xchangeit.XchTransactionTypeeEnum;
import xchangeit.buy.Buy;
import xchangeit.currency.Currency;

/**
 *
 * @author Amer
 */
public class Sell extends Buy
{

    public Sell(int pk, Timestamp transDate, String note, Currency currency, double currAmount, double rate, double cashAmount, double price)
    {
        super(pk, transDate, note, currency, currAmount, rate, cashAmount, price);
    }
    
    
    @Override
    public double getCash()
    {
        return super.getCashAmount();
    }

    @Override
    public XchTransactionTypeeEnum getTransType()
    {
        return XchTransactionTypeeEnum.Sell;
    }

    @Override
    public double getCurrAmt()
    {
        return -super.getCurrAmount();
    }
}
