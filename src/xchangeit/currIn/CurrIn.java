/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit.currIn;

import java.sql.Timestamp;
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

    public CurrIn(int pk, Timestamp transDate, String note, Currency currency, double currAmount, double rate)
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

    @Override
    public String getSqlInsertStatment()
    {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*String s; //insert value statment will be stored here
        
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
*/
    }
            
}
