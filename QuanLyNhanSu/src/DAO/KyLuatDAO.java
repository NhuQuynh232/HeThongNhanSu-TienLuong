/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.DatabaseHelper;
import java.sql.Connection;
import Model.KyLuat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class KyLuatDAO {
    public List<KyLuat> FindAllBoTri() throws Exception{
        String  sql= "SELECT * FROM KyLuat Order By NgayKyLuat";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<KyLuat> list=new ArrayList<>();
                while(rs.next()){
                    KyLuat bp=createKyLuat(rs);
                    list.add(bp);
                }
                return list;
            }
        } 
    }
    public boolean insert(KyLuat kl) throws Exception{
        String sql="INSERT INTO KyLuat (MaNV , NgayKyLuat, MucKyLuat, GhiChu)"+" VALUES(?,?,?,?)";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
        pstmt.setString(1,kl.getMaNV());
        pstmt.setString(2,kl.getNgayKyLuat());
        pstmt.setDouble(3,kl.getMucKyLuat());
        pstmt.setString(4,kl.getGhiChu());
        
        return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean update(KyLuat kt) throws Exception{
        String sql="UPDATE KyLuat SET  MucKyLuat= ?, GhiChu=?"+" WHERE MaNV=? and NgayKyLuat=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(3,kt.getMaNV());
            pstmt.setString(4,kt.getNgayKyLuat());
            pstmt.setDouble(1,kt.getMucKyLuat());
            pstmt.setString(2,kt.getGhiChu());
            return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean delete(String MaNV, String Ngay) throws Exception{
        String sql="DELETE KyLuat"+" WHERE MaNV=? and NgayKyLuat=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setString(2,Ngay);
            return pstmt.executeUpdate()>0;
        }
    }
    
    public KyLuat FindNhanVien(String MaNV,String NgayBienDong) throws Exception{
        String  sql= "SELECT * FROM KyLuat WHERE MaNV=? and NgayKyLuat=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setString(2,NgayBienDong);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    KyLuat bd=createKyLuat(rs);
                    return bd;
                }
            }
            return null;
        }
    }
    
    private KyLuat createKyLuat(final ResultSet rs) throws SQLException {
        KyLuat bt=new KyLuat();
        bt.setMaNV(rs.getString("MaNV"));
        bt.setNgayKyLuat(rs.getString("NgayKyLuat"));
        bt.setMucKyLuat(rs.getDouble("MucKyLuat"));
        bt.setGhiChu(rs.getString("GhiChu"));
        return bt;
    }
}
