/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.DatabaseHelper;
import Model.KhenThuong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class KhenThuongDAO {
    public List<KhenThuong> FindAllBoTri() throws Exception{
        String  sql= "SELECT * FROM KhenThuong  Order By NgayKhenThuong";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<KhenThuong> list=new ArrayList<>();
                while(rs.next()){
                    KhenThuong bp=createKhenThuong(rs);
                    list.add(bp);
                }
                return list;
            }
        } 
    }
    public boolean insert(KhenThuong kt) throws Exception{
        String sql="INSERT INTO KhenThuong (MaNV , NgayKhenThuong, MucKhenThuong, GhiChu)"+" VALUES(?,?,?,?)";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
        pstmt.setString(1,kt.getMaNV());
        pstmt.setString(2,kt.getNgayKhenThuong());
        pstmt.setDouble(3,kt.getMucKhenThuong());
        pstmt.setString(4,kt.getGhiChu());
        
        return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean update(KhenThuong kt) throws Exception{
        String sql="UPDATE KhenThuong SET  MucKhenThuong= ?, GhiChu=?"+" WHERE MaNV=? and NgayKhenThuong=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(3,kt.getMaNV());
            pstmt.setString(4,kt.getNgayKhenThuong());
            pstmt.setDouble(1,kt.getMucKhenThuong());
            pstmt.setString(2,kt.getGhiChu());
            return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean delete(String MaNV, String Ngay) throws Exception{
        String sql="DELETE KhenThuong"+" WHERE MaNV=? and NgayKhenThuong=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setString(2,Ngay);
            return pstmt.executeUpdate()>0;
        }
    }
    
    public KhenThuong FindNhanVien(String MaNV,String NgayBienDong) throws Exception{
        String  sql= "SELECT * FROM KhenThuong WHERE MaNv=? and NgayKhenThuong=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setString(2,NgayBienDong);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    KhenThuong bd=createKhenThuong(rs);
                    return bd;
                }
            }
            return null;
        }
    }
    
    private KhenThuong createKhenThuong(final ResultSet rs) throws SQLException {
        KhenThuong bt=new KhenThuong();
        bt.setMaNV(rs.getString("MaNV"));
        bt.setNgayKhenThuong(rs.getString("NgayKhenThuong"));
        bt.setMucKhenThuong(rs.getDouble("MucKhenThuong"));
        bt.setGhiChu(rs.getString("GhiChu"));
        return bt;
    }
}
