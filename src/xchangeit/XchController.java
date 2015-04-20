/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import javafx.fxml.Initializable;

/**
 *
 * @author Amer
 */
public abstract class XchController implements Initializable
{
    protected static MainScreenController MainScreen;
    public static void setMainScreen(MainScreenController mainScreenController){
        MainScreen = mainScreenController;
    }
        
}
