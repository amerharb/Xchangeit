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
public class XchSettings
{
    //Test still an idea
    private String defaultServerName;
    private String defaultRootPassword;
    private String autoConnect;
    
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
        //read from text file the settings
    }
    
    private void saveSettings(){
        //Save to text file 
    }

    public String getDefaultServerName()
    {
        return defaultServerName;
    }

    public void setDefaultServerName(String defaultServerName)
    {
        this.defaultServerName = defaultServerName;
    }

    public String getDefaultRootPassword()
    {
        return defaultRootPassword;
    }

    public void setDefaultRootPassword(String defaultRootPassword)
    {
        this.defaultRootPassword = defaultRootPassword;
    }

    public String getAutoConnect()
    {
        return autoConnect;
    }

    public void setAutoConnect(String autoConnect)
    {
        this.autoConnect = autoConnect;
    }
    
}
