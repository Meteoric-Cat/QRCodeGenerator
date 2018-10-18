/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Helpers.Convertor;
import Helpers.ServerConnector;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import org.json.simple.JSONObject;

/**
 *
 * @author Meteoric
 */
public class ProductDialog extends JDialog{
    private final Font LABEL_FONT1 = new Font("Tahoma", Font.BOLD, 18);
    private final Font LABEL_FONT2 = new Font("Tahoma", Font.BOLD, 13);
    private final String QR_FILE_TYPES[] = new String[]{"JPEG", "PNG"};
    private final int DEFAULT_QR_SIZE = 250;
    
//    private final String SERVER_DM_KEY = "ServerDomainName";
//    private final String PRODUCT_ID_KEY = "ProductID";
//    private final String PRODUCT_NAME_KEY = "ProductName";
//    private final String MANUFACTURER_NAME_KEY = "ManufacturerName";
//    private final String M_DATE_KEY = "ManufacturingDay";
//    private final String E_DATE_KEY = "ExpirationDay";
    
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
    private JButton editButton;
    private JButton deleteButton;
    
    private ButtonListener buttonListener;
    private SpinnerListener spinnerListener;
    
    public ProductDialog() {
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
        editButton = new JButton();
        deleteButton = new JButton();
        qrSizeSpinner = new JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

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
        
        editButton.setFont(LABEL_FONT2);
        editButton.setText("Sửa");
        
        deleteButton.setFont(LABEL_FONT2);
        deleteButton.setText("Xóa");
        
        //serverNameField.setText(ServerConnector.getInstance().getServerDomainName());
        qrNameField.setText("D://");
        qrSizeSpinner.setValue(DEFAULT_QR_SIZE);
        
        this.setDefaultSpinners();
    }
    
    private void setDefaultSpinners() {
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
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(serverNameField)
                            .addComponent(productIDField)
                            .addComponent(productNameField)
                            .addComponent(manufacturerNameField)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(daySpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(monthSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(daySpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(monthSpinner2)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yearSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                    .addComponent(yearSpinner2)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(qrNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(qrSizeSpinner, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(qrTypeBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 130, Short.MAX_VALUE)))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(serverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGap(7, 7, 7)
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton)
                    .addComponent(saveButton)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
                .addContainerGap(36, Short.MAX_VALUE))
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
        this.editButton.addMouseListener(this.buttonListener);
        this.deleteButton.addMouseListener(this.buttonListener);
    }
    
    public void initData(String... data) {
        this.serverNameField.setText(ServerConnector.getInstance().getServerDomainName());
        if (data != null) {            
            this.productIDField.setText(data[0]);
            this.productNameField.setText(data[1]);
            this.manufacturerNameField.setText(data[2]);
            
            String[] date = data[3].split("/");
            this.daySpinner1.setValue(Integer.valueOf(date[0]));
            this.monthSpinner1.setValue(Integer.valueOf(date[1]));
            this.yearSpinner1.setValue(Integer.valueOf(date[2]));
            
            date = data[4].split("/");
            this.daySpinner2.setValue(Integer.valueOf(date[0]));
            this.monthSpinner2.setValue(Integer.valueOf(date[1]));
            this.yearSpinner2.setValue(Integer.valueOf(date[2]));
        }
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

    public void setServerDomainName(String serverName) {
        this.serverNameField.setText(serverName);
    }
    
    public String getMDate() {
        return String.join("/",
                String.valueOf(this.daySpinner1.getValue()),
                String.valueOf(this.monthSpinner1.getValue()),
                String.valueOf(this.yearSpinner1.getValue())
        );
    }
    
    public String getEDate() {
        return String.join("/",
                String.valueOf(this.daySpinner2.getValue()),
                String.valueOf(this.monthSpinner2.getValue()),
                String.valueOf(this.yearSpinner2.getValue())
        );     
    }
    
    public JSONObject getProductInformation() {
        if (this.serverNameField.getText().isEmpty() || this.productIDField.getText().isEmpty()) {
            return null;
        } 
                
        return Convertor.getInstance().convertProductToJSON(
                this.serverNameField.getText(),
                this.productIDField.getText(),
                this.productNameField.getText(),
                this.manufacturerNameField.getText(),
                this.getMDate(),
                this.getEDate()
        );
    }
    
    public String[] getProductInformation(int amount, String productState) {
        String[] result = new String[amount];
        result[0] = this.productIDField.getText();
        result[1] = this.productNameField.getText();
        result[2] = this.manufacturerNameField.getText();
        result[3] = this.getMDate();
        result[4] = this.getEDate();
        if (amount == 6) {
            result[5] = productState;
        }
        return result;
    }
    
    public void reset() {
        this.productIDField.setText("");
        this.productNameField.setText("");
        this.manufacturerNameField.setText("");
        this.qrNameField.setText("D://");
        this.setDefaultSpinners();
    }
    
    private class ButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton target = (JButton) e.getSource();
            JSONObject productInfo = ProductDialog.this.getProductInformation();
            if (target == ProductDialog.this.createButton) {
                try {
                    Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
                    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                    
                    MultiFormatWriter encoder = new MultiFormatWriter();
                    BitMatrix bitMatrix = encoder.encode(productInfo.toString(), BarcodeFormat.QR_CODE,
                            Integer.parseInt(ProductDialog.this.qrSizeSpinner.getValue().toString()),
                            Integer.parseInt(ProductDialog.this.qrSizeSpinner.getValue().toString()),
                            hintMap
                    );
                   
                    MatrixToImageWriter.writeToPath(bitMatrix,
                            ProductDialog.this.qrTypeBox.getSelectedItem().toString(),
                            (new File(ProductDialog.this.qrNameField.getText())).toPath()
                    );                   
                } catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (target == ProductDialog.this.saveButton) {
                ServerConnector.getInstance().sendRequest(ServerConnector.PRODUCT_ADD_PATH, productInfo);                    
            } else if (target == ProductDialog.this.editButton) {
                ServerConnector.getInstance().sendRequest(ServerConnector.PRODUCT_EDIT_PATH, productInfo);
            } else if (target == ProductDialog.this.deleteButton) {
                ServerConnector.getInstance().sendRequest(ServerConnector.PRODUCT_DELETE_PATH, productInfo);                
            }
        }
    }
    
}
