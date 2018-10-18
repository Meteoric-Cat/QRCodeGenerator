/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Helpers.ServerConnector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Meteoric
 */
public class LoginPanel1 extends JPanel {
//    private final String ACCOUNT_KEY = "Account";
//    private final String PASSWORD_KEY = "Password";

    private JTextField accountField;
    private JLabel forgetPassword;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JButton loginButton;
    private JTextField passwordField;
    private JTextField serverNameField;
    
    private ButtonListener buttonListener;

    public LoginPanel1() {
        super();
        this.initComponents();
        this.initEventListeners();
    }

    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        serverNameField = new JTextField();
        jLabel3 = new JLabel();
        accountField = new JTextField();
        jLabel4 = new JLabel();
        passwordField = new JTextField();
        loginButton = new JButton();
        forgetPassword = new JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Nhập tài khoản");

        jLabel2.setText("Tên miền server");

        jLabel3.setText("Tài khoản");

        jLabel4.setText("Mật khẩu");

        serverNameField.setText("sleepy-depths-45970.herokuapp.com");
        
        loginButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        loginButton.setText("Đăng nhập");

        forgetPassword.setForeground(new java.awt.Color(153, 153, 255));
        forgetPassword.setText("Quen mat khau?");        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(serverNameField)
                    .addComponent(accountField)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(forgetPassword)))
                .addContainerGap(398, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(450, 450, 450))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(serverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(accountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(forgetPassword))
                .addContainerGap(313, Short.MAX_VALUE))
        );        
    }

    private void initEventListeners() {
        this.buttonListener = new ButtonListener();
        this.loginButton.addMouseListener(this.buttonListener);
    }
    
    public class ButtonListener extends MouseAdapter {
        public ButtonListener() {
            super();
        }        
        
        @Override
        public void mouseClicked(MouseEvent event) {
            JButton source = (JButton) event.getSource();
            if (source == LoginPanel1.this.loginButton) {
                ServerConnector.getInstance().setServerDomainName(LoginPanel1.this.serverNameField.getText());
                ServerConnector.getInstance().setAccount(LoginPanel1.this.accountField.getText());
                ServerConnector.getInstance().setPassword(LoginPanel1.this.passwordField.getText());
                ServerConnector.getInstance().sendRequest(ServerConnector.ACCOUNT_LOGIN_PATH, null);
                
                GUIManager.getInstance().mainFrame.changePanel(MainFrame.PanelId.MAIN_PANEL_ID);
            }
            
        }
    }
    
}

