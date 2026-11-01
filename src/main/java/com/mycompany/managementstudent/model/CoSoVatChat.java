
package com.mycompany.managementstudent.model;
import java.time.LocalDate;

public class CoSoVatChat {
        private String ma;
        private String ten;
        private String tenLoai;
        private String maloai;
        private String phongkhuvuc;
        private int soluong;
        private String tinhtrang;
        private LocalDate ngaynhap;
        private String ghichu;
        public CoSoVatChat() {
}
    public CoSoVatChat(
            String ma,
            String ten,
            String tenloai,
            String maloai,
            String phongkhuvuc,
            int soluong,
            String tinhtrang,
            LocalDate ngaynhap,
            String ghichu
    ){
        this.ma=ma;
        this.tenLoai=tenloai;
        this.ten=ten;
        this.maloai=maloai;
        this.phongkhuvuc=phongkhuvuc;
        this.soluong=soluong;
        this.tinhtrang=tinhtrang;
        this.ngaynhap=ngaynhap;
        this.ghichu=ghichu;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    
    public String getMa(){
        return ma;
    }
     
    public void setMa(String ma){
    this.ma=ma;
    }
     public String getTen() {
        return ten;
    }

    public void setTen(String ten){
        this.ten = ten;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getPhongKhuVuc() {
        return phongkhuvuc;
    }

    public void setPhongKhuVuc(String phongkhuvuc) {
        this.phongkhuvuc = phongkhuvuc;
    }

    public int getSoLuong() {
        return soluong;
    }

    public void setSoLuong(int soluong) {
        this.soluong = soluong;
    }

    public String getTinhTrang() {
        return tinhtrang;
    }

    public void setTinhTrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public LocalDate getNgayNhap() {
        return ngaynhap;
    }

    public void setNgayNhap(LocalDate ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public String getGhiChu() {
        return ghichu;
    }

    public void setGhiChu(String ghichu) {
        this.ghichu = ghichu;
    }

    
}
