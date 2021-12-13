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
public class LyLich {
    String MaNV,TenNV,DiaChi,GhiChu,CMND,SoDienThoai,HocVan,AnhVan,TinHoc, QuocTich, MaCV,MaBP;
    byte[] HinhAnh;
    int PhuThuoc;
    double LuongCB;

    public String getMaCV() {
        return MaCV;
    }

    public void setMaCV(String MaCV) {
        this.MaCV = MaCV;
    }

    public String getMaBP() {
        return MaBP;
    }

    public void setMaBP(String MaBP) {
        this.MaBP = MaBP;
    }

    public double getLuongCB() {
        return LuongCB;
    }

    public void setLuongCB(double LuongCB) {
        this.LuongCB = LuongCB;
    }

    public String getQuocTich() {
        return QuocTich;
    }

    public void setQuocTich(String QuocTich) {
        this.QuocTich = QuocTich;
    }

    public String getHocVan() {
        return HocVan;
    }

    public void setHocVan(String HocVan) {
        this.HocVan = HocVan;
    }

    public String getAnhVan() {
        return AnhVan;
    }

    public void setAnhVan(String AnhVan) {
        this.AnhVan = AnhVan;
    }

    public String getTinHoc() {
        return TinHoc;
    }

    public void setTinHoc(String TinHoc) {
        this.TinHoc = TinHoc;
    }

    public int getPhuThuoc() {
        return PhuThuoc;
    }

    public void setPhuThuoc(int PhuThuoc) {
        this.PhuThuoc = PhuThuoc;
    }
    

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }
    String NgaySinh;
    int GioiTinh,TinhTrangHN;

    public LyLich() {
    }

    public int getTinhTrangHN() {
        return TinhTrangHN;
    }

    public void setTinhTrangHN(int TinhTrangHN) {
        this.TinhTrangHN = TinhTrangHN;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public byte[] getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(byte[] HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    
}
