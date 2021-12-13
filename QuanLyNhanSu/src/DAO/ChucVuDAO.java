/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.DatabaseHelper;
import java.sql.Connection;
import Model.ChucVu;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class ChucVuDAO {

    public List<ChucVu> FindAllChucVu() throws Exception {
        String sql = "SELECT * FROM ChucVu";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            try (ResultSet rs = pstmt.executeQuery()) {
                List<ChucVu> list = new ArrayList<>();
                while (rs.next()) {
                    ChucVu bp = createChucVu(rs);
                    list.add(bp);
                }
                return list;
            }
        }
    }

    public ChucVu FindCVByName(String TenCV) throws Exception {
        String sql = "SELECT * FROM ChucVu WHERE TenCV=?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, TenCV);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ChucVu s = createChucVu(rs);
                    return s;
                }
            }
            return null;
        }
    }
    public ChucVu FindCVByID(String MaCV) throws Exception {
        String sql = "SELECT * FROM ChucVu WHERE MaCV=?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, MaCV);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ChucVu s = createChucVu(rs);
                    return s;
                }
            }
            return null;
        }
    }

    public boolean insert(ChucVu kt) throws Exception {
        String sql = "INSERT INTO ChucVu (MaCV , TenCV, GhiChu)" + " VALUES(?,?,?)";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, kt.getMaCV());
            pstmt.setString(2, kt.getTenCV());
            pstmt.setString(3, kt.getGhiChu());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean update(ChucVu kt) throws Exception {
        String sql = "UPDATE ChucVu SET  TenCV=?, GhiChu=?" + " WHERE MaCV=? ";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(3, kt.getMaCV());
            pstmt.setString(1, kt.getTenCV());
            pstmt.setString(2, kt.getGhiChu());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean delete(String MaCV) throws Exception {
        String sql = "EXEC sp_ChucVu_del ?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement cs = conn.prepareStatement(sql);) {
            cs.setString(1, MaCV);
            return cs.execute();
        }
    }

    private ChucVu createChucVu(final ResultSet rs) throws SQLException {
        ChucVu cv = new ChucVu();
        cv.setMaCV(rs.getString("MaCV"));
        cv.setTenCV(rs.getString("TenCV"));
        cv.setGhiChu(rs.getString("GhiChu"));
        return cv;
    }
}
