/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helpers.DatabaseHelper;
import Model.HopDongLaoDong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUYNH
 */
public class HopDongDAO {

    public boolean insert(HopDongLaoDong nv) throws Exception {
        String sql = "INSERT INTO HopDongLaoDong (MaNV, NgayHieuLuc, NgayKetThuc, MaCV, MaBP, LuongCoBan, Loai, PhuCap,  NgayKy)" + "VALUES(?,?,?,?,?,?,?,?,?)";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, nv.getMaNV());
            pstmt.setString(2, nv.getNgayBatDau());
            pstmt.setString(3, nv.getNgayKetThuc());
            pstmt.setString(4, nv.getMaCV());
            pstmt.setString(5, nv.getMaBP());
            pstmt.setDouble(6, nv.getLuongCoBan());
            pstmt.setString(7, nv.getLoai());
            pstmt.setDouble(8, nv.getPhuCap());
            pstmt.setString(9,nv.getNgayKy());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean update(HopDongLaoDong nv) throws Exception {
        String sql = "UPDATE HopDongLaoDong SET  MaCV=?, MaBP=?, LuongCoBan=?, Loai=?, PhuCap=?, NgayKetThuc=?, NgayKy=? WHERE MaNV=? and NgayHieuLuc=?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(6, nv.getNgayKetThuc());
            pstmt.setString(7, nv.getNgayKy());
            pstmt.setString(8, nv.getMaNV());
            pstmt.setString(9, nv.getNgayBatDau());
            pstmt.setString(1, nv.getMaCV());
            pstmt.setString(2, nv.getMaBP());
            pstmt.setDouble(3, nv.getLuongCoBan());
            pstmt.setString(4, nv.getLoai());
            pstmt.setDouble(5, nv.getPhuCap());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean delete(String MaNV, String NgayBatDau) throws Exception {
        String sql = "DELETE FROM HopDongLaoDong" + " WHERE MaNV =? and NgayHieuLuc=? ";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, MaNV);
            pstmt.setString(2, NgayBatDau);
            return pstmt.executeUpdate() > 0;
        }
    }

    
    public HopDongLaoDong FindNV(String MaNV, String NgayBatDau, String NgayKetThuc) throws Exception {
        String sql = "Select * FROM HopDong_v" + " WHERE MaNV =? and NgayHieuLuc=? ";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, MaNV);
            pstmt.setString(2, NgayBatDau);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    HopDongLaoDong bd = createHopDong(rs);
                    return bd;
                }
            }
            return null;
        }
    }
    
    public HopDongLaoDong FindNV(String MaNV) throws Exception {
        String sql = "Select * FROM HopDong_v" + " WHERE MaNV =?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, MaNV);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    HopDongLaoDong bd = createHopDong(rs);
                    return bd;
                }
            }
            return null;
        }
    }

    public List<HopDongLaoDong> FindAll() throws Exception {
        String sql = "Select * From HopDong_v Order By MaNV";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            try (ResultSet rs = pstmt.executeQuery()) {
                List<HopDongLaoDong> list = new ArrayList<>();
                while (rs.next()) {
                    HopDongLaoDong nv = createHopDong(rs);
                    list.add(nv);
                }
                return list;
            }
        }
    }
    public List<HopDongLaoDong> FindAllByDep(String MaBP) throws Exception {
        String sql = "Select * From HopDong_v Where MaBP=?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, MaBP);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<HopDongLaoDong> list = new ArrayList<>();
                while (rs.next()) {
                    HopDongLaoDong nv = createHopDong(rs);
                    list.add(nv);
                }
                return list;
            }
        }
    }

    private HopDongLaoDong createHopDong(final ResultSet rs) throws SQLException {
        HopDongLaoDong hd = new HopDongLaoDong();
        hd.setMaNV(rs.getString("MaNV"));
        hd.setNgayBatDau(rs.getString("NgayHieuLuc"));
        hd.setNgayKetThuc(rs.getString("NgayKetThuc"));
        hd.setMaCV(rs.getString("MaCV"));
        hd.setMaBP(rs.getString("MaBP"));
        hd.setLoai(rs.getString("Loai"));
        hd.setLuongCoBan(rs.getDouble("LuongCoBan"));
        hd.setTenBP(rs.getString("TenBP"));
        hd.setTenCV(rs.getString("TenCV"));
        hd.setTenNV(rs.getString("TenNV"));
        hd.setPhuCap(rs.getDouble("PhuCap"));
        hd.setBaoHiem(rs.getInt("BaoHiem"));
        hd.setNgayKy(rs.getString("NgayKy"));
        return hd;
    }
}
