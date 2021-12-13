/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author QUYNH
 */
public class Format{
    // Format number
    public static String FormatNumber(double number) {
        Locale locale = new Locale("en", "EN");
        String pattern = "###,###";
        DecimalFormat decimal = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimal.applyPattern(pattern);
        return decimal.format(number);
    }
    
    public static double RemoveFormat(String number){ 
        String newnumber=number.replace(",", "");
        return  Double.parseDouble(newnumber);
    }
}
