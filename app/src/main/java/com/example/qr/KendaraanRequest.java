package com.example.qr;

public class KendaraanRequest {

    public String usergw, passgw, no_plat;

    public KendaraanRequest(String no_plat) {
        this.usergw = "mobile";
        this.passgw = "mobile123*";
        this.no_plat = no_plat;
    }
}
