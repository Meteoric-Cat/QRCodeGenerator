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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import org.json.simple.JSONObject;

public class MainFrame extends JFrame {
    private final Font LABEL_FONT1 = new Font("Tahoma", Font.BOLD, 18);
    private final Font LABEL_FONT2 = new Font("Tahoma", Font.BOLD, 13);
    private final String QR_FILE_TYPES[] = new String[]{"JPEG", "PNG"};
    private final int DEFAULT_QR_SIZE = 250;
    
    private final String SERVER_DM_KEY = "ServerDomainName";
    private final String PRODUCT_ID_KEY = "ProductID";
    private final String PRODUCT_NAME_KEY = "ProductName";
    private final String MANUFACTURER_NAME_KEY = "ManufacturerName";
    private final String M_DATE_KEY = "ManufacturingDay";
    private final String E_DATE_KEY = "ExpirationDay";
    
    private JButton createButton;
    private JSpinner daySpinner1;
    private JSpinner daySpinner2;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JTextField manufacturerNameField;
    private JSpinner monthSpinner1;
    private JSpinner monthSpinner2;
    private JTextField productIDField;
    private JTextField productNameField;
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
        serverNameField = new JTextField();
        productIDField = new JTextField();
        jLabel4 = new JLabel();
        productNameField = new JTextField();
        jLabel5 = new JLabel();
        manufacturerNameField = new JTextField();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        qrNameField = new JTextField();
        jLabel10 = new JLabel();
        daySpinner1 = new JSpinner();
        monthSpinner1 = new JSpinner();
        yearSpinner1 = new JSpinner();
        daySpinner2 = new JSpinner();
        monthSpinner2 = new JSpinner();
        yearSpinner2 = new JSpinner();
        jLabel11 = new JLabel();
        qrTypeBox = new JComboBox<>();
        createButton = new JButton();
        saveButton = new JButton();
        qrSizeSpinner = new JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(LABEL_FONT1); // NOI18N
        jLabel1.setText("Thông tin sản phẩm");

        jLabel2.setFont(LABEL_FONT2); // NOI18N
        jLabel2.setText("Tên miền server");

        jLabel3.setFont(LABEL_FONT2); // NOI18N
        jLabel3.setText("ID sản phẩm");

        jLabel4.setFont(LABEL_FONT2); // NOI18N
        jLabel4.setText("Tên sản phẩm");

        jLabel5.setFont(LABEL_FONT2); // NOI18N
        jLabel5.setText("Tên NSX");

        jLabel6.setFont(LABEL_FONT2); // NOI18N
        jLabel6.setText("Ngày sản xuất");

        jLabel7.setFont(LABEL_FONT2); // NOI18N
        jLabel7.setText("Hạn sử dụng");

        jLabel8.setFont(LABEL_FONT1); // NOI18N
        jLabel8.setText("Thông tin file QR Code");

        jLabel9.setFont(LABEL_FONT2); // NOI18N
        jLabel9.setText("Tên");

        jLabel10.setFont(LABEL_FONT2); // NOI18N
        jLabel10.setText("Kích thước");

        jLabel11.setFont(LABEL_FONT2); // NOI18N
        jLabel11.setText("Loại");

        qrTypeBox.setModel(new DefaultComboBoxModel(QR_FILE_TYPES));

        createButton.setFont(LABEL_FONT2); // NOI18N
        createButton.setText("Tạo");

        saveButton.setFont(LABEL_FONT2); // NOI18N
        saveButton.setText("Lưu");
        
        serverNameField.setText("sleepy-depths-45970.herokuapp.com");
        qrNameField.setText("D://");
        
        Calendar now = Calendar.getInstance();
        daySpinner1.setValue(now.get(Calendar.DAY_OF_MONTH));
        monthSpinner1.setValue(now.get(Calendar.MONTH) + 1);
        yearSpinner1.setValue(now.get(Calendar.YEAR));
       
        daySpinner2.setValue(now.get(Calendar.DAY_OF_MONTH));
        monthSpinner2.setValue(now.get(Calendar.MONTH) + 1);
        yearSpinner2.setValue(now.get(Calendar.YEAR));
    }
    
    private void placeGUIComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(daySpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(monthSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(yearSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                    .addGap(27, 27, 27)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(productNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                        .addComponent(manufacturerNameField)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(daySpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(monthSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(yearSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(qrNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(qrSizeSpinner, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(qrTypeBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 76, Short.MAX_VALUE))))
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(serverNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(productIDField))))
                        .addGap(0, 58, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(serverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(productIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(productNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(manufacturerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(daySpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(daySpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(qrNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(qrSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(qrTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton)
                    .addComponent(saveButton))
                .addContainerGap(66, Short.MAX_VALUE))
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
        if (this.serverNameField.getText().isEmpty() || this.productIDField.getText().isEmpty()) {
            return null;
        } 
        
        JSONObject jsonObject = new JSONObject();
        String date;
        
        jsonObject.put(SERVER_DM_KEY, this.serverNameField.getText());
        jsonObject.put(PRODUCT_ID_KEY, this.productIDField.getText()); 
        jsonObject.put(PRODUCT_NAME_KEY, this.productNameField.getText());
        jsonObject.put(MANUFACTURER_NAME_KEY, this.manufacturerNameField.getText());
        
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
        System.out.println(jsonObject.toString());
        return jsonObject;
    }
    
    private class ButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton target = (JButton) e.getSource();
            if (target == MainFrame.this.createButton) {
                try {
//                    String mDate = String.join("/",
//                            MainFrame.this.daySpinner1.getValue().toString(),
//                            MainFrame.this.monthSpinner1.getValue().toString(),
//                            MainFrame.this.yearSpinner1.getValue().toString()
//                    );
//                    String eDate = String.join("/",
//                            MainFrame.this.daySpinner2.getValue().toString(),
//                            MainFrame.this.monthSpinner2.getValue().toString(),
//                            MainFrame.this.yearSpinner2.getValue().toString()
//                    );
//                    String value = String.join("||",
//                            MainFrame.this.serverNameField.getText(),
//                            MainFrame.this.productIDField.getText(),
//                            MainFrame.this.productNameField.getText(),
//                            MainFrame.this.manufacturerNameField.getText(),                           
//                            mDate,
//                            eDate
//                    );
                    
                    JSONObject productInfo = MainFrame.this.getProductInformation();

                    Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
                    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                    
                    MultiFormatWriter encoder = new MultiFormatWriter();
                    BitMatrix bitMatrix = encoder.encode(productInfo.toString(), BarcodeFormat.QR_CODE,
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
