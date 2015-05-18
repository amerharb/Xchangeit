
package xchangeit.currency;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Amer
 */

public class CurrencyProperty extends Currency
{
    private final SimpleIntegerProperty pkProperty;
    private final SimpleStringProperty currNameProperty;
    private final SimpleStringProperty isoSymbolProperty;
    private final SimpleStringProperty symbolProperty;
    private final SimpleStringProperty noteProperty;
    private final SimpleBooleanProperty inactiveProperty;			

    public CurrencyProperty(Currency c)
    {
        this(c.getPk(), c.getCurrName(),c.getIsoSymbol(), c.getSymbol(), c.getNote(), c.isInactive());
    }
    
    public CurrencyProperty(int pk, String currName, String isoSymbol, String symbol, String note, boolean inactive)
    {
        super(pk, currName, isoSymbol, symbol, note, inactive);
        this.pkProperty = new SimpleIntegerProperty(pk);
        this.currNameProperty = new SimpleStringProperty(currName);
        this.isoSymbolProperty = new SimpleStringProperty(isoSymbol);
        this.symbolProperty = new SimpleStringProperty(symbol);
        this.noteProperty= new SimpleStringProperty(note);
        this.inactiveProperty = new SimpleBooleanProperty(inactive);
    }
    
    public SimpleIntegerProperty getPkProperty()
    {
        return pkProperty;
    }

    public SimpleStringProperty getCurrNameProperty()
    {
        return currNameProperty;
    }

    @Override
    public String getCurrName(){
        return currNameProperty.get();
    }
    
    public SimpleStringProperty getIsoSymbolProperty()
    {
        return isoSymbolProperty;
    }

    @Override
    public String getIsoSymbol(){
        return isoSymbolProperty.get();
    }
    
    public SimpleStringProperty getSymbolProperty()
    {
        return symbolProperty;
    }

    @Override
    public String getSymbol(){
        return symbolProperty.get();
    }
    
    public SimpleStringProperty getNoteProperty()
    {
        return noteProperty;
    }

    @Override
    public String getNote(){
        return noteProperty.get();
    }
    
    public SimpleBooleanProperty isInactiveProperty()
    {
        return inactiveProperty;
    }
    
    @Override
    public String toString(){
        return super.getCurrName();
    } 

}