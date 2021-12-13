/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.DatabaseHelper;
import Model.Luong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class LuongDAO {

    public boolean insert(Luong l) throws Exception {
        String sql = "INSERT INTO Luong (MaNV, LuongThang, SoNgayCong, LuongThuc, BHYT, BHXH, BHTN, TTNCN, Luong, MienTruGiaCanh,LuongNam) " + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, l.getMaNV());
            pstmt.setInt(2, l.getLuongThang());
            pstmt.setInt(3, l.getSoNgayCong());
            pstmt.setDouble(4, l.getLuongThuc());
            pstmt.setDouble(5, l.getBHYT());
            pstmt.setDouble(6, l.getBHXH());
            pstmt.setDouble(7, l.getBHTN());
            pstmt.setDouble(8, l.getThue());
            pstmt.setDouble(9, l.getLuong());
            pstmt.setDouble(10,l.getMienTruGiaCanh());
            pstmt.setInt(11,l.getLuongNam());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean update(Luong l) throws Exception {
        String sql = "UPDATE Luong SET SoNgayCong=?, LuongThuc=?, BHYT=?, BHXH=?, BHTN=?, Luong=?, TTNCN=?, MienTruGiaCanh=? " + " WHERE MaNV=? and LuongThang=? and LuongNam=?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(9, l.getMaNV());
            pstmt.setInt(10, l.getLuongThang());
            pstmt.setInt(1, l.getSoNgayCong());
            pstmt.setDouble(2, l.getLuongThuc());
            pstmt.setDouble(3, l.getBHYT());
            pstmt.setDouble(4, l.getBHXH());
            pstmt.setDouble(5, l.getBHTN());
            pstmt.setDouble(7, l.getThue());
            pstmt.setDouble(6, l.getLuong());
            pstmt.setDouble(8,l.getMienTruGiaCanh());
            pstmt.setInt(11,l.getLuongNam());
            return pstmt.executeUpdate() > 0;
        }
    }

    public Luong FindLuong(String MaNV, int month, int year) throws Exception {
        String sql = "SELECT * FROM Luong Where MaNV=? and LuongThang=? and LuongNam=? ";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, MaNV);
            pstmt.setInt(2, month);
            pstmt.setInt(3, year);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Luong tt = createLuong(rs);
                    return tt;
                }
                return null;
            }
        }
    }

    private Luong createLuong(final ResultSet rs) throws SQLException {
        Luong l = new Luong();
        l.setMaNV(rs.getString("MaNV"));
        l.setLuongThang(rs.getInt("LuongThang"));
        l.setLuongNam(rs.getInt("LuongNam"));
        l.setSoNgayCong(rs.getInt("SoNgayCong"));
        l.setBHYT(rs.getDouble("BHYT"));
        l.setBHXH(rs.getDouble("BHXH"));
        l.setBHTN(rs.getDouble("BHTN"));
        l.setLuong(rs.getDouble("Luong"));
        l.setThue(rs.getDouble("TTNCN"));
        l.setMienTruGiaCanh(rs.getDouble("MienTruGiaCanh"));
        l.setLuongThuc(rs.getDouble("LuongThuc"));
        return l;
    }
}
