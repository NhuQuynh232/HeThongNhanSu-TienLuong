/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Helpers.DatabaseHelper;
import java.util.List;
import Model.ChamCong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class ChamCongDAO {
    public List<ChamCong> FindAllChamCong() throws Exception{
        String  sql= "SELECT * FROM ChamCong";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<ChamCong> list=new ArrayList<>();
                while(rs.next()){
                    ChamCong cc=createChamCong(rs);
                    list.add(cc);
                }
                return list;
            }
        } 
    }
    
    public List<ChamCong> FindAllNgayChamCong(String NgayChamCong) throws Exception{
        String  sql= "SELECT * FROM ChamCong WHERE NgayChamCong=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,NgayChamCong);
            try(ResultSet rs=pstmt.executeQuery()){
                List<ChamCong> list=new ArrayList<>();
                while(rs.next()){
                    ChamCong cc=createChamCong(rs);
                    list.add(cc);
                }
                return list;
            }
        } 
    }
    
    public ChamCong FindNhanVien(String MaNV,String NgayChamCong) throws Exception{
        String  sql= "SELECT * FROM ChamCong Where MaNV=? and NgayChamCong=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setString(2,NgayChamCong);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    ChamCong nv=createChamCong(rs);
                    return nv;
                }
            }
            return null;
        }
    }
    
    public boolean insert(ChamCong cc) throws Exception{
        String sql="INSERT INTO ChamCong (MaNV , NgayChamCong, GhiChu)"+" VALUES(?,?,?)";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
        pstmt.setString(1,cc.getMaNV());
        pstmt.setString(2,cc.getNgayChamCong());
        pstmt.setString(3,cc.getGhiChu());
        
        return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean update(ChamCong cc) throws Exception {
        String sql = "UPDATE ChamCong SET GhiChu=?" + " WHERE MaNV =? and NgayChamCong=?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(2, cc.getMaNV());
            pstmt.setString(3, cc.getNgayChamCong());
            pstmt.setString(1, cc.getGhiChu());

            return pstmt.executeUpdate() > 0;
        }
    }
    
     public boolean delete(String MaNV,String NgayChamCong) throws Exception{
        String sql="DELETE ChamCong "+" WHERE MaNV =? and NgayChamCong=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
                ChamCong cc=new ChamCong();
                pstmt.setString(1,cc.getMaNV());
                pstmt.setString(2,cc.getNgayChamCong());
        return pstmt.executeUpdate()>0;
        }
    }
    
    private ChamCong createChamCong(final ResultSet rs) throws SQLException {
        ChamCong cc=new ChamCong();
        cc.setMaNV(rs.getString("MaNV"));
        cc.setNgayChamCong(rs.getString("NgayChamCong"));
        cc.setGhiChu(rs.getString("GhiChu"));
        return cc;
    }
    
    public ChamCong FindTongCC(String MaNV,int Thang,int Nam,int Ngay) throws Exception{
        String  sql= "SELECT * FROM ChamCong Where MONTH(NgayChamCong)=? and YEAR(NgayChamCong)=? and MaNV=? and DAY(NgayChamCong)=?";
        try(
            Connection conn = DatabaseHelper.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, Thang);
            pstmt.setInt(2, Nam);
            pstmt.setString(3, MaNV);
            pstmt.setInt(4, Ngay);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ChamCong cc = new ChamCong();
                    cc.setGhiChu(rs.getString("GhiChu"));
                    return cc;
                }
            }
            return null;
        }
    }

}
