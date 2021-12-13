/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.DatabaseHelper;
import Model.BoPhan;
import java.sql.CallableStatement;
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
public class BoPhanDAO {
    
    public List<BoPhan> FindAllBoPhan() throws Exception{
        String  sql= "SELECT * FROM BoPhan";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            try(ResultSet rs=pstmt.executeQuery()){
                List<BoPhan> list=new ArrayList<>();
                while(rs.next()){
                    BoPhan bp=createBoPhan(rs);
                    list.add(bp);
                }
                return list;
            }
        } 
    }
    
    public BoPhan FindBPByName(String TenBP) throws Exception{
        String  sql= "SELECT * FROM BoPhan WHERE TenBP=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,TenBP);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    BoPhan s=createBoPhan(rs);
                    return s;
                }
            }
            return null;
        }
    }
    
     public BoPhan FindBPByMa(String MaBP) throws Exception{
        String  sql= "SELECT * FROM BoPhan WHERE MaBP=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaBP);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    BoPhan s=createBoPhan(rs);
                    return s;
                }
            }
            return null;
        }
    }
    
    public boolean insert(BoPhan kt) throws Exception{
        String sql="INSERT INTO BoPhan (MaBP , TenBP, Email, SoDienThoai, GhiChu)"+" VALUES(?,?,?,?,?)";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
        pstmt.setString(1,kt.getMaPB());
        pstmt.setString(2,kt.getTenPB());
        pstmt.setString(3,kt.getEmail());
        pstmt.setString(4,kt.getSDT());
        pstmt.setString(5,kt.getGhiChu());
        
        return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean update(BoPhan kt) throws Exception{
        String sql="UPDATE BoPhan SET  TenBP=?,Email= ?, GhiChu=?, SoDienThoai=? WHERE MaBP=? ";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(5,kt.getMaPB());
            pstmt.setString(1,kt.getTenPB());
            pstmt.setString(2,kt.getEmail());
            pstmt.setString(4,kt.getSDT());
            pstmt.setString(3,kt.getGhiChu());
            return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean delete(String MaBP) throws Exception {
        String sql = "EXEC sp_BoPhan_del ?";
        try (
                Connection conn = DatabaseHelper.openConnection();
                CallableStatement cs = conn.prepareCall(sql);) {
            cs.setString(1, MaBP);
            return cs.executeUpdate() > 0;
        }
    }
    
    private BoPhan createBoPhan(final ResultSet rs) throws SQLException {
        BoPhan bp=new BoPhan();
        bp.setMaBP(rs.getString("MaBP"));
        bp.setTenPB(rs.getString("TenBP"));
        bp.setEmail(rs.getString("Email"));
        bp.setGhiChu(rs.getString("GhiChu"));
        bp.setSDT(rs.getString("SoDienThoai"));
        
        return bp;
    }
}
