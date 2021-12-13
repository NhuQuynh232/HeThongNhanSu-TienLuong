/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.BoPhanDAO;
import DAO.HopDongDAO;
import DAO.LuongDAO;
import DAO.LyLichDAO;
import DAO.NhanVienDAO;
import DAO.ThongTinLuongDAO;
import Helpers.DatabaseHelper;
import Helpers.Format;
import Helpers.MessageDialogHelper;
import Helpers.ShareData;
import Helpers.TableDesign;
import Model.BoPhan;
import Model.HopDongLaoDong;
import Model.Luong;
import Model.LyLich;
import Model.NhanVien;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class FrThongTinLuong extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrThongTinLuong
     */
    public FrThongTinLuong() {
        initComponents();
        initTable();
        LoadTableNhanVien();
        LoadComboBox();
        DesignTable();
        processLoginSuccessful();
    }

    SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat reportfm=new SimpleDateFormat("MM/dd/yy");

    private DefaultTableModel tblmodel;
    private Home home;

    private void processLoginSuccessful(){
        if (ShareData.login.getDep().equals("All") || ShareData.login.getRole().equals("manager")) {
            btnPhieuLuong.setEnabled(true);
            btnReport.setEnabled(true);
        } else {
            btnPhieuLuong.setEnabled(false);
            btnReport.setEnabled(false);
            btnPhieuLuong.setToolTipText("Tài khoản không được cấp quyền");
            btnReport.setToolTipText("Tài khoản không được cấp quyền");
        }
    }

    private void initTable() {
        tblmodel = new DefaultTableModel();
        // tên cột
        String[] header= {"Tên nhân viên"
                            , "Phụ cấp"
                            ,"Lương cơ bản"
                            , "Số ngày công"
                            , "Tổng thu nhập"
                            , "<html><center>Bảo hiểm xã hội<br>8%</br></center></html>"
                            , "<html><center>Bảo hiểm y tế<br>1.5%</br></center></html>"
                            , "<html><center>Bảo hiểm thất nghiệp<br>1%</br></center></html>"
                            , "<html><center>Tổng các khoản<br> trích theo lương</br><br>10.5%</br></center></html>"
                            , "<html><center>Mức giảm trừ<br>gia cảnh</br></center></html>"
                            , "Thu nhập chịu thế"
                            , "Thuế TNCN"
                            , "Thực lĩnh"};
        tblmodel.setColumnIdentifiers(header);
        TableDesign.Design(tblLuong);
        
    }
    
    private void DesignTable(){
        //định dạng số sang bên phải
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tblLuong.setModel(tblmodel);
        for (int i = 1; i < tblLuong.getColumnCount(); i++) {
            tblLuong.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
        //chiều cao header
        tblLuong.getTableHeader().setPreferredSize(new Dimension(tblLuong.getColumnModel().getTotalColumnWidth(), 70));
        //kích thước cột
        tblLuong.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblLuong.getColumnModel().getColumn(1).setPreferredWidth(10);
        tblLuong.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblLuong.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblLuong.getColumnModel().getColumn(4).setPreferredWidth(60);
        tblLuong.getColumnModel().getColumn(7).setPreferredWidth(100);
        tblLuong.getColumnModel().getColumn(9).setPreferredWidth(40);
        tblLuong.getColumnModel().getColumn(11).setPreferredWidth(20);
        tblLuong.getColumnModel().getColumn(12).setPreferredWidth(20);
    }

    private void LoadTableNhanVien() {
        try {
            HopDongDAO daonv = new HopDongDAO();
            List<HopDongLaoDong> list;
            BoPhanDAO bpdao=new BoPhanDAO();
            BoPhan bp=bpdao.FindBPByName(cbbBP.getSelectedItem().toString());
            tblmodel.setRowCount(0);
            if (cbbBP.getSelectedItem() == "Tất cả") {
                list = daonv.FindAll();
            } else {
                list = daonv.FindAllByDep(bp.getMaPB());
            }
            list.forEach(it -> {
                String MaNV = it.getMaNV();
                TinhLuong(MaNV);
            });
        } catch (Exception ex) {
//            MessageDialogHelper.showError(home, ex.toString(), "Lỗi");
        }
    }

    private void TinhLuong(String MaNV) {
        try {
            ThongTinLuongDAO daott = new ThongTinLuongDAO();
            //khai báo
            double phucap = 0;
            double baohiemyte = 0;
            double baohiemxahoi = 0;
            double baohiemthatnghiep = 0;
            double luongcb = 0;
            double mientru = 0;
            double baohiem=0;
            int count;
            //Lấy tháng năm
            int month = cbbThang.getMonth() + 1;
            int year = cbbNam.getYear();
            //Tìm số ngày công
            int dilam = daott.FindNgayDiLam(MaNV, month, year);
            int congtac = daott.FindNgayCongTac(MaNV, month, year);
            int coluong = daott.FindNgayNghiCoLuong(MaNV, month, year);
            int ngaycong = dilam + coluong + congtac;
            //lấy phụ cấp, hệ số lương, số người phụ thuộc, tạm ứng nhân viên
            HopDongDAO hdao=new HopDongDAO();
            NhanVienDAO dao = new NhanVienDAO();
            LyLichDAO lldao = new LyLichDAO();
            HopDongLaoDong hd=hdao.FindNV(MaNV);
            NhanVien nv = dao.FindNhanVien(MaNV);
            LyLich ll = lldao.FindNhanVien(MaNV);
            count = ll.getPhuThuoc();// số người phụ thuộc
            mientru += count * 4400000;
            phucap = hd.getPhuCap();
            luongcb=nv.getLuongCoBan();
            //tính lương
            double luong = (((luongcb + phucap) / 26) * (ngaycong));
            //tính bảo hiểm: nếu như chấp nhận đsong bảo hiểm thì sẽ tính => không thì bảo hiểm bằng không
            if(hd.getBaoHiem()==1){
                baohiemyte = (luong * 0.015);
                baohiemxahoi = (luong * 0.08);
                baohiemthatnghiep = (luong * 0.01);
                baohiem = baohiemxahoi + baohiemthatnghiep + baohiemyte;
            }
            double chiuthue=luong-mientru;
            if(chiuthue<0){
                chiuthue=0;
            }
            double thue = Tax(chiuthue);
            tblmodel.addRow(new Object[]{nv.getTenNV(), 
                                    Format.FormatNumber(phucap), 
                                    Format.FormatNumber(luongcb),
                                    ngaycong, 
                                    Format.FormatNumber(luong), 
                                    Format.FormatNumber(baohiemxahoi), 
                                    Format.FormatNumber(baohiemyte), 
                                    Format.FormatNumber(baohiemthatnghiep), 
                                    Format.FormatNumber(baohiem), 
                                    Format.FormatNumber(mientru), 
                                    Format.FormatNumber(chiuthue), 
                                    Format.FormatNumber(thue), 
                                    Format.FormatNumber(luong - baohiem - thue)});

        } catch (Exception ex) {
            MessageDialogHelper.showError(home, ex.toString(), "Lỗi");
        }
    }

    private void LoadComboBox() {
        try {
            cbbBP.removeAllItems();
            BoPhanDAO dao3 = new BoPhanDAO();
            List<BoPhan> list3 = dao3.FindAllBoPhan();
            cbbBP.addItem("Tất cả");
            list3.forEach(it -> {
                cbbBP.addItem(it.getTenPB());
            });
        } catch (Exception ex) {
            MessageDialogHelper.showError(home, ex.toString(), "Lỗi");
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLuong = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbbBP = new javax.swing.JComboBox<>();
        lblMaBP = new javax.swing.JLabel();
        cbbThang = new com.toedter.calendar.JMonthChooser();
        cbbNam = new com.toedter.calendar.JYearChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnTinhLuong = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnPhieuLuong = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("THÔNG TIN LƯƠNG");

        tblLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null", "null", "null"
            }
        ));
        tblLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLuongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLuong);
        if (tblLuong.getColumnModel().getColumnCount() > 0) {
            tblLuong.getColumnModel().getColumn(0).setResizable(false);
            tblLuong.getColumnModel().getColumn(1).setResizable(false);
            tblLuong.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Bộ phận");

        cbbBP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbBP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbBPItemStateChanged(evt);
            }
        });

        lblMaBP.setForeground(new java.awt.Color(204, 204, 204));

        cbbThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbThangMouseClicked(evt);
            }
        });
        cbbThang.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cbbThangCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cbbThangInputMethodTextChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Năm");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tháng");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Đơn vị tính: Việt Nam đồng");

        btnTinhLuong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTinhLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_plus_+_40px.png"))); // NOI18N
        btnTinhLuong.setText("Lưu");
        btnTinhLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhLuongActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_restart_40px.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnPhieuLuong.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnPhieuLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_calendar_40px.png"))); // NOI18N
        btnPhieuLuong.setText("Phiếu lương");
        btnPhieuLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuLuongActionPerformed(evt);
            }
        });

        btnReport.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_print_40px.png"))); // NOI18N
        btnReport.setText("Báo cáo");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTinhLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPhieuLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoi)
                    .addComponent(btnTinhLuong)
                    .addComponent(btnPhieuLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReport))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(cbbBP, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMaBP, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cbbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(9, 9, 9)
                        .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaBP, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbBPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbBPItemStateChanged
        LoadTableNhanVien();
    }//GEN-LAST:event_cbbBPItemStateChanged

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        tblmodel.setRowCount(0);//Xóa các dòng bản chấm công
        LoadTableNhanVien();//Truyền dữ liệu tất cả nhân viên vào bảng nhân viên
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnTinhLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhLuongActionPerformed
        try {
            int n = 0;//số dòng thêm được
            for (int i = 0; i < tblLuong.getRowCount(); i++) {
                LuongDAO dao = new LuongDAO();
                LyLichDAO daoll = new LyLichDAO();
                //Tìm mã nhân viên
                LyLich ll = daoll.FindNhanVienByName(tblLuong.getValueAt(i, 0).toString());
                Luong l = dao.FindLuong(ll.getMaNV(), (cbbThang.getMonth() + 1), cbbNam.getYear());
                Luong salary = new Luong();
                salary.setMaNV(ll.getMaNV());
                salary.setLuongThang(cbbThang.getMonth() + 1);
                salary.setLuongNam(cbbNam.getYear());
                salary.setLuongThuc(Format.RemoveFormat((tblLuong.getValueAt(i, 4).toString())));
                salary.setSoNgayCong((int) tblLuong.getValueAt(i, 3));
                salary.setBHXH(Format.RemoveFormat(tblLuong.getValueAt(i, 5).toString()));
                salary.setBHYT(Format.RemoveFormat(tblLuong.getValueAt(i, 6).toString()));
                salary.setBHTN(Format.RemoveFormat(tblLuong.getValueAt(i, 7).toString()));
                salary.setMienTruGiaCanh(Format.RemoveFormat(tblLuong.getValueAt(i, 9).toString()));
                salary.setThue(Format.RemoveFormat(tblLuong.getValueAt(i, 11).toString()));
                salary.setLuong(Format.RemoveFormat(tblLuong.getValueAt(i, 12).toString()));
                if (l != null) {
                    dao.update(salary);
                } else {
                    dao.insert(salary);
                }
                n++;
            }
            if (n > 0) {
                MessageDialogHelper.showMessage(home, "Cập nhật thành công " + n + " dòng", "Thông báo");
            } else {
                MessageDialogHelper.showMessage(home, "Không thể cập nhật", "Thông báo");
            }
        } catch (Exception ex) {
            MessageDialogHelper.showError(home, ex.toString(), "Lỗi");
        }
    }//GEN-LAST:event_btnTinhLuongActionPerformed
    

    // Tính thuế thu nhập cá nhân
    public double Tax(double salary) {
        double tax = 0;
        int n = 0; //sử dụng lấy số vòng lặp
        int[] total = {5, 10, 18, 32, 52, 80, 90};// tổng thu nhập
        double[] percent = {0.05, 0.1, 0.15, 0.2, 0.25, 0.30, 0.35};// thuế suất
        double[] money = {0, 0.25, 0.75, 1.95, 4.75, 9.75, 18.15};
        for (int i = 0; i < 7; i++) {
            if ((salary / 1000000) > total[i]) {
                n++;
                break;
            }
        }
        tax = money[n] + ((salary) * percent[n]);
        return tax;
    }
    private void tblLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLuongMouseClicked

    }//GEN-LAST:event_tblLuongMouseClicked

    private void cbbThangInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cbbThangInputMethodTextChanged
        
    }//GEN-LAST:event_cbbThangInputMethodTextChanged

    private void cbbThangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbThangMouseClicked

    }//GEN-LAST:event_cbbThangMouseClicked

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        try {
            java.sql.Connection conn= DatabaseHelper.openConnection();
            String link="C:\\Users\\QUYNH\\OneDrive - UFM\\ThucTapTotNghiep\\QuanLyNhanSu\\src\\Report\\LuongNhanVien.jrxml";
            JasperReport jr= JasperCompileManager.compileReport(link);
            HashMap param=new HashMap();
            param.put("LuongThang", cbbThang.getMonth()+1);
            param.put("LuongNam", cbbNam.getYear());
            JasperPrint jp=JasperFillManager.fillReport(jr,param, conn);
            JasperViewer.viewReport(jp,false);
        } catch (Exception ex) {
            MessageDialogHelper.showError(home, ex.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnReportActionPerformed

    private void btnPhieuLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuLuongActionPerformed
        try {
            java.sql.Connection conn= DatabaseHelper.openConnection();
            String link="C:\\Users\\QUYNH\\OneDrive - UFM\\ThucTapTotNghiep\\QuanLyNhanSu\\src\\Report\\Luong.jrxml";
            JasperReport jr= JasperCompileManager.compileReport(link);
            HashMap param=new HashMap();
            param.put("LuongThang", cbbThang.getMonth()+1);
            param.put("LuongNam", cbbNam.getYear());
            JasperPrint jp=JasperFillManager.fillReport(jr,param, conn);
            JasperViewer.viewReport(jp,false);
        } catch (Exception ex) {
            MessageDialogHelper.showError(home, ex.getMessage(), "Lỗi");
        }
    }//GEN-LAST:event_btnPhieuLuongActionPerformed

    private void cbbThangCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cbbThangCaretPositionChanged
        
    }//GEN-LAST:event_cbbThangCaretPositionChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnPhieuLuong;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnTinhLuong;
    private javax.swing.JComboBox<String> cbbBP;
    private com.toedter.calendar.JYearChooser cbbNam;
    private com.toedter.calendar.JMonthChooser cbbThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaBP;
    private javax.swing.JTable tblLuong;
    // End of variables declaration//GEN-END:variables
}
