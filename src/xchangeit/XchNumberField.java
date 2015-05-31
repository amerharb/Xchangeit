/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit;

import javafx.scene.control.TextField;

/**
 *
 * @author Amer
 */


public class XchNumberField extends TextField{

    public XchNumberField() {
        super();
        //setMinWidth(25);
        //setMaxWidth(25);
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if ((!text.matches("[A-Za-z]") && !text.matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")) 
                || (text.contains(".") && !getText().contains("."))) {
            
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if ((!text.matches("[A-Za-z]") && !text.matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")) 
                || (text.contains(".") && !getText().contains("."))) {
            
            super.replaceSelection(text);
        }
    }
}