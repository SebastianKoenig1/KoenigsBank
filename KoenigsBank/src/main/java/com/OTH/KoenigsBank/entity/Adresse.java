package com.OTH.KoenigsBank.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {


    private String strassenname;

    private String hausnummer;

    private String plz;

    private String ort;

    public Adresse(){}

    public Adresse(String straßenname, String hausnummer, String plz, String ort) {
        this.ort = ort;
        this.strassenname = straßenname;
        this.hausnummer = hausnummer;
        this.plz = plz;
    }

    public String getStrassenname() {
        return strassenname;
    }

    public void setStrassenname(String straßenname) {
        this.strassenname = straßenname;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() { return ort; }

    public void setOrt(String ort) { this.ort = ort; }

    @Override
    public String toString() {
        return "Adresse{" +
                "straßenname='" + strassenname + '\'' +
                ", hausnummer='" + hausnummer + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }
}
