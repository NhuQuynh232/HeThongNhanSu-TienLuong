/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class ChucVu {
    String MaCV,TenCV,GhiChu;
    int PhuCap;

    public ChucVu() {
    }

    public ChucVu(String MaCV, String TenCV, String GhiChu, int PhuCap) {
        this.MaCV = MaCV;
        this.TenCV = TenCV;
        this.GhiChu = GhiChu;
        this.PhuCap = PhuCap;
    }

    public String getMaCV() {
        return MaCV;
    }

    public void setMaCV(String MaCV) {
        this.MaCV = MaCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String TenCV) {
        this.TenCV = TenCV;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public int getPhuCap() {
        return PhuCap;
    }

    public void setPhuCap(int PhuCap) {
        this.PhuCap = PhuCap;
    }
    
}
