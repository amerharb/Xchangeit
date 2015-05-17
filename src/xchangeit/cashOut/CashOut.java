/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit.cashOut;

import java.sql.Timestamp;
import xchangeit.Transaction;
import xchangeit.XchTransactionTypeeEnum;
import xchangeit.cashIn.CashIn;

/**
 *
 * @author Amer
 */
public class CashOut extends CashIn
{

    public CashOut(int pk, Timestamp transDate, String note, String cashAmount){
        this(pk, transDate, note, Double.valueOf(cashAmount));
    }
    
    public CashOut(int pk, Timestamp transDate, String note, double cashAmount){
        super(pk, transDate, note, cashAmount);
    }

    //this field return the value as it will be store in the database
    @Override
    public double getCash(){ 
        return -this.getCashAmount();
    }

    @Override
    public XchTransactionTypeeEnum getTransType()
    {
        return XchTransactionTypeeEnum.CashOut;
    }
    
    @Override
    public String getSqlInsertStatment()
    {
         String s; //insert value statment will be stored here
        
        s = "insert into trans(";
        if (getTransDate() != null){
            s += "trans_date, ";
        }   
        
        s += "trans_type, cash,  note) values(";
                
        if (getTransDate() != null){
            s += "'" + getTransDate().toString() + "', ";
        }
        
        s += "12"; //trans type is 12 for cash Out
        s += ", " + String.valueOf(getCash());
        
        if (getNote() == null || getNote().isEmpty())
            s += ", null";
        else
            s += ", '" + getNote() + "'";

        s += ")";

        return s;
    }


}
