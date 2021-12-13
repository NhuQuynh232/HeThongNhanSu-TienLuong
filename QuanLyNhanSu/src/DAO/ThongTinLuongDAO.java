/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helpers.DatabaseHelper;
import Model.ThongTinLuong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class ThongTinLuongDAO {
//    public ThongTinLuong FindAllByMa(String MaNV, int Thang, int Nam) throws Exception{
//        String  sql= "SELECT * FROM ThongTinLuong Where MaNV=? and MONTH(NgayBatDau)=? and YEAR(NgayBatDau)=?";
//        try(
//            Connection conn= DatabaseHelper.openConnection();
//            PreparedStatement pstmt=conn.prepareStatement(sql);
//            ){
//            pstmt.setString(1,MaNV);
//            pstmt.setInt(2,Thang);
//            pstmt.setInt(3,Nam);
//            try(ResultSet rs=pstmt.executeQuery()){
//                while(rs.next()){
//                    ThongTinLuong tt=createLuong(rs);
//                    return tt;
//                }
//                return null;
//            }
//        } 
//    }
//    
//    public List<ThongTinLuong> FindAllByMa(String MaNV) throws Exception{
//        String  sql= "SELECT * FROM ThongTinLuong Where MaNV=?";
//        try(
//            Connection conn= DatabaseHelper.openConnection();
//            PreparedStatement pstmt=conn.prepareStatement(sql);
//            ){
//            pstmt.setString(1,MaNV);
//            try(ResultSet rs=pstmt.executeQuery()){
//                List<ThongTinLuong> list=new ArrayList<>();
//                while(rs.next()){
//                    ThongTinLuong bp=createLuong(rs);
//                    list.add(bp);
//                }
//                return list;
//            }
//        } 
//    }
    
    private ThongTinLuong createLuong(final ResultSet rs) throws SQLException {
        ThongTinLuong l=new ThongTinLuong();
        l.setMaNV(rs.getString("MaNV"));
        l.setNgayBatDau(rs.getString("NgayBatDau"));
        l.setSoNgayDiLam(rs.getInt("SoNgayDiLam"));
        l.setSoNgayCongTac(rs.getInt("SoNgayCongTac"));
        l.setSoNgayNghiKhongLuong(rs.getInt("SoNgayNghiKhongLuong"));
        l.setSoNgayNghiCoLuong(rs.getInt("SoNgayNghiCoLuong"));
        return l;
    }
    
    //Tính số ngày đi làm
    public int FindNgayDiLam(String MaNV, int Thang, int Nam) throws Exception{
        String  sql= "SELECT Count(NgayChamCong) as SoNgay FROM ChamCong Where MaNV=? and MONTH(NgayChamCong)= ? and GhiChu=N'C' and YEAR(NgayChamCong)=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setInt(2,Thang);
            pstmt.setInt(3,Nam);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("SoNgay");
                }
                return 0;
            }
        }
    }
    //tính số ngày nghỉ có phép
    public int FindNgayNghiCoLuong(String MaNV, int Thang, int Nam) throws Exception{
        String  sql= "SELECT Count(NgayChamCong) as SoNgay FROM ChamCong Where MaNV=? and MONTH(NgayChamCong)= ? and GhiChu=N'CL' and YEAR(NgayChamCong)=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setInt(2,Thang);
            pstmt.setInt(3,Nam);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("SoNgay");
                }
                return 0;
            }
            
        }
    }
    //tính số ngày nghỉ không phép
    public int FindNgayNghiKhongLuong(String MaNV, int Thang, int Nam) throws Exception{
        String  sql= "SELECT Count(NgayChamCong) as SoNgay FROM ChamCong Where MaNV=? and GhiChu=N'KL' and  MONTH(NgayChamCong)= ? and YEAR(NgayChamCong)=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setInt(2,Thang);
            pstmt.setInt(3,Nam);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("SoNgay");
                }
                return 0;
            }
            
        }
    }
    //tính số ngày công tác
    public int FindNgayCongTac(String MaNV, int Thang, int Nam) throws Exception{
        String  sql= "SELECT Count(NgayChamCong) as SoNgay FROM ChamCong Where MaNV=? and GhiChu=N'CT' and  MONTH(NgayChamCong)= ? and YEAR(NgayChamCong)=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,MaNV);
            pstmt.setInt(2,Thang);
            pstmt.setInt(3,Nam);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("SoNgay");
                }
                return 0;
            }
        }
    }
}
    
