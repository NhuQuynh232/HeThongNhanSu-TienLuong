/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DELL
 */
public class DatabaseHelper {
    public static Connection openConnection() throws Exception{
        Connection conn=null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn= (Connection)DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu","sa","123");
        return conn;
    } 
}
