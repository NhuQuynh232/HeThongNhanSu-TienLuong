/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
// cho phép định nghĩa và sử dụng các phương thức hiển thị các hộp thoại thông báo
public class MessageDialogHelper {
    public static void showMessage(Component parent, String content,String title){
        JOptionPane.showMessageDialog(parent, content, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showError(Component parent, String content,String title){
        JOptionPane.showMessageDialog(parent, content, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static int showConfirm(Component parent, String content,String title){
        int choose = JOptionPane.showConfirmDialog(parent, content, title,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return choose;
    }
}
