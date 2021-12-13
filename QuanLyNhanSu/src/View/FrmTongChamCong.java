/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.BoPhanDAO;
import DAO.ChamCongDAO;
import DAO.HopDongDAO;
import DAO.NhanVienDAO;
import Helpers.MessageDialogHelper;
import Helpers.TableDesign;
import Model.BoPhan;
import Model.ChamCong;
import Model.HopDongLaoDong;
import Model.NhanVien;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author QUYNH
 */
public class FrmTongChamCong extends javax.swing.JFrame {

    /**
     * Creates new form rmTongChamCong
     */
    private int Thang;
    private int Nam;
    
    public FrmTongChamCong(int Thang, int Nam) {
        initComponents();
        setLocationRelativeTo(null);
        this.Thang = Thang;
        this.Nam = Nam;
        LoadComboBox();
        initTable();
        LoadTable();
        DesignTable();
    }

    public FrmTongChamCong() {

    }

    private Home home;
    private DefaultTableModel tblmodel;

    private void initTable() {
        tblmodel = new DefaultTableModel();
        String[] header = {"Mã nhân viên",
            "Tên nhân viên",
            "Tên phòng ban",
            "Tên chức vụ",
            "1","2","3","4","5","6","7","8","9","10",
            "11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30",
            "31"
        };
        tblmodel.setColumnIdentifiers(header);
        tblChamCong.setModel(tblmodel);
        TableDesign.Design(tblChamCong);
    }

    private void LoadTable() {
        try {
            HopDongDAO daonv = new HopDongDAO();
            List<HopDongLaoDong> list;
            BoPhanDAO bpdao = new BoPhanDAO();
            BoPhan bp = bpdao.FindBPByName(cbbBP.getSelectedItem().toString());
            tblmodel.setRowCount(0);
            if (cbbBP.getSelectedItem() == "Tất cả") {
                list = daonv.FindAll();
            } else {
                list = daonv.FindAllByDep(bp.getMaPB());
            }
            list.forEach(it -> {
                LoadData(it.getMaNV().trim());
            });
        } catch (Exception ex) {
//            MessageDialogHelper.showError(home, ex.getMessage(), "Lỗi");
        }
    }

    private void LoadData(String MaNV) {
        try {
            NhanVienDAO daonv = new NhanVienDAO();
            NhanVien nv = daonv.FindNhanVien(MaNV);
            String[] data = {MaNV, nv.getTenNV(), nv.getTenPB(), nv.getTenCV()};
            List<String> newData = new ArrayList<>(Arrays.asList(data));
            for (int i = 1; i < 32; i++) {
                ChamCongDAO daocc = new ChamCongDAO();
                ChamCong cc = daocc.FindTongCC(MaNV, Thang, Nam, i);
                if (cc != null) {
                    String TinhTrang = cc.getGhiChu();
                    newData.add(TinhTrang);
                    continue;
                }
                newData.add("");
            }
            tblmodel.addRow(newData.toArray());
        } catch (Exception ex) {
            MessageDialogHelper.showError(home, ex.getMessage(), "Lỗi");
        }
    }

    private void LoadComboBox() {
        try {
            cbbBP.removeAllItems();
            BoPhanDAO dao3=new BoPhanDAO();
            List<BoPhan> list3= dao3.FindAllBoPhan();
            cbbBP.addItem("Tất cả");
            list3.forEach(it -> {
                cbbBP.addItem(it.getTenPB());
            });
        }catch(Exception ex){
            MessageDialogHelper.showError(home, ex.getMessage(), "Lỗi");
        }
    }
    
    private void DesignTable(){
        tblChamCong.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblChamCong.getColumnModel().getColumn(0).setPreferredWidth(200);
        tblChamCong.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblChamCong.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblChamCong.getColumnModel().getColumn(3).setPreferredWidth(200);
        //Canh giữa
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblChamCong.setModel(tblmodel);
        for (int i = 4; i < 35; i++) {
            tblChamCong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            tblChamCong.getColumnModel().getColumn(i).setPreferredWidth(50);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChamCong = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbbBP = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TỔNG CHẤM CÔNG");

        tblChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Tên bộ phận", "tên chức vụ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "Title 28", "Title 29", "Title 30", "Title 31", "Title 32", "Title 33", "Title 34", "Title 35"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblChamCong);

        jLabel2.setText("C: đi làm; CT: công tác; KL: nghỉ không lương; CL: nghỉ có lương");

        cbbBP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbBP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbBPItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Bộ phận");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1207, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbBP, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbBPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbBPItemStateChanged
        LoadTable();
    }//GEN-LAST:event_cbbBPItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmTongChamCong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTongChamCong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTongChamCong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTongChamCong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmTongChamCong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbBP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblChamCong;
    // End of variables declaration//GEN-END:variables
}