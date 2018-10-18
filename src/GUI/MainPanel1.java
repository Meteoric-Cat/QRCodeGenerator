/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Helpers.Convertor;
import Helpers.ServerConnector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONObject;

/**
 *
 * @author Meteoric
 */
public class MainPanel1 extends javax.swing.JPanel {

    private JButton logoutButton;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JLabel productAmountLabel;
    private JButton productCreateButton;
    private JTable productTable;
    private DefaultTableModel tableModel;
    
    private ButtonListener buttonListener;
    
    private int clickedRowID;
    private int productAmount;

    public MainPanel1() {
        super();
        initComponents();
        initEventListeners();
    }

    private void initComponents() {

        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        productTable = new JTable();
        productAmountLabel = new JLabel();
        productCreateButton = new JButton();
        logoutButton = new JButton();

        jLabel1.setText("Số lượng sản phẩm:");

        jScrollPane1.setViewportView(productTable);
        tableModel = new DefaultTableModel(new Object[] {
            "ID sản phẩm",
            "Tên sản phẩm",
            "Tên nhà sản xuất",
            "Ngày sản xuất",
            "Ngày hết hạn",
            "Trạng thái",
        }, 0);
        productTable.setModel(tableModel);

        productAmountLabel.setText("0");

        productCreateButton.setText("Tạo sản phẩm");

        logoutButton.setText("Thoát");
        
        clickedRowID = 0;

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoutButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(productAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productCreateButton))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(productAmountLabel)
                    .addComponent(productCreateButton))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public void initEventListeners() {
        this.productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                MainPanel1.this.clickedRowID = MainPanel1.this.productTable.rowAtPoint(event.getPoint());   
                
                GUIManager.getInstance().productDialog.setEnabled(true);
                GUIManager.getInstance().productDialog.initData(
                        MainPanel1.this.tableModel.getValueAt(MainPanel1.this.clickedRowID, 0).toString(),
                        MainPanel1.this.tableModel.getValueAt(MainPanel1.this.clickedRowID, 1).toString(),
                        MainPanel1.this.tableModel.getValueAt(MainPanel1.this.clickedRowID, 2).toString(),
                        MainPanel1.this.tableModel.getValueAt(MainPanel1.this.clickedRowID, 3).toString(),
                        MainPanel1.this.tableModel.getValueAt(MainPanel1.this.clickedRowID, 4).toString()
                );
                GUIManager.getInstance().productDialog.setVisible(true);
            }
        });
        
        this.buttonListener = new ButtonListener();
        this.logoutButton.addMouseListener(this.buttonListener);
        this.productCreateButton.addMouseListener(this.buttonListener);
    }
    
    public void initData(JSONObject data) {
        productAmount = Integer.parseInt(data.get(ServerConnector.getInstance().RESPONSE_CODE_KEY).toString());
        this.productAmountLabel.setText(String.valueOf(productAmount));
        
        JSONObject productInfo;
        for (int i=1; i<=productAmount; i++) {
            productInfo = (JSONObject) data.get(String.valueOf(i));
            this.tableModel.addRow(Convertor.getInstance().convertProductToArray(productInfo));            
        }        
    }
    
    public void addProductInfo(String[] productInfo) {        
        productAmount++;
        this.productAmountLabel.setText(String.valueOf(productAmount));
        this.tableModel.addRow(productInfo);
        
    } 
    
    public void editProductInfo(String[] productInfo) {
        for (int i=0; i<productInfo.length; i++) {
            this.tableModel.setValueAt(productInfo[i], clickedRowID, i);
        }
    }
    
    public void deleteProductInfo() {
        productAmount--;
        this.productAmountLabel.setText(String.valueOf(productAmount));
        this.tableModel.removeRow(clickedRowID);
    }
    
    public class ButtonListener extends MouseAdapter {
        public ButtonListener() {
            super();
        }
        
        @Override
        public void mouseClicked(MouseEvent event) {
            JButton button = (JButton) event.getSource();
            
            if (button == MainPanel1.this.productCreateButton) {
                GUIManager.getInstance().productDialog.setEnabled(true);               
                GUIManager.getInstance().productDialog.reset();
                GUIManager.getInstance().productDialog.setVisible(true);
            }
            if (button == MainPanel1.this.logoutButton) {
                int rowAmount = MainPanel1.this.tableModel.getRowCount();
                for (int i=rowAmount-1; i>=0; i--) {
                    MainPanel1.this.tableModel.removeRow(i);
                }
                    
                GUIManager.getInstance().mainFrame.changePanel(MainFrame.PanelId.LOGIN_PANEL_ID);
            }
        }
    }   
}
