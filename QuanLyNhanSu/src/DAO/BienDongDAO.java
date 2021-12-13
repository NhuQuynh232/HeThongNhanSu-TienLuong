/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.DatabaseHelper;
import Model.BienDongNhanSu;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class BienDongDAO {
    public List<BienDongNhanSu> FindAllBoTri() throws Exception{
        String  sql= "Select * From BienDong_v  Order By NgayBienDong";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<BienDongNhanSu> list=new ArrayList<>();
                while(rs.next()){
                    BienDongNhanSu bt=new BienDongNhanSu();
                    bt.setMaNV(rs.getString("MaNV"));
                    bt.setNgayHieuLuc(rs.getString("NgayBienDong"));
                    bt.setHeSoLuong_C(rs.getDouble("LuongCoBan_C"));
                    bt.setTenBP_C(rs.getString("TenBP_C"));
                    bt.setTenCV_C(rs.getString("TenCV_C"));
                    bt.setTenBP_M(rs.getString("TenBP_M"));
                    bt.setTenCV_M(rs.getString("TenCV_M"));
                    bt.setHeSoLuong_M(rs.getDouble("LuongCoBan_M"));
                    list.add(bt);
                }
                return list;
            }
        } 
    }
    
    public boolean insert(BienDongNhanSu bd) throws Exception{
        String sql="INSERT INTO BienDongNhanSu (MaNV , NgayBienDong, MaBP_C, MaCV_C, LuongCoBan_C, MaBP_M, MaCV_M, LuongCoBan_M)"+" VALUES(?,?,?,?,?,?,?,?)";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
        pstmt.setString(1,bd.getMaNV());
        pstmt.setString(2,bd.getNgayHieuLuc());
        pstmt.setString(4,bd.getMaCV_C());
        pstmt.setString(3,bd.getMaBP_C());
        pstmt.setDouble(5,bd.getHeSoLuong_C());
        pstmt.setString(7,bd.getMaCV_M());
        pstmt.setString(6,bd.getMaBP_M());
        pstmt.setDouble(8,bd.getHeSoLuong_M());
        
        return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean update(BienDongNhanSu bd) throws Exception{
        String sql="UPDATE BienDongNhanSu SET  MaBP_M= ?, MaCV_M =?, LuongCoBan_M=?"+" WHERE MaNV=? and NgayBienDong=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(4,bd.getMaNV());
            pstmt.setString(5,bd.getNgayHieuLuc());
            pstmt.setString(2,bd.getMaCV_M());
            pstmt.setString(1,bd.getMaBP_M());
            pstmt.setDouble(3,bd.getHeSoLuong_M());
        
            return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean delete(String MaNV,String NgayBienDong) throws Exception{
        String sql="DELETE BienDongNhanSu WHERE MaNV=? and NgayBienDong=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setString(2,NgayBienDong);
        
            return pstmt.executeUpdate()>0;
        }
    }
    
    
    public BienDongNhanSu FindNhanVien(String MaNV,String NgayBienDong) throws Exception{
        String  sql= "Select * From BienDong_v Where MaNV=? and NgayBienDong =?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setString(2,NgayBienDong);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    BienDongNhanSu bd=createBoTri(rs);
                    return bd;
                }
            }
            return null;
        }
    }
    
    public BienDongNhanSu FindNhanVien(String MaNV) throws Exception{
        String  sql= "Select * From Biendong_v Where MaNV=? ";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    BienDongNhanSu bd=createBoTri(rs);
                    return bd;
                }
            }
            return null;
        }
    }
    
    
    private BienDongNhanSu createBoTri(final ResultSet rs) throws SQLException {
        BienDongNhanSu bt=new BienDongNhanSu();
        bt.setMaNV(rs.getString("MaNV"));
        bt.setNgayHieuLuc(rs.getString("NgayBienDong"));
        bt.setMaBP_C(rs.getString("MaBP_C"));
        bt.setMaCV_C(rs.getString("MaCV_C"));
        bt.setHeSoLuong_C(rs.getDouble("LuongCoBan_C"));
        bt.setTenBP_C(rs.getString("TenBP_C"));
        bt.setTenCV_C(rs.getString("TenCV_C"));
        bt.setTenBP_M(rs.getString("TenBP_M"));
        bt.setTenCV_M(rs.getString("TenCV_M"));
        bt.setMaBP_M(rs.getString("MaBP_M"));
        bt.setMaCV_M(rs.getString("MaCV_M"));
        bt.setHeSoLuong_M(rs.getDouble("LuongCoBan_M"));
        return bt;
    }
}
