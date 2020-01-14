package com.OTH.KoenigsBank.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaktion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transId;

    private final Date zeitstempel = new Date();

    @ManyToOne(cascade=CascadeType.PERSIST)
    private Konto vonKonto;

    @ManyToOne(cascade=CascadeType.PERSIST)
    private Konto zuKonto;

    private double betrag;

    private String beschreibung;

    @OneToOne
    private Tan tannummer;

    public Transaktion() {}

    public Transaktion(Konto vonKonto, Konto zuKonto, double betrag, String beschreibung, Tan tannummer) {
        this.vonKonto = vonKonto;
        this.zuKonto = zuKonto;
        this.betrag = betrag;
        this.beschreibung = beschreibung;
        this.tannummer = tannummer;
    }

    public long getTransId() {
        return transId;
    }

    public Date getZeitstempel() {
        return zeitstempel;
    }

    public Konto getVonKonto() {
        return vonKonto;
    }

    public void setVonKonto(Konto vonKonto) {
        this.vonKonto = vonKonto;
    }

    public Konto getZuKonto() {
        return zuKonto;
    }

    public void setZuKonto(Konto zuKonto) {
        this.zuKonto = zuKonto;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Tan getTannummer() { return tannummer; }

    public void setTannummer(Tan tannummer) { this.tannummer = tannummer; }

    @Override
    public String toString() {
        return "Transaktion{" +
                "transId=" + transId +
                ", zeitstempel=" + zeitstempel +
                ", vonKonto=" + vonKonto +
                ", zuKonto=" + zuKonto +
                ", betrag=" + betrag +
                ", beschreibung='" + beschreibung + '\'' +
                ", tannummer=" + tannummer +
                '}';
    }
}
