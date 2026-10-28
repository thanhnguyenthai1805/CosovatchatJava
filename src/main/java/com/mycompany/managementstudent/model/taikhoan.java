/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.managementstudent.model;

/**
 *
 * @author Admin
 */
public class taikhoan {
    private String tendangnhap;
    private String matkhau;
    public taikhoan(){
        tendangnhap = "";
        matkhau="";
    }

    public taikhoan(String tendangnhap, String matkhau) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }
    private static final System.Logger LOG = System.getLogger(taikhoan.class.getName());

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
    
}
