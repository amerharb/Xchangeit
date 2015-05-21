/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xchangeit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Amer
 */
public class XchSettings
{
    //Test still an idea
    private String defaultDatabaseServerAddress;
    private String defaultRootPassword;
    private boolean autoConnect;
    
    File settingFile = new File("settings.ini");

    public XchSettings(){
        super();
        loadSetting();
    }
    
    @Override
    public void finalize() throws Throwable {
        saveSettings();
        super.finalize();
    }
    
    private void loadSetting(){

        try {
            FileReader reader = new FileReader(settingFile);
            Properties props = new Properties();
            props.load(reader);

            defaultDatabaseServerAddress = props.getProperty("Default Database Server Address");
            defaultRootPassword = props.getProperty("Default Database Server Address");
            if (props.getProperty("Default Database Server Address") == "true"){
                autoConnect = true;
            }else{
                autoConnect = false;
            }

            reader.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
            ex.printStackTrace();
        } catch (IOException ex) {
            // I/O error
            ex.printStackTrace();
        }    
    }
    
    public void saveSettings(){

        try {
            Properties props = new Properties();
            props.setProperty("Default Database Server Address", defaultDatabaseServerAddress);
            props.setProperty("Default Root Password", defaultRootPassword);
            props.setProperty("Auto Connect", autoConnect ? "true":"false");
            FileWriter writer = new FileWriter(settingFile);
            props.store(writer, "Database settings");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
            ex.printStackTrace();
        } catch (IOException ex) {
            // I/O error
            ex.printStackTrace();
        }    
    }

    public String getDefaultDatabaseServerName()
    {
        return defaultDatabaseServerAddress;
    }

    public void setDefaultDatabaseServerName(String defaultDatabaseServerName)
    {
        this.defaultDatabaseServerAddress = defaultDatabaseServerName;
    }

    public String getDefaultRootPassword()
    {
        return defaultRootPassword;
    }

    public void setDefaultRootPassword(String defaultRootPassword)
    {
        this.defaultRootPassword = defaultRootPassword;
    }

    public Boolean isAutoConnect()
    {
        return autoConnect;
    }

    public void setAutoConnect(Boolean autoConnect)
    {
        this.autoConnect = autoConnect;
    }
    
}
