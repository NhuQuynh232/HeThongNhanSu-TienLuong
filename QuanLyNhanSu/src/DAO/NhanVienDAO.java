/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.DatabaseHelper;
import Model.LyLich;
import Model.NhanVien;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class NhanVienDAO {
    
    public boolean update(LyLich nv) throws Exception {
        String sql = "UPDATE NhanVien SET MaCV=?, MaBP=?, LuongCoBan=?"
                + " WHERE MaNV =?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(4, nv.getMaNV());

            pstmt.setString(1, nv.getMaCV());
            pstmt.setString(2, nv.getMaBP());
            pstmt.setDouble(3, nv.getLuongCB());

            return pstmt.executeUpdate() > 0;
        }
    }

    public List<NhanVien> FindAllNhanVien() throws Exception{
        String  sql= "SELECT * FROM NhanVien_v ";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<NhanVien> list=new ArrayList<>();
                while(rs.next()){
                    NhanVien nv=new NhanVien();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setTenCV(rs.getString("TenCV"));
                    nv.setTenPB(rs.getString("TenBP"));
                    nv.setLuongCoBan(rs.getDouble("LuongCoBan"));
                    nv.setMaBP(rs.getString("MaBP"));
                    nv.setMaCV(rs.getString("MaCV"));
                    list.add(nv);
                }
                return list;
            }
        } 
    }
    
    public List<NhanVien> FindNhanVienlist(String MaNV) throws Exception{
        String  sql= "Select * from NhanVien_v Where MaNV like N'%"+MaNV+"%'";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<NhanVien> list=new ArrayList<>();
                while(rs.next()){
                    NhanVien nv=new NhanVien();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setTenCV(rs.getString("TenCV"));
                    nv.setTenPB(rs.getString("TenBP"));
                    nv.setLuongCoBan(rs.getDouble("LuongCoBan"));
                    nv.setMaBP(rs.getString("MaBP"));
                    nv.setMaCV(rs.getString("MaCV"));
                    list.add(nv);
                }
                return list;
            }
        }
    }
    
    public List<NhanVien> FindBPNhanVienlist(String TenBP) throws Exception{
        String  sql= "Select * From NhanVien_v Where TenBP like N'%"+TenBP+"%'";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<NhanVien> list=new ArrayList<>();
                while(rs.next()){
                    NhanVien nv=new NhanVien();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setTenCV(rs.getString("TenCV"));
                    nv.setTenPB(rs.getString("TenBP"));
                    nv.setLuongCoBan(rs.getDouble("LuongCoBan"));
                    nv.setMaBP(rs.getString("MaBP"));
                    nv.setMaCV(rs.getString("MaCV"));
                    list.add(nv);
                }
                return list;
            }
        }
    }
    
    public List<NhanVien> FindCVNhanVienlist(String TenCV) throws Exception{
        String  sql= "SELECT * FROM NhanVien_v Where TenCV like N'%"+TenCV+"%'";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<NhanVien> list=new ArrayList<>();
                while(rs.next()){
                    NhanVien nv=new NhanVien();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setTenCV(rs.getString("TenCV"));
                    nv.setTenPB(rs.getString("TenBP"));
                    nv.setLuongCoBan(rs.getDouble("LuongCoBan"));
                    nv.setMaBP(rs.getString("MaBP"));
                    nv.setMaCV(rs.getString("MaCV"));
                    list.add(nv);
                }
                return list;
            }
        }
    }
    
    
    
    public List<NhanVien> FindNameNhanVien(String TenNV) throws Exception{
        String  sql= "SELECT * FROM NhanVien_v Where TenNV like N'%"+TenNV+"%'";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<NhanVien> list=new ArrayList<>();
                while(rs.next()){
                    NhanVien nv=new NhanVien();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setTenCV(rs.getString("TenCV"));
                    nv.setTenPB(rs.getString("TenBP"));
                    nv.setLuongCoBan(rs.getDouble("LuongCoBan"));
                    nv.setMaBP(rs.getString("MaBP"));
                    nv.setMaCV(rs.getString("MaCV"));
                    list.add(nv);
                }
                return list;
            }
        }
    }
    
    public NhanVien FindNhanVien(String MaNV) throws Exception {
        String sql = "Select * From NhanVien_v Where MaNV=?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, MaNV);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setTenCV(rs.getString("TenCV"));
                    nv.setTenPB(rs.getString("TenBP"));
                    nv.setLuongCoBan(rs.getDouble("LuongCoBan"));
                    Blob blob = rs.getBlob("HinhAnh");
                    if (blob != null) {
                        nv.setHinhAnh(blob.getBytes(1, (int) blob.length()));
                    }
                    return nv;
                }
            }
            return null;
        }
    }
    
    public List<NhanVien> FindBPNhanVien(String TenBP) throws Exception{
        String  sql= "SELECT * FROM ChucVu cv, NhanVien nv full join LyLich ll on nv.MaNV=ll.MaNV, BoPhan bp Where cv.MaCV=nv.MaCV and nv.MaBP=bp.MaBP and bp.TenBP=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,TenBP);
            try(ResultSet rs=pstmt.executeQuery()){
                List<NhanVien> list=new ArrayList<>();
                if(rs.next()){
                    NhanVien bd=createNhanVien(rs);
                    list.add(bd);
                }
                return list;
            }
        }
    }
    
    public List<NhanVien> FindNhanVien_v() throws Exception{
        String  sql= "SELECT * FROM NhanVien_v";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<NhanVien> list=new ArrayList<>();
                while(rs.next()){
                    NhanVien nv=new NhanVien();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setTenCV(rs.getString("TenCV"));
                    nv.setTenPB(rs.getString("TenBP"));
                    list.add(nv);
                }
                return list;
            }
        }
    }
    
    public List<NhanVien> FindNhanVienBP_v(String Ten) throws Exception{
        String  sql= "SELECT * FROM NhanVien_v WHERE TenBoPhan=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                pstmt.setString(1,Ten);
                List<NhanVien> list=new ArrayList<>();
                while(rs.next()){
                    NhanVien nv=new NhanVien();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setTenCV(rs.getString("TenCV"));
                    nv.setTenPB(rs.getString("TenBP"));
                    list.add(nv);
                }
                return list;
            }
        }
    }
    
    
    
    public List<NhanVien> FindNhanVienCV_v(String Ten) throws Exception{
        String  sql= "SELECT * FROM NhanVien_v WHERE TenChucVu=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                pstmt.setString(1,Ten);
                List<NhanVien> list=new ArrayList<>();
                while(rs.next()){
                    NhanVien nv=new NhanVien();
                    nv.setMaNV(rs.getString("MaNV"));
                    nv.setTenNV(rs.getString("TenNV"));
                    nv.setTenCV(rs.getString("TenCV"));
                    nv.setTenPB(rs.getString("TenBP"));
                    list.add(nv);
                }
                return list;
            }
        }
    }
    
    private NhanVien createNhanVien(final ResultSet rs) throws SQLException {
        NhanVien nv=new NhanVien();
        nv.setMaNV(rs.getString("MaNV"));
        nv.setTenNV(rs.getString("TenNV"));
        nv.setTenCV(rs.getString("TenCV"));
        nv.setTenPB(rs.getString("TenBP"));
        nv.setGioiTinh(rs.getInt("GioiTinh"));
        nv.setLuongCoBan(rs.getDouble("LuongCoBan"));
        nv.setMaBP(rs.getString("MaBP"));
        nv.setMaCV(rs.getString("MaCV"));
        Blob blob =rs.getBlob("HinhAnh");
        if(blob !=null){
            nv.setHinhAnh(blob.getBytes(1,(int)blob.length()));
        }
        return nv;
    }
}
