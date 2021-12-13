/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.BoPhanDAO;
import DAO.HopDongDAO;
import DAO.LyLichDAO;
import DAO.NhanVienDAO;
import Helpers.DatabaseHelper;
import Helpers.Format;
import Helpers.ImageHelper;
import Helpers.MessageDialogHelper;
import Helpers.TableDesign;
import Model.BoPhan;
import Model.HopDongLaoDong;
import Model.LyLich;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class FrLyLich extends javax.swing.JInternalFrame {

    /**
     * Creates new form LyLich
     */
    public FrLyLich() {
        initComponents();
        initTable();
        LoadTable();
        Design();
    }
    
    SimpleDateFormat fm= new SimpleDateFormat("yyyy-MM-dd");
    private byte[] personalImage;
    private DefaultTableModel tblmodel;
    private Home home;
    
    private void initTable(){
        tblmodel =new DefaultTableModel();
        String[] header=new String[]{"Mã nhân viên",
                                    "Tên nhân viên",
                                    "Quốc tịch",
                                    "CMND",
                                    "Ngày sinh",
                                    "Giới tính",
                                    "Tình trạng hôn nhân",
                                    "Phụ thuộc",
                                    "Địa chỉ",
                                    "Số điện thoại",
                                    "Học vấn",
                                    "Anh văn",
                                    "Tin học"};
        tblmodel.setColumnIdentifiers(header);
        tblLyLich1.setModel(tblmodel);
        TableDesign.Design(tblLyLich1);
        
    }
    
    private void LoadTable(){
        try{
            LyLichDAO dao=new LyLichDAO();
            List<LyLich> list;
            list = dao.FindAll();
            tblmodel.setRowCount(0);
            list.forEach(it -> {
                tblmodel.addRow(new Object[]{it.getMaNV(),
                                        it.getTenNV(),
                                        it.getQuocTich(),
                                        it.getCMND(),
                                        it.getNgaySinh(),
                                        it.getGioiTinh()==1?"Nam" : "Nữ",
                                        it.getTinhTrangHN()==1?"Kết hôn":"Độc thân",
                                        it.getPhuThuoc(),
                                        it.getDiaChi(),
                                        it.getSoDienThoai(),
                                        it.getHocVan(),
                                        it.getAnhVan(),
                                        it.getTinHoc()});
            });
            tblmodel.fireTableDataChanged();
        }catch(Exception ex){
            MessageDialogHelper.showError(home, ex.toString(), "Lỗi");
        }
    }
  
    private void Design() {
        tblLyLich1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblLyLich1.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblLyLich1.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblLyLich1.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblLyLich1.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblLyLich1.getColumnModel().getColumn(4).setPreferredWidth(80);
        tblLyLich1.getColumnModel().getColumn(5).setPreferredWidth(80);
        tblLyLich1.getColumnModel().getColumn(6).setPreferredWidth(180);
        tblLyLich1.getColumnModel().getColumn(7).setPreferredWidth(150);
        tblLyLich1.getColumnModel().getColumn(8).setPreferredWidth(300);
        tblLyLich1.getColumnModel().getColumn(9).setPreferredWidth(150);
        tblLyLich1.getColumnModel().getColumn(10).setPreferredWidth(200);
        tblLyLich1.getColumnModel().getColumn(11).setPreferredWidth(100);
        tblLyLich1.getColumnModel().getColumn(12).setPreferredWidth(100);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLyLich = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnIn = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        rdbKetHon = new javax.swing.JRadioButton();
        rdbDocThan = new javax.swing.JRadioButton();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtHocVan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtAnhVan = new javax.swing.JTextField();
        txtTinHoc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        dpNgaySinh = new com.toedter.calendar.JDateChooser();
        lblImage = new javax.swing.JLabel();
        btnBrows = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtQuocTich = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtPhuThuoc = new javax.swing.JSpinner();
        txtMaNV = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLyLich1 = new javax.swing.JTable();

        tblLyLich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblLyLich);

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("LÝ LỊCH");

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_save_40px.png"))); // NOI18N
        btnThem.setText("Lưu");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_delete_40px.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnIn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_print_40px.png"))); // NOI18N
        btnIn.setText("In");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_restart_40px.png"))); // NOI18N
        btnNew.setText("Làm mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNew)
                .addContainerGap(671, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnXoa)
                    .addComponent(btnIn)
                    .addComponent(btnNew))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã nhân viên");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên nhân viên");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("CMND");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tình trạng hôn nhân");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Giới tính");

        buttonGroup1.add(rdbKetHon);
        rdbKetHon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbKetHon.setText("Kết hôn");

        buttonGroup1.add(rdbDocThan);
        rdbDocThan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbDocThan.setText("Độc thân");

        buttonGroup2.add(rdbNam);
        rdbNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbNam.setText("Nam");
        rdbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNamActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdbNu);
        rdbNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbNu.setText("Nữ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Ngày Sinh");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Số điện thoại");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Học vấn");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Ngoại ngữ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Tin học");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Ghi chú");

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnBrows.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBrows.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_pictures_folder_16px.png"))); // NOI18N
        btnBrows.setText("Tải ảnh");
        btnBrows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowsActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Quốc tịch");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Phụ thuộc");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(txtCMND)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(rdbKetHon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdbDocThan))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel15))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdbNam)
                                .addGap(24, 24, 24)
                                .addComponent(rdbNu))
                            .addComponent(txtPhuThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dpNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))))
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(txtDiaChi)
                            .addComponent(txtQuocTich)
                            .addComponent(txtHocVan)
                            .addComponent(txtAnhVan)
                            .addComponent(txtTinHoc)
                            .addComponent(txtGhiChu))))
                .addGap(118, 118, 118)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnBrows, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(190, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBrows))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtHocVan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtAnhVan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTinHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rdbKetHon)
                            .addComponent(rdbDocThan))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdbNu)
                                .addComponent(rdbNam))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(dpNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtPhuThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        tblLyLich1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblLyLich1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLyLich1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblLyLich1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 333, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(312, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked

    }//GEN-LAST:event_btnThemMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        StringBuilder sb = new StringBuilder();
        if (sb.length() > 0) {
            MessageDialogHelper.showError(this, sb.toString(), "Lỗi");
            return;
        }
        try {
            LyLich nv = SetLyLich();
            LyLichDAO dao = new LyLichDAO();
            LyLich find = dao.FindNhanVien(txtMaNV.getText());
            if (MessageDialogHelper.showConfirm(home, "Tiếp tục thưc hiện?", "Thông báo") == JOptionPane.NO_OPTION) {
                return;
            }
            if (find != null) {
                if (dao.update(nv)) {
                    MessageDialogHelper.showMessage(this, "Đã lưu", "Thông báo");
                    LoadTable();
                } else {
                    MessageDialogHelper.showError(this, "Không thể lưu", "Lỗi");
                }
            } else {
                if (dao.insert(nv)) {
                    MessageDialogHelper.showMessage(this, "Đã lưu", "Thông báo");
                    LoadTable();
                } else {
                    MessageDialogHelper.showError(this, "Không thể lưu", "Lỗi");
                }
            }

        } catch (Exception ex) {
            MessageDialogHelper.showError(this, ex.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        try {
            java.sql.Connection conn= DatabaseHelper.openConnection();
            String link="C:\\Users\\QUYNH\\OneDrive - UFM\\ThucTapTotNghiep\\QuanLyNhanSu\\src\\Report\\LyLichNV.jrxml";
            JasperReport jr= JasperCompileManager.compileReport(link);
            HashMap param=new HashMap();
            param.put("MaNv", txtMaNV.getText());
            JasperPrint jp=JasperFillManager.fillReport(jr,param, conn);
            JasperViewer.viewReport(jp,false);
        } catch (Exception ex) {
            MessageDialogHelper.showError(this, ex.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnInActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        personalImage=null;
        txtTenNV.setText("");
        txtCMND.setText("");
        txtSDT.setText("");
        txtHocVan.setText("");
        txtTinHoc.setText("");
        txtAnhVan.setText("");
        txtGhiChu.setText("");
        txtDiaChi.setText("");
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        StringBuilder sb=new StringBuilder();
        if(sb.length()>0){
            MessageDialogHelper.showError(home, sb.toString(), "Lỗi");
            return;
        }
        if(MessageDialogHelper.showConfirm(home, "Tiếp tục xóa?", "Thông báo")==JOptionPane.NO_OPTION){
            return;
        }
        try{
            LyLichDAO dao=new LyLichDAO();
            if(dao.delete(txtMaNV.getText())){
                MessageDialogHelper.showMessage(home, "Đã xóa", "Thông báo");
                LoadTable();
                btnNewActionPerformed(evt);
            }else {
                MessageDialogHelper.showError(home, "Không thể lưu", "Lỗi");
            }
        }catch(Exception ex){
            MessageDialogHelper.showError(home, ex.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnBrowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowsActionPerformed
        JFileChooser choose= new JFileChooser();
        choose.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f.isDirectory()){
                    return true;
                }else{
                    return f.getName().toLowerCase().endsWith(".jpg");
                }
            }

            @Override
            public String getDescription() {
                return "Image File(*.jpg)";
            }
        });
        if (choose.showOpenDialog(home)== JFileChooser.CANCEL_OPTION){
            return;
        }
        File file= choose.getSelectedFile();
        try{
            ImageIcon icon=new ImageIcon(file.getPath());
            Image img=ImageHelper.resize(icon.getImage(), 140, 140);
            ImageIcon resizeIcon =new ImageIcon(img);
            lblImage.setIcon(resizeIcon);
            personalImage =ImageHelper.toByteArray(img, "jpg");
        } catch (IOException ex){
            MessageDialogHelper.showMessage(home, ex.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnBrowsActionPerformed

    private void rdbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbNamActionPerformed

    private void tblLyLich1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLyLich1MouseClicked
        int row = tblLyLich1.getSelectedRow();
        if (row >= 0) {
            String MaNV = (String) tblLyLich1.getValueAt(row, 0);
            NhanVien(MaNV);
        }
    }//GEN-LAST:event_tblLyLich1MouseClicked

    private void NhanVien(String MaNV) {
        try {
            LyLichDAO dao = new LyLichDAO();
            LyLich ll = dao.FindNhanVien(MaNV);
            txtMaNV.setText(ll.getMaNV());
            txtTenNV.setText(ll.getTenNV());
            txtCMND.setText(ll.getCMND());
            txtSDT.setText(ll.getSoDienThoai());
            dpNgaySinh.setDate(fm.parse(ll.getNgaySinh()));
            txtHocVan.setText(ll.getHocVan());
            txtTinHoc.setText(ll.getTinHoc());
            txtAnhVan.setText(ll.getAnhVan());
            txtPhuThuoc.setValue(ll.getPhuThuoc());
            txtQuocTich.setText(ll.getQuocTich());
            txtGhiChu.setText(ll.getGhiChu());
            txtDiaChi.setText(ll.getDiaChi());
            if (ll.getGioiTinh() == 1) {
                rdbNam.setSelected(true);
            } else {
                rdbNu.setSelected(true);
            }
            if (ll.getTinhTrangHN() == 1) {
                rdbKetHon.setSelected(true);
            } else {
                rdbDocThan.setSelected(true);
            }
            if (ll.getHinhAnh() != null) {
                Image img = ImageHelper.createImageFromBytoArray(ll.getHinhAnh(), "jpg");
                lblImage.setIcon(new ImageIcon(img));
                personalImage = ll.getHinhAnh();
            } else {
                personalImage = null;
                ImageIcon icon = null;
                switch (ll.getGioiTinh()) {
                    case 0:
                        icon = new ImageIcon(getClass().getResource("/Image/icons8_female_profile_80px.png"));
                        break;
                    case 1:
                        icon = new ImageIcon(getClass().getResource("/Image/icons8_male_user_80px.png"));
                        break;
                }
                lblImage.setIcon(icon);
            }
        } catch (Exception ex) {
            MessageDialogHelper.showError(home, ex.getMessage(), "Lỗi");
        }
    }

    private LyLich SetLyLich() {
        LyLich nv=new LyLich();
        nv.setMaNV(txtMaNV.getText());
        nv.setCMND(txtCMND.getText());
        nv.setDiaChi(txtDiaChi.getText());
        nv.setTenNV(txtTenNV.getText());
        nv.setNgaySinh(fm.format(dpNgaySinh.getDate()));
        nv.setTinhTrangHN(rdbKetHon.isSelected()?1:0);
        nv.setGioiTinh(rdbNam.isSelected()?1:0);
        nv.setGhiChu(txtGhiChu.getText());
        nv.setHinhAnh(personalImage);
        nv.setSoDienThoai(txtSDT.getText());
        nv.setHocVan(txtHocVan.getText());
        nv.setAnhVan(txtAnhVan.getText());
        nv.setTinHoc(txtTinHoc.getText());
        nv.setQuocTich(txtQuocTich.getText());
        nv.setPhuThuoc(Integer.parseInt(txtPhuThuoc.getValue().toString()));
        return nv;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrows;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dpNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblImage;
    private javax.swing.JRadioButton rdbDocThan;
    private javax.swing.JRadioButton rdbKetHon;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JTable tblLyLich;
    private javax.swing.JTable tblLyLich1;
    private javax.swing.JTextField txtAnhVan;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtHocVan;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JSpinner txtPhuThuoc;
    private javax.swing.JTextField txtQuocTich;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTinHoc;
    // End of variables declaration//GEN-END:variables
}
