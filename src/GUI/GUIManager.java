package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Meteoric
 */
public class GUIManager {
    public MainFrame mainFrame;
    public LoginDialog loginDialog;
    
    private static GUIManager instance = new GUIManager();
    
    public GUIManager() {
        this.mainFrame = new MainFrame();
        this.loginDialog = new LoginDialog();    
    }
    
    public static GUIManager getInstance() {
        return instance;
    }
}
