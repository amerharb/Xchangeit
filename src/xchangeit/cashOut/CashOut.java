/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit.cashOut;

import java.util.Date;
import xchangeit.Transaction;

/**
 *
 * @author Amer
 */
public class CashOut extends Transaction
{
    private double cashAmount;
    
    public CashOut(Date transDate, String note, double cashAmount){
        super(transDate, note);
        cashAmount = Math.abs(cashAmount); // the amout must be all time positive 
        this.cashAmount = cashAmount;
                
    }

    public double getCashAmount()
    {
        return cashAmount;
    }
    
    //this field return the value as it will be store in the database
    public double getCashField(){ 
        return -this.cashAmount;
    }
}
