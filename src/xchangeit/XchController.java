/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import javafx.fxml.Initializable;
import java.sql.Timestamp;

/**
 *
 * @author Amer
 */
public abstract class XchController implements Initializable
{

    protected static MainScreenController mainScreen;
    protected static XchDatabase database;
    protected static XchSettings settings;

    protected Timestamp getTimeStamp(String datetime){ //in case of error this function will return null
        try{

            Timestamp st = Timestamp.valueOf(datetime);
            return st;
        }catch(Exception ex){
            return null;
        }
    }
    

}
