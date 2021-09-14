package com.example.qr;

public class KendaraanResponse {

    public String rCode, message, no_plat, nama_pemilik, jabatan;

    public KendaraanResponse(String rCode, String message, String no_plat, String nama_pemilik, String jabatan) {
        this.rCode = rCode;
        this.message = message;
        this.no_plat = no_plat;
        this.nama_pemilik = nama_pemilik;
        this.jabatan = jabatan;
    }

    public String getrCode() {
        return rCode;
    }

    public String getMessage() {
        return message;
    }

    public String getNo_plat() {
        return no_plat;
    }

    public String getNama_pemilik() {
        return nama_pemilik;
    }

    public String getJabatan() {
        return jabatan;
    }
}
