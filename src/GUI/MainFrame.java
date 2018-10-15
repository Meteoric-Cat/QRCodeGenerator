/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    
    private LoginPanel loginPanel;
    private MainPanel mainPanel;
    
    public MainFrame() {
        this.initComponents();
    }
    
    private void initComponents() {      
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        this.loginPanel = new LoginPanel();
        this.mainPanel = new MainPanel();
        
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
}
