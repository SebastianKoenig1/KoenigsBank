package com.OTH.KoenigsBank.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Konto {

    @Id
    private String kontonummer;

    private double kontostand = 0;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="konr")
    private List<Tan> tannummern = new ArrayList<>();

    public Konto() {
        this.kontonummer = String.format("%010d", new Random().nextInt(999999999));
    }

    public Konto(String kontonummer, double kontostand) {
        this.kontonummer = kontonummer;
        this.kontostand = kontostand;
    }

    public Konto(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public double getKontostand() {
        return kontostand;
    }

    public void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    public List<Tan> getTannummern() {
        return tannummern;
    }

    public void setTannummern(List<Tan> tannummern) {
        this.tannummern = tannummern;
    }

    public void addTannummer(Tan tannummer) {
        tannummern.add(tannummer);
    }

    @Override
    public String toString() {
        return "Konto{" +
                "kontonummer='" + kontonummer + '\'' +
                ", kontostand=" + kontostand +
                ", tannummern=" + tannummern +
                '}';
    }
}
