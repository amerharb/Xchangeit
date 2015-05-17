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
    
    public CashIn(int pk, Timestamp transDate, String note, String cashAmount){
        this(pk, transDate, note, Double.valueOf(cashAmount));
    }
    
    public CashIn(int pk, Timestamp transDate, String note, double cashAmount){
        super(pk, transDate, note); 
        this.cashAmount = Math.abs(cashAmount); // the amout must be all time positive;

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
    /*
    @Override
    public String getSqlInsertStatment()
    {
         String s; //insert value statment will be stored here
        
        s = "insert into trans(";
        if (getTransDate() != null){
            s += "trans_date, ";
        }   
        
        //keep this line only to copy from here later
        //s += "trans_type, cash, curr, curr_amt, rate, sell_buy_price, note) values(";
        s += "trans_type, cash,  note) values(";
                
        if (getTransDate() != null){
            s += "'" + getTransDate().toString() + "', ";
        }
        
        s += "2"; //trans type is 2 for cash in
        s += ", " + String.valueOf(getCash());
        
        if (getNote() == null || getNote().isEmpty())
            s += ", null";
        else
            s += ", '" + getNote() + "'";

        s += ")";

        return s;
    }
*/
}
