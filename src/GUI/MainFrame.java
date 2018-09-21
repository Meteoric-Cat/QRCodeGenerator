/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import org.json.simple.JSONObject;

public class MainFrame extends JFrame {
    private final Font LABEL_FONT1 = new Font("Tahoma", Font.BOLD, 18);
    private final Font LABEL_FONT2 = new Font("Tahoma", Font.BOLD, 14);
    private final String QR_FILE_TYPES[] = new String[]{"JPEG", "PNG"};
    private final int DEFAULT_QR_SIZE = 250;
    
    private final String SERVER_DM_KEY = "ServerDomainName";
    private final String PRODUCT_ID_KEY = "ProductionID";
    private final String M_DATE_KEY = "ManufacturingDay";
    private final String E_DATE_KEY = "ExpirationDay";
    
    private JButton createButton;
    private JSpinner daySpinner1;
    private JSpinner daySpinner2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JSpinner monthSpinner1;
    private JSpinner monthSpinner2;
    private JTextField productionIDField;
    private JTextField qrNameField;
    private JSpinner qrSizeSpinner;
    private JComboBox<String> qrTypeBox;
    private JButton saveButton;
    private JTextField serverNameField;
    private JSpinner yearSpinner1;
    private JSpinner yearSpinner2;  
    
    private ButtonListener buttonListener;
    private SpinnerListener spinnerListener;
    
    public MainFrame() {
        initGUIComponents();        
        placeGUIComponents();        
        initGUIListeners();
    }
    
    private void initGUIComponents() {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        serverNameField = new JTextField();
        productionIDField = new JTextField();
        daySpinner1 = new JSpinner();
        monthSpinner1 = new JSpinner();
        yearSpinner1 = new JSpinner();
        daySpinner2 = new JSpinner();
        monthSpinner2 = new JSpinner();
        yearSpinner2 = new JSpinner();
        qrNameField = new JTextField();
        qrSizeSpinner = new JSpinner();
        qrTypeBox = new JComboBox<>();
        createButton = new JButton();
        saveButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        serverNameField.setText("sleepy-depths-45970.herokuapp.com");

        jLabel1.setFont(LABEL_FONT1); // NOI18N
        jLabel1.setText("Thông tin sản phẩm");

        jLabel2.setFont(LABEL_FONT2);
        jLabel2.setText("Tên miền server");

        jLabel3.setFont(LABEL_FONT2);
        jLabel3.setText("ID sản phẩm");

        jLabel4.setFont(LABEL_FONT2);
        jLabel4.setText("Ngày sản xuất");

        jLabel5.setFont(LABEL_FONT2);
        jLabel5.setText("Hạn dùng");

        jLabel6.setFont(LABEL_FONT1);
        jLabel6.setText("Thông tin file QR Code");

        jLabel7.setFont(LABEL_FONT2);
        jLabel7.setText("Tên");

        jLabel8.setFont(LABEL_FONT2);
        jLabel8.setText("Kích thước");

        jLabel9.setFont(LABEL_FONT2);
        jLabel9.setText("Loại");

        qrTypeBox.setModel(new DefaultComboBoxModel<>(QR_FILE_TYPES));

        createButton.setFont(LABEL_FONT2); // NOI18N
        createButton.setText("Tạo");

        saveButton.setFont(LABEL_FONT2); // NOI18N
        saveButton.setText("Lưu");      

        Calendar now = Calendar.getInstance();        
        daySpinner1.setValue(now.get(Calendar.DAY_OF_MONTH));
        daySpinner2.setValue(now.get(Calendar.DAY_OF_MONTH));
        
        monthSpinner1.setValue(now.get(Calendar.MONTH) + 1);
        monthSpinner2.setValue(now.get(Calendar.MONTH) + 1);
        
        yearSpinner1.setValue(now.get(Calendar.YEAR));
        yearSpinner2.setValue(now.get(Calendar.YEAR));        
        
        qrSizeSpinner.setValue(DEFAULT_QR_SIZE);
    }
    
