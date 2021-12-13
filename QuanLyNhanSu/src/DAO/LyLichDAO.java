/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.DatabaseHelper;
import Model.LyLich;
import javax.sql.rowset.serial.SerialBlob;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Blob;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class LyLichDAO {
    
    public boolean insert(LyLich nv) throws Exception{
        String sql="INSERT INTO NhanVien (MaNV,TenNV, CMND, TinhTrangHN, GioiTinh, SoDienThoai, DiaChi, HocVan, AnhVan, TinHoc, GhiChu, NgaySinh, HinhAnh,PhuThuoc,QuocTich)"+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,nv.getMaNV());
            pstmt.setString(2,nv.getTenNV());
            pstmt.setString(3,nv.getCMND());
            pstmt.setInt(4,nv.getTinhTrangHN());
            pstmt.setInt(5,nv.getGioiTinh());
            pstmt.setString(7,nv.getDiaChi());
            pstmt.setString(8,nv.getHocVan());
            pstmt.setString(9,nv.getAnhVan());
            pstmt.setString(10,nv.getTinHoc());
            pstmt.setString(11,nv.getGhiChu());
            pstmt.setString(12,nv.getNgaySinh());
            pstmt.setString(6,nv.getSoDienThoai());
            if (nv.getHinhAnh()!= null){
                Blob hinh=new SerialBlob(nv.getHinhAnh());
                pstmt.setBlob(13, hinh);
            }else{
                Blob hinh=null;
                pstmt.setBlob(13, hinh);
            }
            pstmt.setInt(14,nv.getPhuThuoc());
            pstmt.setString(15,nv.getQuocTich());
            return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean delete(String MaNV) throws Exception{
        String sql="DELETE FROM NhanVien"+" WHERE MaNV =?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean update(LyLich nv) throws Exception{
        String sql="UPDATE NhanVien SET CMND=?, TinhTrangHN=?, GioiTinh=?, DiaChi=?, GhiChu=?, NgaySinh=?, SoDienThoai=?,HinhAnh=?,  TenNV=?, PhuThuoc=?, HocVan=?, AnhVan=?, TinHoc=?, QuocTich=?"
                +" WHERE MaNV =?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(15,nv.getMaNV());
            
            pstmt.setString(1,nv.getCMND());
            pstmt.setInt(2,nv.getTinhTrangHN());
            pstmt.setInt(3,nv.getGioiTinh());
            pstmt.setString(4,nv.getDiaChi());
            pstmt.setString(5,nv.getGhiChu());
            pstmt.setString(6,nv.getNgaySinh());
            pstmt.setString(7,nv.getSoDienThoai());
            if (nv.getHinhAnh()!= null){
                Blob hinh=new SerialBlob(nv.getHinhAnh());
                pstmt.setBlob(8, hinh);
            }else{
                Blob hinh=null;
                pstmt.setBlob(8, hinh);
            }
            pstmt.setString(9,nv.getTenNV());
            pstmt.setInt(10,nv.getPhuThuoc());
            pstmt.setString(11,nv.getHocVan());
            pstmt.setString(12,nv.getAnhVan());
            pstmt.setString(13,nv.getTinHoc());
            pstmt.setString(14,nv.getQuocTich());
            return pstmt.executeUpdate()>0;
        }
    }
    
    public List<LyLich> FindAll() throws Exception{
        String  sql= "SELECT * FROM NhanVien";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<LyLich> list=new ArrayList<>();
                while(rs.next()){
                    LyLich bd=createLyLich(rs);
                    list.add(bd);
                }
                return list;
            }
        }
    }
    
    public LyLich FindNhanVien(String MaNV) throws Exception{
        String  sql= "SELECT * FROM NhanVien WHERE MaNV=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    LyLich bd=createLyLich(rs);
                    return bd;
                }
            }
            return null;
        }
    }
    
    public LyLich FindNhanVienByName(String TenNV) throws Exception{
        String  sql= "SELECT * FROM NhanVien WHERE TenNV=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,TenNV);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    LyLich bd=createLyLich(rs);
                    return bd;
                }
            }
            return null;
        }
    }
    
    
    private LyLich createLyLich(final ResultSet rs) throws SQLException {
        LyLich ll=new LyLich();
        ll.setMaNV(rs.getString("MaNV"));
        ll.setTenNV(rs.getString("TenNV"));
        ll.setCMND(rs.getString("CMND"));
        ll.setSoDienThoai(rs.getString("SoDienThoai"));
        ll.setDiaChi(rs.getString("DiaChi"));
        ll.setNgaySinh(rs.getString("NgaySinh"));
        ll.setHocVan(rs.getString("HocVan"));
        ll.setAnhVan(rs.getString("AnhVan"));
        ll.setTinHoc(rs.getString("TinHoc"));
        ll.setGioiTinh(rs.getInt("GioiTinh"));
        ll.setTinhTrangHN(rs.getInt("TinhTrangHN"));
        ll.setGhiChu(rs.getString("GhiChu"));
        ll.setQuocTich(rs.getString("QuocTich"));
        ll.setPhuThuoc(rs.getInt("PhuThuoc"));
        Blob blob =rs.getBlob("HinhAnh");
        if(blob !=null){
            ll.setHinhAnh(blob.getBytes(1,(int)blob.length()));
        }
        return ll;
    }
    
    
}
