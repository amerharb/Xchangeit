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
    private String Symbol;
    private String note;
    private boolean inactive;			

    public Currency(int pk, String currName, String isoSymbol, String Symbol, String note, boolean inactive){
        this.pk = pk;
        this.currName= currName;
        this.isoSymbol = isoSymbol;
        this.Symbol = Symbol;
        this.note= note;
        this.inactive = inactive;
    }
    
//    public Currency(){}
    
    public int getPk()
    {
        return pk;
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
        return Symbol;
    }

    public void setSymbol(String Symbol)
    {
        this.Symbol = Symbol;
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

    public String getInsertValues()
    {
        String s; //insert value statment will be stored here
        
        s = "'" + currName + "'";
        s += ", '" + isoSymbol + "'";
        s += ", '" + Symbol + "'";
        if (note.isEmpty())
            s += ", null";
        else
            s += ", '" + note + "'";

        if (inactive)
            s += ", true";
        else
            s += ", false";

        return s;
    }
}



