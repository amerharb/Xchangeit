/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit.currency;

/**
 *
 * @author Amer
 */

public class Currency
{
    private int pk;
    private String currName;
    private String isoSymbol;
    private String symbol;
    private String note;
    private boolean inactive;			

    public Currency(int pk, String currName, String isoSymbol, String Symbol, String note, boolean inactive){
        this.pk = pk;
        this.currName = currName;
        this.isoSymbol = isoSymbol;
        this.symbol = Symbol;
        this.note = note;
        this.inactive = inactive;
    }
    
//    public Currency(){}
    
    public int getPk()
    {
        return pk;
    }

    public void setPK(int pk) //to update the PK after insert in the database
    {
        this.pk = pk;
    }

    public String getCurrName()
    {
        return currName;
    }

    public void setCurrName(String currName)
    {
        this.currName = currName;
    }

    public String getIsoSymbol()
    {
        return isoSymbol;
    }

    public void setIsoSymbol(String isoSymbol)
    {
        this.isoSymbol = isoSymbol;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String Symbol)
    {
        this.symbol = Symbol;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public boolean isInactive()
    {
        return inactive;
    }

    public void setInactive(boolean inactive)
    {
        this.inactive = inactive;
    }

    public String getSqlInsertStatment()
    {
        String s; //insert value statment will be stored here
        
        s = "insert into curr(curr_name, iso_symbol, symbol, note, inactive) values ("; 
        s += "'" + getCurrName() + "'";
        s += ", '" + getIsoSymbol() + "'";
        s += ", '" + getSymbol() + "'";
        if (getNote() == null || getNote().isEmpty())
            s += ", null";
        else
            s += ", '" + getNote() + "'";

        if (inactive)
            s += ", true";
        else
            s += ", false";

        s += ")";

        return s;
    }

    public String getSqlUpdateStatment()
    {
        String s; //update statment will be stored here
        
        s = "update curr set ";
        s += "curr_name = '" + getCurrName() + "'";
        s += ", iso_symbol = '" + getIsoSymbol() + "'";
        s += ", symbol = '" + getSymbol() + "'";
        if (getNote() == null || getNote().isEmpty())
            s += ", note = null";
        else
            s += ", note = '" + getNote() + "'";

        if (inactive)
            s += ", inactive = true";
        else
            s += ", inactive = false";

        s += " where pk = " + pk;
        
        return s;
    }

}



