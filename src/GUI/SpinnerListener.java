/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Meteoric
 */
public class SpinnerListener implements ChangeListener{
    private final int MIN_DAY = 1, MAX_DAY = 31, MIN_MONTH = 1, MAX_MONTH = 12;
    
    private JSpinner daySpinner[], monthSpinner[];   
    
    public SpinnerListener(ProductDialog dialog) {
        this.daySpinner = dialog.getDaySpinners();
        this.monthSpinner = dialog.getMonthSpinners();        
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        JSpinner spinner = (JSpinner) ce.getSource();
        int value = Integer.parseInt(spinner.getValue().toString());
        
        for (int i = 0; i < this.daySpinner.length; i++) {
            if (spinner == this.daySpinner[i]) {
                if (value < MIN_DAY) {
                    spinner.setValue(MAX_DAY);
                }
                if (value > MAX_DAY) {
                    spinner.setValue(MIN_DAY);
                }
                return;
            }        
        }
        
        for (int i = 0; i < this.monthSpinner.length; i++) {
            if (spinner == this.monthSpinner[i]) {
                if (value < MIN_MONTH) {
                    spinner.setValue(MAX_MONTH);
                }
                if (value > MAX_MONTH) {
                    spinner.setValue(MIN_MONTH);
                }
            }
        }
    }    
}
