/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import org.json.simple.JSONObject;

/**
 *
 * @author Meteoric
 */
public class ServerConnector {
    public final String PRODUCT_ADD_PATH = "/add_product";
    public final String PRODUCT_CHECK_PATH = "/check_product";
    public final String ACCOUNT_LOGIN_PATH = "/login_account";
    
    private String serverDomainName;
    
    private static ServerConnector instance = new ServerConnector();

    private ServerConnector() {
    }
    
    public static ServerConnector getInstance() {
        return instance;
    }
    
    public void setServerDomainName(String serverDomainName) {
        this.serverDomainName = serverDomainName;
    }
    
    public void sendRequest(String path, JSONObject data) {
        //... send json object to server to process
    }
}
