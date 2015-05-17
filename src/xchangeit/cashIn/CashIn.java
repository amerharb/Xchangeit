/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit.cashIn;

import java.sql.Timestamp;
import xchangeit.Transaction;
import xchangeit.XchTransactionTypeeEnum;
import xchangeit.currency.Currency;

/**
 *
 * @author Amer
 */
public class CashIn extends Transaction
{
    private double cashAmount;
    
    public CashIn(int pk, Timestamp transDate, String note, double cashAmount){
        super(pk, transDate, note);
        cashAmount = Math.abs(cashAmount); // the amout must be all time positive 
        this.cashAmount = cashAmount;

    }

    public double getCashAmount()
    {
        return cashAmount;
    }
    
    @Override
    public XchTransactionTypeeEnum getTransType()
    {
        return XchTransactionTypeeEnum.CashIn;
    }

    @Override
    public double getCash()
    {
        return this.cashAmount;
    }

    @Override
    public Currency getCurr()
    {
        return null;
    }

    @Override
    public double getCurrAmt()
    {
        return 0;
    }

    @Override
    public double getRate()
    {
        return 0;
    }

    @Override
    public double getSellBuyPrice()
    {
        return 0;
    }
}
