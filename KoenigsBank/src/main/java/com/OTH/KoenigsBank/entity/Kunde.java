package com.OTH.KoenigsBank.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Kunde implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String vorname;

    private String nachname;

    private String email;

    private Adresse adresse;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Konto konto;

    private String passwort;

    public Kunde(){}

    public Kunde(String vorname, String nachname, String email, Adresse adresse, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.adresse = adresse;
        this.konto = new Konto();
        this.passwort = passwort;
    }

    public Kunde(String vorname, String nachname, String email, Adresse adresse, String passwort, Konto konto) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.adresse = adresse;
        this.konto = konto;
        this.passwort = passwort;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorites = new HashSet<>();

        return authorites;
    }

    @Override
    public String getPassword() {
        return passwort;
    }

    @Override
    public String getUsername() {
        return getKonto().getKontonummer();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", email='" + email + '\'' +
                ", adresse=" + adresse +
                ", konto=" + konto +
                ", passwort='" + passwort + '\'' +
                '}';
    }
}
