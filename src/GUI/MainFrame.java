/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    
    private LoginPanel1 loginPanel;
    private MainPanel1 mainPanel;
    private JPanel contentPanel;
    
    public MainFrame() {
        super();
        this.initComponents();
    }
    
    private void initComponents() {      
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1100, 800);
        this.setResizable(false);
        
        this.loginPanel = new LoginPanel1();
        this.mainPanel = new MainPanel1();
        this.contentPanel = (JPanel) this.getContentPane();
        
        this.contentPanel.add(this.loginPanel);
    }
    
    public void changePanel(int id) {
        switch (id) {
            case PanelId.LOGIN_PANEL_ID:                 
                this.contentPanel.removeAll();
                
                this.loginPanel.setEnabled(true);
                this.loginPanel.setVisible(true);
                this.contentPanel.add(this.loginPanel);
                
                this.contentPanel.revalidate();
                this.contentPanel.repaint();
                
                this.mainPanel.setVisible(false);
                this.mainPanel.setEnabled(false);
                break;            
            case PanelId.MAIN_PANEL_ID: 
                this.contentPanel.removeAll();
                
                this.mainPanel.setEnabled(true);
                this.mainPanel.setVisible(true);
                this.contentPanel.add(this.mainPanel);
                
                this.contentPanel.revalidate();
                this.contentPanel.repaint();
                
                this.loginPanel.setVisible(false);
                this.loginPanel.setEnabled(false);
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
