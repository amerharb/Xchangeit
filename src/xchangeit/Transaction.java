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
}
