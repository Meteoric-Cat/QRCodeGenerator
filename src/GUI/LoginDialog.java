/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.json.simple.JSONObject;

/**
 *
 * @author Meteoric
 */
public class LoginDialog extends JDialog{
    private final Font LABEL_FONT = new Font("Tahoma", Font.BOLD, 14);
    
    private final String ACCOUNT_KEY = "Account";
    private final String PASSWORD_KEY = "Password";
    private final String PRODUCTION_KEY = "ProductionInformation";

    private final String SUB_DIALOG_TITLE = "System announcement";
    private final String SERVER_FAULT_1 = "Could not find the server";
    private final String SYSTEM_ERROR_1 = "Lacking some product information";
    
    private final String SERVER_PROTOCOL = "https://";
    private final String POST_METHOD = "POST";
    
    private final String REQUEST_PROPERTY_1 = "Content-Type";
    private final String RP1_VALUE = "application/json; charset=UTF-8";
    
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton okButton, cancelButton;
    
    private ButtonListener buttonListener;
    
    public LoginDialog() {
        initGUIComponents();
        placeGUIComponents();
        initGUIListeners();
    }
    
    private void initGUIComponents() {
        jLabel2 = new JLabel();
        jLabel1 = new JLabel();
        accountField = new JTextField();
        passwordField = new JPasswordField();
        okButton = new JButton();
        cancelButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(LABEL_FONT); // NOI18N
        jLabel2.setText("Tài khoản");

        jLabel1.setFont(LABEL_FONT); // NOI18N
        jLabel1.setText("Mật khẩu");

        okButton.setText("OK");

        cancelButton.setText("CANCEL");        
    }
    
    private void placeGUIComponents() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton))
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(accountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();        
    }
    
    private void initGUIListeners() {
        this.buttonListener = new ButtonListener();
        this.okButton.addMouseListener(this.buttonListener);
        this.cancelButton.addMouseListener(this.buttonListener);
    }
    
    private class ButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            if (source == LoginDialog.this.okButton) {
                sendDataToServer();
            } 
            if (source == LoginDialog.this.cancelButton) {
                GUIManager.getInstance().loginDialog.setVisible(false);
            }
        }                
    }
    
    private void sendDataToServer() {        
        JSONObject productInfo = GUIManager.getInstance().mainFrame.getProductInformation();
        if (productInfo == null) {
            JOptionPane.showMessageDialog(null, SYSTEM_ERROR_1, SUB_DIALOG_TITLE, JOptionPane.PLAIN_MESSAGE);
            return;
        }
            
        JSONObject contentObject = new JSONObject();                
        contentObject.put(ACCOUNT_KEY, LoginDialog.this.accountField.getText());
        contentObject.put(PASSWORD_KEY, new String(LoginDialog.this.passwordField.getPassword()));
        contentObject.put(PRODUCTION_KEY, productInfo);                
                
        try {
            String serverDN = GUIManager.getInstance().mainFrame.getServerDomainName();
            if (serverDN.isEmpty()) {
                JOptionPane.showMessageDialog(null, SERVER_FAULT_1, SUB_DIALOG_TITLE, JOptionPane.WARNING_MESSAGE);
            } 
            URL serverUrl = new URL(SERVER_PROTOCOL + serverDN);
            HttpsURLConnection serverConnection = (HttpsURLConnection) serverUrl.openConnection();
            serverConnection.setRequestMethod(POST_METHOD);
            serverConnection.setRequestProperty(REQUEST_PROPERTY_1, RP1_VALUE);                   
        
            serverConnection.setDoOutput(true);
            OutputStream outputStream = serverConnection.getOutputStream();
            outputStream.write(contentObject.toString().getBytes());
            outputStream.close();
                   
            serverConnection.setDoInput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
            String responseMessage = reader.readLine();
            if (responseMessage.isEmpty()) {
                responseMessage = SERVER_FAULT_1;
            }
            JOptionPane.showMessageDialog(null, responseMessage, SUB_DIALOG_TITLE, JOptionPane.PLAIN_MESSAGE);                    
            reader.close();                    
        } catch (Exception ex) {
            //Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), SUB_DIALOG_TITLE, JOptionPane.PLAIN_MESSAGE);
        }        
    }
}
