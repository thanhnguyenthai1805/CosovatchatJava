/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.managementstudent.model;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class CoSoVatChat {
        private int ma;
        private String ten;
        private String loai;
        private String phongkhuvuc;
        private int soluong;
        private String tinhtrang;
        private LocalDate ngaynhap;
        private String ghichu;
        public CoSoVatChat() {
}
    public CoSoVatChat(
            int ma,
            String ten,
            String loai,
            String phongkhuvuc,
            int soluong,
            String tinhtrang,
            LocalDate ngaynhap,
            String ghichu
    ){
        this.ma=ma;
        this.ten=ten;
        this.loai=loai;
        this.phongkhuvuc=phongkhuvuc;
        this.soluong=soluong;
        this.tinhtrang=tinhtrang;
        this.ngaynhap=ngaynhap;
        this.ghichu=ghichu;
    }

    public int getMa(){
        return ma;
    }
     
    public void setMa(int ma){
    this.ma=ma;
    }
     public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
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
