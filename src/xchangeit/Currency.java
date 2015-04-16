/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

/**
 *
 * @author Amer
 */

public class Currency
{
    private int pk;
    private String currName;
    private char[] isoSymbol = new char[3];
    private String Symbol;
    private String note;
    private boolean inactive;			

    public int getPk()
    {
        return pk;
    }

    public void setPk(int pk)
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

    public char[] getIsoSymbol()
    {
        return isoSymbol;
    }

    public void setIsoSymbol(char[] isoSymbol)
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
}



