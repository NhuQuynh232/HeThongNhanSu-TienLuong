/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helpers.DatabaseHelper;
import Model.LyLich;
import Model.ThanNhan;
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
public class ThanNhanDAO {

    public boolean insert(ThanNhan nv) throws Exception {
        String sql = "INSERT INTO ThanNhan ( MaNV, TenTN, GioiTinh, SoDienThoai, GhiChu, NgaySinh)" + "VALUES(?,?,?,?,?,?)";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, nv.getMaNV());
            pstmt.setString(2, nv.getTenTN());
            pstmt.setInt(3, nv.getGioiTinh());
            pstmt.setString(4, nv.getSoDienThoai());
            pstmt.setString(5, nv.getGhiChu());
            pstmt.setString(6, nv.getNgaySinh());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean delete(String TenTN) throws Exception {
        String sql = "DELETE FROM ThanNhan" + " WHERE TenTN =?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, TenTN);
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean update(ThanNhan nv) throws Exception {
        String sql = "UPDATE ThanNhan SET GioiTinh=?, MaNV=?, GhiChu=?, NgaySinh=?, SoDienThoai=?, TenTN=?" + " WHERE MaTN =?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(7, nv.getMaTN());
            pstmt.setString(2, nv.getMaNV());
            pstmt.setString(6, nv.getTenTN());
            pstmt.setInt(1, nv.getGioiTinh());
            pstmt.setString(5, nv.getSoDienThoai());
            pstmt.setString(3, nv.getGhiChu());
            pstmt.setString(4, nv.getNgaySinh());
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public List<ThanNhan> FindAll() throws Exception{
        String  sql= "SELECT * FROM ThanNhan_v";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<ThanNhan> list=new ArrayList<>();
                while(rs.next()){
                    ThanNhan bd=createThanNhan(rs);
                    list.add(bd);
                }
                return list;
            }
        }
    }
    
    public List<ThanNhan> FindThanNhan(String MaNV) throws Exception{
        String  sql= "SELECT * FROM ThanNhan_v WHERE MaNV=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            try(ResultSet rs=pstmt.executeQuery()){
                List<ThanNhan> list=new ArrayList<>();
                while(rs.next()){
                    ThanNhan bd=createThanNhan(rs);
                    list.add(bd);
                }
                return list;
            }
        }
    }
    
    
    private ThanNhan createThanNhan(final ResultSet rs) throws SQLException {
        ThanNhan tn=new ThanNhan();
        tn.setMaNV(rs.getString("MaNV"));
        tn.setMaTN(rs.getString("MaTN"));
        tn.setTenNV(rs.getString("TenNV"));
        tn.setTenTN(rs.getString("TenTN"));
        tn.setSoDienThoai(rs.getString("SoDienThoai"));
        tn.setNgaySinh(rs.getString("NgaySinh"));
        tn.setGioiTinh(rs.getInt("GioiTinh"));
        tn.setGhiChu(rs.getString("GhiChu"));
        return tn;
    }
}
