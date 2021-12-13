/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.User;
import java.sql.Connection;
import Helpers.DatabaseHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author DELL
 */
public class NguoiDungDAO {
    public User checkLogin(String UserName,String PassWord) throws Exception{
        String sql="SELECT * FROM TaiKhoan WHERE UserName = ? AND Password = ?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,UserName);
            pstmt.setString(2,PassWord);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    User user=new User();
                    user.setUserName(rs.getString("UserName"));
                    user.setPassword(rs.getString("Password"));
                    user.setRole(rs.getString("Role"));
                    user.setDep(rs.getString("Dep"));
                    return  user;
                }
            }return null;
        }
    }
    
    public boolean insert(User user) throws Exception{
        String sql="INSERT INTO TaiKhoan (UserName, Password,  Role, Dep)"+"VALUES(?,?,?,?)";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2,user.getPassword());
        pstmt.setString(3,user.getRole());
        pstmt.setString(4,user.getDep());
        
        return pstmt.executeUpdate()>0;
        }
    }
    public boolean update(User user) throws Exception{
        String sql="UPDATE  TaiKhoan SET Password=? "+"Where UserName=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
        pstmt.setString(2,user.getUserName());
        pstmt.setString(1,user.getPassword());
        
        return pstmt.executeUpdate()>0;
        }
    }
    
    public boolean delete(String UserName) throws Exception{
        String sql="DELETE FROM TaiKhoan"+" WHERE UserName =?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
        pstmt.setString(1,UserName);
        return pstmt.executeUpdate()>0;
        }
    }
    
    public User FindUser(String UserName) throws Exception{
        String  sql= "SELECT * FROM TaiKhoan WHERE UserName=?";
        try(
            Connection conn= DatabaseHelper.openConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ){
            pstmt.setString(1,UserName);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    User user=new User();
                    user.setUserName(rs.getString("UserName"));
                    user.setRole(rs.getString("Role"));
                    user.setDep(rs.getString("Dep"));
                    return user;
                }
            }
            return null;
        }
    }
}
