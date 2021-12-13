/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author QUYNH
 */
public class TableDesign {
    public static void Design(JTable table){
        JTableHeader header=table.getTableHeader();
        //Set header center
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        table.getTableHeader().setResizingAllowed(false);
        //Font size header
        Font headerFont = new Font("Verdana", Font.PLAIN, 14);
        header.setFont(headerFont);
    }
}
