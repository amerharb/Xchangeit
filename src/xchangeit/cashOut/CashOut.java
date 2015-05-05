/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit.cashOut;

import java.util.Date;
import xchangeit.Transaction;
import xchangeit.XchTransactionTypeeEnum;
import xchangeit.cashIn.CashIn;

/**
 *
 * @author Amer
 */
public class CashOut extends CashIn
{
    private double cashAmount;
    
    public CashOut(int pk, Date transDate, String note, double cashAmount){
        super(pk, transDate, note, cashAmount);
    }

    //this field return the value as it will be store in the database
    @Override
    public double getCash(){ 
        return -this.cashAmount;
    }

    @Override
    public XchTransactionTypeeEnum getTransType()
    {
        return XchTransactionTypeeEnum.CashOut;
    }

}
