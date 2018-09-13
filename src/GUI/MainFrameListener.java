/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Meteoric
 */
public class MainFrameListener extends MouseAdapter{
    final String CREATE_BUTTON = "Create QR";
    final String SAVE_BUTTON = "Save DB";
    ButtonHandler buttonHandler;
    
    public MainFrameListener(MainFrame mainFrame) {
        super();
        this.buttonHandler = (ButtonHandler) mainFrame;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equalsIgnoreCase(CREATE_BUTTON)) {
            this.buttonHandler.handleCreating();
        }
        if (button.getText().equalsIgnoreCase(SAVE_BUTTON)) {
            this.buttonHandler.handleSaving();
        }
    }
    
    public interface ButtonHandler {
        void handleCreating();
        void handleSaving();
    }
}