    private void placeGUIComponents() {
        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(serverNameField)
                            .addComponent(productionIDField)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(daySpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(monthSpinner2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(daySpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(monthSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(yearSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(yearSpinner2)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qrTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qrNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qrSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(serverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(productionIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(daySpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(daySpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(qrNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(qrSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(qrTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton)
                    .addComponent(saveButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();                
    }
    
    private void initGUIListeners() {
        this.spinnerListener = new SpinnerListener(this);
        this.daySpinner1.addChangeListener(this.spinnerListener);
        this.daySpinner2.addChangeListener(this.spinnerListener);
        this.monthSpinner1.addChangeListener(this.spinnerListener);
        this.monthSpinner2.addChangeListener(this.spinnerListener);   
        
        this.buttonListener = new ButtonListener();
        this.createButton.addMouseListener(this.buttonListener);
        this.saveButton.addMouseListener(this.buttonListener);
    }
    
    public JSpinner[] getDaySpinners() {
        JSpinner result[] = new JSpinner[2];
        result[0] = this.daySpinner1;
        result[1] = this.daySpinner2;
        return result;
    }
    
    public JSpinner[] getMonthSpinners() {
        JSpinner result[] = new JSpinner[2];
        result[0] = this.monthSpinner1;
        result[1] = this.monthSpinner2;
        return result;
    }
    
    public String getServerDomainName() {
        return this.serverNameField.getText();
    }

    public JSONObject getProductInformation() {
        if (this.serverNameField.getText().isEmpty() || this.productionIDField.getText().isEmpty()) {
            return null;
        } 
        
        JSONObject jsonObject = new JSONObject();
        String date;
        
        jsonObject.put(SERVER_DM_KEY, this.serverNameField.getText());
        jsonObject.put(PRODUCT_ID_KEY, this.productionIDField.getText());        
        
        date = String.join("/",
                String.valueOf(this.daySpinner1.getValue()),
                String.valueOf(this.monthSpinner1.getValue()),
                String.valueOf(this.yearSpinner1.getValue())
        );
        jsonObject.put(M_DATE_KEY, date);
        
        date = String.join("/",
                String.valueOf(this.daySpinner2.getValue()),
                String.valueOf(this.monthSpinner2.getValue()),
                String.valueOf(this.yearSpinner2.getValue())
        );                
        jsonObject.put(E_DATE_KEY, date);
        
        return jsonObject;
    }
    
    private class ButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton target = (JButton) e.getSource();
            if (target == MainFrame.this.createButton) {
                try {
                    String mDate = String.join("/",
                            MainFrame.this.daySpinner1.getValue().toString(),
                            MainFrame.this.monthSpinner1.getValue().toString(),
                            MainFrame.this.yearSpinner1.getValue().toString()
                    );
                    String eDate = String.join("/",
                            MainFrame.this.daySpinner2.getValue().toString(),
                            MainFrame.this.monthSpinner2.getValue().toString(),
                            MainFrame.this.yearSpinner2.getValue().toString()
                    );
                    String value = String.join("||",
                            MainFrame.this.serverNameField.getText(),
                            MainFrame.this.productionIDField.getText(),
                            mDate,
                            eDate
                    );
                    
                    Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
                    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                    
                    MultiFormatWriter encoder = new MultiFormatWriter();
                    BitMatrix bitMatrix = encoder.encode(value, BarcodeFormat.QR_CODE,
                            Integer.parseInt(MainFrame.this.qrSizeSpinner.getValue().toString()),
                            Integer.parseInt(MainFrame.this.qrSizeSpinner.getValue().toString()),
                            hintMap
                    );
                   
                    MatrixToImageWriter.writeToPath(bitMatrix,
                            MainFrame.this.qrTypeBox.getSelectedItem().toString(),
                            (new File(MainFrame.this.qrNameField.getText())).toPath()
                    );
                } catch (WriterException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (target == MainFrame.this.saveButton) {
                    GUIManager.getInstance().loginDialog.setVisible(true);
                }
        }
    }
}
