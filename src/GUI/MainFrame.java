/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;
import org.json.simple.JSONObject;

public class MainFrame extends JFrame {
    
    private LoginPanel1 loginPanel;
    private MainPanel1 mainPanel;
    
    public MainFrame() {
        this.initComponents();
    }
    
    private void initComponents() {      
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1100, 750);
        this.setResizable(false);
        
        this.loginPanel = new LoginPanel1();
        this.mainPanel = new MainPanel1();
        
        this.setContentPane(this.loginPanel);
    }
    
    public void changePanel(int id) {
        switch (id) {
            case PanelId.LOGIN_PANEL_ID: 
                this.setContentPane(this.loginPanel);
                break;            
            case PanelId.MAIN_PANEL_ID: 
                this.setContentPane(this.mainPanel);
                break;            
        }
    }            
    
    public class PanelId {
        public static final int LOGIN_PANEL_ID = 1;
        public static final int MAIN_PANEL_ID = 2;
    }
    
    public MainPanel1 getMainPanel() {
        return mainPanel;
    }
}
