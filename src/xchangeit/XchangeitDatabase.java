/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Amer
 */


public class XchangeitDatabase
{
    public enum XchConnectionStatusEnum{
        Disconnect,
        Connected
    }
    private XchConnectionStatusEnum status;
    
    private Connection conn;

    public void connect(String server, String rootPassword){
               // TODO code application logic here
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL = "jdbc:mysql://" + server + ":3306/curEx?user=root&password=" + rootPassword;
            conn = DriverManager.getConnection(URL);
            status = XchConnectionStatusEnum.Connected;
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }

    }
    
    public void Disconnect() {
    
        try{
            conn.close();
            status = XchConnectionStatusEnum.Disconnect;
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }

}
    public XchConnectionStatusEnum getStatus(){
        return status;
    }
    
    public ResultSet executeStatment(String stmt){
        
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(stmt);
            return rs;
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            return null;
        }
              
    }
    
    public Currency getCurrencyByPK(int pk){
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select curr_name, iso_symbol, symbol, note, inactive from curr where pk = " + pk);

            if (rs.first()){
                Currency curr = new Currency(pk, rs.getString("curr_name"), rs.getString("iso_symbol"), rs.getString("symbol"), rs.getString("note"), rs.getBoolean("inactive") );
                return curr;
            }else{
                return null;
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            return null;
        }
        
    }
}
