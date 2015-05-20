/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit;

import java.util.Date;
import xchangeit.currency.Currency;

/**
 *
 * @author Amer
 */
public interface XchTransactoinInterface
{
    public int getPk();
    public Date getTransDate();
    public XchTransactionTypeeEnum getTransType();
    public double getCash();
    public Currency getCurr();
    public double getCurrAmt();
    public double getRate();
    public double getSellBuyPrice();
    public String getNote();

    public String getSqlInsertStatment();

    public void setPk(int pk);
    
}
