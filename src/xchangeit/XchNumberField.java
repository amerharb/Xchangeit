package xchangeit;

import javafx.scene.control.TextField;

public class XchNumberField extends TextField{

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