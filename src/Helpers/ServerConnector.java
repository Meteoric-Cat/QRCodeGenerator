/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import GUI.GUIManager;
import GUI.MainFrame;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Meteoric
 */
public class ServerConnector {

    public static final String PRODUCT_ADD_PATH = "/add_product";
    public static final String PRODUCT_CHECK_PATH = "/check_product";
    public static final String PRODUCT_EDIT_PATH = "/edit_product";
    public static final String PRODUCT_DELETE_PATH = "/delete_product";
    public static final String ACCOUNT_LOGIN_PATH = "/login_account";  
    
    public final String RESPONSE_CODE_KEY = "Code";
    public final String RESPONSE_MSG_KEY = "Message";

    private final String ACCOUNT_KEY = "Account";
    private final String PASSWORD_KEY = "Password";
    private final String STATE_KEY = "ProductState";
    private final String PRODUCTION_KEY = "ProductionInformation";

    private final String REQUEST_METHOD_1 = "POST";
    private final String REQUEST_PROPERTY_1 = "Content-Type";
    private final String RP_VALUE_1 = "application/json;";
    private final String SERVER_FAULT_1 = "Server didn't response";
    private final String SERVER_PROTOCOL = "https://";
    
    private final int YES = 0;
    private final int NO = -1;

    private String serverDomainName;
    private String account;
    private String password;

    private static ServerConnector instance = new ServerConnector();

    private ServerConnector() {
    }

    public static ServerConnector getInstance() {
        return instance;
    }

    public void sendRequest(String path, JSONObject data) {
        //... send json object to server to process           
        JSONObject finalData = new JSONObject();
        finalData.put(ACCOUNT_KEY, this.account);
        finalData.put(PASSWORD_KEY, this.password);
        if (data != null) {
            finalData.put(PRODUCTION_KEY, data);
        }

        try {
            URL url = new URL(SERVER_PROTOCOL + serverDomainName + path);

            HttpsURLConnection serverConnection = (HttpsURLConnection) url.openConnection();
            serverConnection.setRequestMethod(REQUEST_METHOD_1);
            serverConnection.addRequestProperty(REQUEST_PROPERTY_1, RP_VALUE_1);

            serverConnection.setDoOutput(true);
            OutputStream output = serverConnection.getOutputStream();
            output.write(finalData.toString().getBytes());
            output.close();

            serverConnection.setDoInput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));

            String temp = reader.readLine();
            StringBuilder messageBuilder = new StringBuilder();
            if (temp == null) {
                //responseMessage = SERVER_FAULT_1;                
                JOptionPane.showMessageDialog(null, SERVER_FAULT_1);
                return;
            }
            messageBuilder.append(temp);

            while ((temp = reader.readLine()) != null) {
                messageBuilder.append(temp);
            }

            JSONParser parser = new JSONParser();
            JSONObject responseData = (JSONObject) parser.parse(messageBuilder.toString());
            
            if (path.equals(ACCOUNT_LOGIN_PATH)) {
                this.handleLoginResult(responseData);                        
            } else {
                int messageCode = (int) data.get(RESPONSE_CODE_KEY);
                String message = (String) data.get(RESPONSE_MSG_KEY);
                JOptionPane.showMessageDialog(null, message);

//                if (path.equals(PRODUCT_ADD_PATH)) {
//                   this.handlePAResult(responseData);
//                } else if (path.equals(PRODUCT_EDIT_PATH)) {
//                    this.handlePEResult(responseData);                
//                } else if (path.equals(PRODUCT_DELETE_PATH)) {
//                   this.handlePDResult(responseData);
//                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleLoginResult(JSONObject data) {
        int resultCode = (int) data.get(RESPONSE_CODE_KEY);
        
        if (resultCode < 0) {
            GUIManager.getInstance().mainFrame.changePanel(MainFrame.PanelId.MAIN_PANEL_ID);
            GUIManager.getInstance().mainFrame.getMainPanel().initData(data);
        }
    }
    
    private void handlePAResult(int messageCode) {
        if (messageCode == YES) {
            GUIManager.getInstance().mainFrame.getMainPanel().addProductInfo(
                    GUIManager.getInstance().productDialog.getProductInformation(6, "True")
            );
            GUIManager.getInstance().productDialog.reset();
        }
    }
    
    private void handlePEResult(int messageCode) {
        if (messageCode == YES) {
        }
    }
    
    private void handlePDResult(int messageCode) {
        
    }
    
    public void setServerDomainName(String serverDomainName) {
        this.serverDomainName = serverDomainName;
    }

    public String getServerDomainName() {
        return this.serverDomainName;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
