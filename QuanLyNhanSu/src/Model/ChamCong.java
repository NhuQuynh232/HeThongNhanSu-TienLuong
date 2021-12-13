/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author GOLD ̣̣̣̣9999
 */
public class ChamCong {
    String MaNV,GhiChu;
    String NgayChamCong;

    public ChamCong() {
    }

    public ChamCong(String MaNV, String GhiChu, String NgayChamCong) {
        this.MaNV = MaNV;
        this.GhiChu = GhiChu;
        this.NgayChamCong = NgayChamCong;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getNgayChamCong() {
        return NgayChamCong;
    }

    public void setNgayChamCong(String NgayChamCong) {
        this.NgayChamCong = NgayChamCong;
    }


    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
}
