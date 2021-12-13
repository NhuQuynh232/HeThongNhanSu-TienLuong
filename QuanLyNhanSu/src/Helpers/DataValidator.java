/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class DataValidator {
    public static void validateEmpty(StringBuilder ab, JTextField field, String errorMessage){
        if(field.getText().equals("")){
            ab.append(errorMessage).append("\n");
            field.setBackground(Color.red);// nhận biết trường gặp lỗi
            field.requestFocus();// trỏ con trỏ về trường đang lỗi
        }else{
            field.setBackground(Color.WHITE);
        }
    }
    
    public static void validateEmpty(StringBuilder ab, JPasswordField field, String errorMessage){
        String password =new String(field.getPassword());
        if(password.equals("")){
            ab.append(errorMessage).append("\n");
            field.setBackground(Color.red);// nhận biết trường gặp lỗi
            field.requestFocus();// trỏ con trỏ về trường đang lỗi
        }else{
            field.setBackground(Color.WHITE);
        }
    }
}
