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
    ButtonHandler buttonHandler;
    
    public MainFrameListener(MainFrame mainFrame) {
        super();
        this.buttonHandler = (ButtonHandler) mainFrame;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        this.buttonHandler.handleButton((JButton) e.getSource());
    }
    
    public interface ButtonHandler {
        public void handleButton(JButton button);
    }
}
