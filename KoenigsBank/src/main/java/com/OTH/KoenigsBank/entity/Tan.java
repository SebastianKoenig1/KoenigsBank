package com.OTH.KoenigsBank.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean benutzt;

    private Date benutzungsDatum;

    private String kontonummer;

    public Tan() {}

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() { return id; }

    public boolean isBenutzt() { return benutzt; }

    public void setBenutzt(boolean benutzt) { this.benutzt = benutzt; }

    public Date getBenutzungsDatum() { return benutzungsDatum; }

    public void setBenutzungsDatum() { this.benutzungsDatum = new Date(); }

    @Override
    public String toString() {
        return "Tan{" +
                "id=" + id +
                ", benutzt=" + benutzt +
                ", benutzungsDatum=" + benutzungsDatum +
                '}';
    }
}
