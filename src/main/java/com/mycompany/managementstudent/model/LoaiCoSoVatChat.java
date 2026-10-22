/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.managementstudent.model;

/**
 *
 * @author Admin
 */
public class LoaiCoSoVatChat {

    private int maloai;
    private String tenloai;

    public LoaiCoSoVatChat() {
        maloai = 0;
        tenloai = "";
    }

    public LoaiCoSoVatChat(int maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getMaloai() {
        return maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

}
