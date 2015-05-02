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
public enum XchTransactionTypeeEnum
{
    CurrIn (1),
    CashIn (2),
    Buy (3),
    CurrOut (11),
    CashOut (12),
    Sell (13);
    
    private int code;
 
    private XchTransactionTypeeEnum(int c) {
        code = c;
    }

    public int getCode() {
        return code;
    }
}
