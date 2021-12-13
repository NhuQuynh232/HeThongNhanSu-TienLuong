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
public class BoPhan {
    String MaBP,TenPB,GhiChu,SDT,email;

    public String getMaPB() {
        return MaBP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMaBP(String MaPB) {
        this.MaBP = MaPB;
    }

    public String getTenPB() {
        return TenPB;
    }

    public void setTenPB(String TenPB) {
        this.TenPB = TenPB;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public BoPhan() {
    }

    public BoPhan(String MaPB, String TenPB, String GhiChu, String SDT) {
        this.MaBP = MaPB;
        this.TenPB = TenPB;
        this.GhiChu = GhiChu;
        this.SDT = SDT;
    }
}
