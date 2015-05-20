/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit;

import java.sql.Timestamp;

/**
 *
 * @author Amer
 */
public abstract class Transaction implements XchTransactoinInterface
{
    private int pk;
    private Timestamp transDate;
    private String note;
    
    public Transaction(int pk, Timestamp transDate, String note){
        
        this.pk = pk;
        this.transDate = transDate;
        this.note = note;
    }

    @Override
    public String getSqlInsertStatment()
    {
         String s; //insert value statment will be stored here
        
        s = "insert into trans(";
        if (getTransDate() != null){
            s += "trans_date, ";
        }   
        
        s += "trans_type, cash, curr, curr_amt, rate, sell_buy_price, note) values(";
                
        if (getTransDate() != null){
            s += "'" + getTransDate().toString() + "', ";
        }
        
        switch (getTransType()){
            case CurrIn:
                s += "1"; //trans type is 1 for curr in
                s += ", null"; //cash is null
                s += ", " + getCurr().getPk(); 
                s += ", " + getCurrAmt(); 
                s += ", " + getRate(); 
                s += ", null"; //sell buy price is null
                break;
            case CashIn:
                s += "2"; //trans type is 2 for cash in
                s += ", " + getCash();
                s += ", null"; //curr is null
                s += ", null"; //curr amt is null
                s += ", null"; //curr rate is null
                s += ", null"; //sell buy price is null
                break;
            case Buy:
                s += "3"; //trans type is 3 for buy
                s += ", " + getCash();
                s += ", " + getCurr().getPk(); 
                s += ", " + getCurrAmt(); 
                s += ", " + getRate(); 
                s += ", " + getSellBuyPrice();
                break;
            case CurrOut:
                s += "11"; //trans type is 11 for curr out
                s += ", null"; //cash is null
                s += ", " + getCurr().getPk(); 
                s += ", " + getCurrAmt(); 
                s += ", " + getRate(); 
                s += ", null"; //sell buy price is null
                break;
            case CashOut:
                s += "12"; //trans type is 12 for cash out
                s += ", " + getCash();
                s += ", null"; //curr is null
                s += ", null"; //curr amt is null
                s += ", null"; //curr rate is null
                s += ", null"; //sell buy price is null
                break;
            case Sell:
                s += "13"; //trans type is 13 for sell
                s += ", " + getCash();
                s += ", " + getCurr().getPk(); 
                s += ", " + getCurrAmt(); 
                s += ", " + getRate();
                s += ", " + getSellBuyPrice();
                break;
        }
        
        if (getNote() == null || getNote().isEmpty())
            s += ", null";
        else
            s += ", '" + getNote() + "'";

        s += ")";
        
        return s;
    }

    public Timestamp getTransDate()
    {
        return transDate;
    }

    public String getNote()
    {
        return note;
    }

    public int getPk()
    {
        return pk;
    }

    public void setPk(int pk)
    {
        this.pk = pk;
    }
}
