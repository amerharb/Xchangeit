/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xchangeit;

import xchangeit.rate.Rate;
import xchangeit.currency.Currency;
import xchangeit.XchTransactoinInterface;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xchangeit.cashIn.CashIn;
import xchangeit.currency.CurrencyProperty;
import xchangeit.rate.RateProperty;

/**
 *
 * @author Amer
 */


public class XchDatabase
{

    public enum XchConnectionStatusEnum{
        Disconnect,
        Connected
    }
    
    private XchConnectionStatusEnum status = XchConnectionStatusEnum.Disconnect;
    
    private Connection conn;

    //TEST still under test
    private ArrayList<Currency> allCurrency;
    private ArrayList<Rate> allRate;
    private ArrayList<XchTransactoinInterface> allTrans;
    
    private ObservableList<RateProperty> allRateProperty;
    private ObservableList<CurrencyProperty> allCurrencyProperty;

    private boolean currencyNeedRefresh; // this is an indicator that something has been changed in currency 
    private boolean rateNeedRefresh; // this is an indicator that something has been changed in Rate 
    private boolean transNeedRefresh; //this is an indicator that somehting has been changed in Trans table
    
    private void setCurrencyNeedRefreshAndDepedency(){
        currencyNeedRefresh = true;
        rateNeedRefresh = true;
        transNeedRefresh = true;
    }
        
    public XchDatabase()
    {
        this.allCurrency = new ArrayList<>();
        this.allRate = new ArrayList<>();
        this.allTrans = new ArrayList<>();
        
        this.allCurrencyProperty = FXCollections.observableArrayList();
        this.allRateProperty = FXCollections.observableArrayList();

    }

    public void connect(String server, String rootPassword){
        connect(server, rootPassword, "");
    }
    
    public boolean connect(String server, String rootPassword, String databaseName){
        try {
            if (!databaseName.isEmpty())
                databaseName = "/" + databaseName;
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL = "jdbc:mysql://" + server + ":3306" + databaseName + "?user=root&password=" + rootPassword;
            conn = DriverManager.getConnection(URL);
            status = XchConnectionStatusEnum.Connected;
            return true;
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            return false;
        }

    }
    
    public boolean disconnect() {
    
        try{
            conn.close();
            status = XchConnectionStatusEnum.Disconnect;
            allCurrency.clear();
            allCurrencyProperty.clear();
            allRate.clear();
            allRateProperty.clear();
            allTrans.clear();
            return true;
        } catch (Exception ex) {
            System.err.println("ERROR: " + ex);
            return false;
        }

}
    public XchConnectionStatusEnum getStatus(){
        return status;
    }
    
//    public Currency getCurrencyByPK(int pk){
//        try{
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery("select curr_name, iso_symbol, symbol, note, inactive from curr where pk = " + pk);
//
//            if (rs.first()){
//                Currency curr = new Currency(pk, rs.getString("curr_name"), rs.getString("iso_symbol"), rs.getString("symbol"), rs.getString("note"), rs.getBoolean("inactive") );
//                return curr;
//            }else{
//                return null;
//            }
//        } catch (Exception e) {
//            System.err.println("ERROR: " + e);
//            return null;
//        }
//        
//    }
//    
//    public boolean delCurrencyByPK(int pk){
//        try{
//            Statement st = conn.createStatement();
//            boolean r = st.execute("delete from curr where pk = " + pk);
//            if (st.getUpdateCount()>0)
//                //TODO look in allCurrency object and remove the currency from here also
//                return true;
//            else
//                return false;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
// 
//        }
//        
//    }
    
    public void buildAllCurrency(){ // this method will fill the all currency object 
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select pk, curr_name, iso_symbol, symbol, note, inactive from curr " );

            allCurrency.clear();
            allCurrencyProperty.clear();
            while(rs.next()){
                CurrencyProperty currProp = new CurrencyProperty(rs.getInt("pk"), rs.getString("curr_name"), rs.getString("iso_symbol"), rs.getString("symbol"), rs.getString("note"), rs.getBoolean("inactive") );
                allCurrency.add(currProp);
                allCurrencyProperty.add(currProp);
            }
            
            currencyNeedRefresh = false;
        
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
    }

    public ArrayList<Currency> getAllCurrency(){
        try{
            return allCurrency;
        
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            return null;
        }
    }

