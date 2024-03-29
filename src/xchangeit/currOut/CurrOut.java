/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit.currOut;

import java.sql.Timestamp;
import xchangeit.XchTransactionTypeeEnum;
import xchangeit.currIn.CurrIn;
import xchangeit.currency.Currency;

/**
 *
 * @author Amer
 */
public class CurrOut extends CurrIn
{

    public CurrOut(int pk, Timestamp transDate, String note, Currency currency, String currAmount, String rate)
    {
        this(pk, transDate, note, currency, Double.valueOf(currAmount), Double.valueOf(rate));
    }
    
    public CurrOut(int pk, Timestamp transDate, String note, Currency currency, double currAmount, double rate)
    {
        super(pk, transDate, note, currency, currAmount, rate);
    }

    //this field return the value as it will be store in the database
    @Override
    public double getCurrAmt(){ 
        return -this.getCurrAmount();
    }

    @Override
    public XchTransactionTypeeEnum getTransType()
    {
        return XchTransactionTypeeEnum.CurrOut;
    }


}
