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
    private String defaultDatabaseName;
    private boolean autoConnect;
    private boolean savePassword;
    
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

            defaultDatabaseServerAddress = props.getProperty("DefaultDatabaseServerAddress");
            defaultRootPassword = props.getProperty("DefaultRootPassword");
            defaultDatabaseName = props.getProperty("DefaultDatabaseName");
            autoConnect = props.getProperty("AutoConnect").equals("true");
            savePassword = props.getProperty("SavePassword").equals("true");

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
            props.setProperty("DefaultDatabaseServerAddress", defaultDatabaseServerAddress);
            props.setProperty("DefaultRootPassword", defaultRootPassword);
            props.setProperty("DefaultDatabaseName", defaultDatabaseName);
            props.setProperty("AutoConnect", autoConnect ? "true":"false");
            props.setProperty("SavePassword", savePassword ? "true":"false");
            FileWriter writer = new FileWriter(settingFile);
            props.store(writer, "Database Settings");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
            ex.printStackTrace();
        } catch (IOException ex) {
            // I/O error
            ex.printStackTrace();
        }    
    }

    public String getDefaultDatabaseServerAddress()
    {
        return defaultDatabaseServerAddress;
    }

    public void setDefaultDatabaseServerAddress(String defaultDatabaseServerAddress)
    {
        this.defaultDatabaseServerAddress = defaultDatabaseServerAddress;
    }

    public String getDefaultRootPassword()
    {
        return defaultRootPassword;
    }

    public void setDefaultRootPassword(String defaultRootPassword)
    {
        this.defaultRootPassword = defaultRootPassword;
    }

    public boolean isAutoConnect()
    {
        return autoConnect;
    }

    public void setAutoConnect(boolean autoConnect)
    {
        this.autoConnect = autoConnect;
    }

    public boolean isSavePassword()
    {
        return savePassword;
    }
    
    public void setSavePassword(boolean savePassword)
    {
        this.savePassword = savePassword;
    }

    void setDefaultDatabaseName(String defaultDatabaseName)
    {
        this.defaultDatabaseName = defaultDatabaseName;
    }

    String getDefaultDatabaseName()
    {
        return defaultDatabaseName;
    }
    
}