    public boolean delCurrency(Currency c){
        try{
            Statement st = conn.createStatement();
            st.execute("delete from curr where pk = " + c.getPk());
            if (st.getUpdateCount()>0){
                allCurrency.remove(c);
                allCurrencyProperty.remove(c);
                return true;
            }
            else {return false;}
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public Currency addCurrency(Currency c){ // this method will return the Currency that actually added and it will ignore the one passed, in case of error it will return null.
    
        try{
            Statement st = conn.createStatement();
            String s = c.getSqlInsertStatment();
            st.executeUpdate(s,Statement.RETURN_GENERATED_KEYS);
            if (st.getUpdateCount() > 0){
                //get the PK that generated
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()) {
                    c.setPK(rs.getInt(1));
                }
                rs.close();
 
                CurrencyProperty currProp = new CurrencyProperty(c);
                allCurrency.add(currProp);
                allCurrencyProperty.add(currProp);
                setCurrencyNeedRefreshAndDepedency();
                return currProp;
            }else {return null;}
        }catch(Exception ex) {
            //TODO idintifiy the error 
            System.err.println("ERROR: " + ex);
            return null;
        }
    }
    
    public boolean updateCurrency(Currency c){
    
        try{
            Statement st = conn.createStatement();
            String s=c.getSqlUpdateStatment();
            st.execute(s);
            if (st.getUpdateCount() > 0){
                setCurrencyNeedRefreshAndDepedency();
                return true;
            }else{return false;}
        } catch (Exception ex) {
            //TODO idintifiy the error 
            System.err.println("ERROR: " + ex);
            return false;
        }
    }
    
//    public ArrayList<Currency> getLastGrabedCurrency(){
//        return allCurrency;
//    }
//    
    public ObservableList<CurrencyProperty> getAllCurrencyProperty(){
        return allCurrencyProperty;
    }
    
    public void BuildAllRate(){
        try{
            allRate.clear();
            allRateProperty.clear();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select pk, rate_date, curr, rate, sell_price, buy_price, note from rates" );
            
            if (currencyNeedRefresh || allCurrency.isEmpty()){
                buildAllCurrency(); //fill currency if it is empty
            }
            
            if (allCurrency.isEmpty()){
                allCurrencyProperty.clear();
                return; //exit from here, there is no rates if there is no currency
            }
            
            while(rs.next()){
                CurrencyProperty rateCurrProp = null; // I found it kind of stupid that i have to init this var to null in order to make it work 
                //look for the currency 
                for (CurrencyProperty c:allCurrencyProperty){
                    if (c.getPk() == rs.getInt("curr")){
                        rateCurrProp = c;
                        break;
                    }
                }
                if (rateCurrProp != null){ 
                    Timestamp ts = rs.getTimestamp("rate_date");
                    RateProperty r = new RateProperty(rs.getInt("pk"), ts, rateCurrProp, rs.getDouble("rate"), rs.getDouble("sell_price"), rs.getDouble("buy_price"), rs.getString("note"));

                    allRate.add(r);
                    allRateProperty.add(r);
                }else{//thats mean currency not found maybe delete it by mistake from database or mistake in the currency collection here ... dont know what to do in this case yet
                    
                }
             }
            
            rateNeedRefresh = false;

        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
        
    }
    
    public ArrayList<Rate> getAllRate(){
        try{
            return allRate;
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            return null;
        }
    }

    public boolean delRate(Rate r){
        try{
            Statement st = conn.createStatement();
            st.execute("delete from rates where pk = " + r.getPk());
            if (st.getUpdateCount()>0){
                allRate.remove(r);
                allRateProperty.remove(r);
                return true;
            }else{return false;}
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
    
    public Rate addRate(Rate r){ // this method will return the Rate that actually added and it will ignore the one passed, in case of error it will return null.
        try{
            Statement st = conn.createStatement();
            String s = r.getSqlInsertStatment();
            st.executeUpdate(s,Statement.RETURN_GENERATED_KEYS);
            
            if (st.getUpdateCount() > 0){
                //get the PK that generated
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()) {
                    r.setPK(rs.getInt(1));
                }
                rs.close();
                
                RateProperty rateProp = new RateProperty(r);
                allRate.add(rateProp);
                allRateProperty.add(rateProp);
                rateNeedRefresh = true; //
                return rateProp;
            }else{return null;}
            
        } catch (Exception ex) {
            //TODO idintifiy the error 
            System.err.println("ERROR: " + ex);
            return null;
        }
    }
    
    public ObservableList<RateProperty> getAllRateProperty(){
        return allRateProperty;
    }

    public boolean addTrans(XchTransactoinInterface t)
    {
        try{
            Statement st = conn.createStatement();
            String s = t.getSqlInsertStatment();
            st.execute(s);
            
            if (st.getUpdateCount() > 0){
                allTrans.add(t);
                transNeedRefresh = true;
                return true;
            }else{return false;}
            
        } catch (Exception e) {
            //TODO idintifiy the error 
            System.err.println("ERROR: " + e);
            return false;

        }
    }


    public void createDatabase(){
        createDatabase("Xchangeit");
    }
    
    public void createDatabase(String databaseName){

        System.out.println("You Click Create Database");
        try{
            Statement st = conn.createStatement();
            st.execute("drop database if exists " + databaseName);
            st.execute("create database " + databaseName);
            st.execute("use " + databaseName);
            st.execute("create table curr \n" +
                            "(  \n" +
                            "\n" +
                            "	pk			    	int			  	not null\n" +
                            "					auto_increment primary key\n" +
                            "	,curr_name			varchar(50)			not null\n" +
                            "	,ISO_symbol			char(3)				not null\n" +
                            "					unique\n" +
                            "	,symbol				nvarchar(10)                    null\n" +
                            "	,note                           nvarchar(100)           	null\n" +
                            "	,inactive			bit	 			null \n" +
                            ");\n");
            
            st.execute(     "create table rates\n" +
                            "(\n" +
                            "	pk                              int				not null\n" +
                            "					auto_increment Primary key\n" +
                            "	,rate_date			datetime			not null\n" +
                            "					default current_timestamp\n" +
                            "	,curr				int				not null\n" +
                            "					references curr(pk)\n" +
                            "	,rate                          	float				not null # this represent the actual value of the currency, it will be used for internally\n" +
                            "	,sell_price			float				not null # for sell transaction \n" +
                            "	,buy_price			float				not null # from buy transaction \n" +
                            "        ,note                           nvarchar(100)                   null # could be use full to register the info of rate source\n" +
                            ");\n" );
            
            st.execute(     "create table trans\n" +
                            "(\n" +
                            "	pk				int				not null\n" +
                            "					auto_increment Primary key\n" +
                            "	,trans_date			datetime			not null						\n" +
                            "					default current_timestamp\n" +
                            "        ,trans_type			smallint			not null # 1 for buy, 2 for sell\n" +
                            "					check (trans_type in (1, 2, 3, 11, 12, 13))						#transaction type\n" +
                            "        #1	transfare currency in\n" +
                            "        #2	transfare cash (local money) in\n" +
                            "        #3	selling currency for cash\n" +
                            "        #11	transfare currency out\n" +
                            "        #12	transfare cash out\n" +
                            "        #13	buying currency for cash\n" +
                            "        ,cash				float				null #local currency\n" +
                            "	,curr				int				null\n" +
                            "					references curr(pk)\n" +
                            "	,curr_amt			float				null\n" +
                            "	,rate				float				null    #its the actual value of currency in this transaction \n" +
                            "	,sell_buy_price 		float				null    #this column in fact it could be calculated column, basicly the value of it is the abslut result of \n" +
                            "                                                                                #dividing cash on curr_amt: ABS(cash/curr_amt). but I will keep it normal column to 2 resones\n" +
                            "                                                 #1. I dont know how to create a calc column in mySQL and dont know it is supported\n" +
                            "                                                 #2. to document the value that we calc the cash and ignore the fraction of the result\n" +
                            "	#this check will determind according to the transaction type which column should be null and which will be not null and the positive and negative value\n" +
                            "        ,check(	(trans_type = 1 	and cash is null	and curr is not null	and curr_amt > 0 		and rate > 0		and sell_buy_price is null) or\n" +
                            "		(trans_type = 2 	and cash > 0 		and curr is null 		and curr_amt is null 	and rate is null	and sell_buy_price is null) or\n" +
                            "                (trans_type = 3 	and cash < 0 		and curr is not null 	and curr_amt > 0 		and rate > 0 		and sell_buy_price > 0) or\n" +
                            "                (trans_type = 11 	and cash is null 	and curr is not null 	and curr_amt < 0 		and rate > 0 		and sell_buy_price is null) or\n" +
                            "                (trans_type = 12 	and cash < 0 		and curr is null 		and curr_amt is null 	and rate is null 	and sell_buy_price is null) or\n" +
                            "                (trans_type = 13 	and cash > 0 		and curr is not null 	and curr_amt < 0 		and rate > 0 		and sell_buy_price > 0)	)\n" +
                            "	,note             	nvarchar(100)		null\n" +
                            ");\n" );
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}