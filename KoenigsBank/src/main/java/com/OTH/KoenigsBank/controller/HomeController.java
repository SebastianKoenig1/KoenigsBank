package com.OTH.KoenigsBank.controller;

import com.OTH.KoenigsBank.entity.*;
import com.OTH.KoenigsBank.service.KontoService;
import com.OTH.KoenigsBank.service.KundeService;
import com.OTH.KoenigsBank.service.TanService;
import com.OTH.KoenigsBank.service.TransaktionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private KundeService kundeService;

    @Autowired
    private TransaktionService transaktionService;

    @Autowired
    private KontoService kontoService;

    @Autowired
    private TanService tanService;

    @RequestMapping("/home")
    public String starten(Model model) {
        String currUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("konto", kontoService.findenByKontonummer(currUserName));
        model.addAttribute("transaktionen", transaktionService.getAllTransaktionenByKontonummer(currUserName));
        model.addAttribute("kunde", kundeService.getKundeByKonto(kontoService.findenByKontonummer(currUserName)));
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/kundewerden")
    public String kundewerden() {
        return "registrieren";
    }

    @RequestMapping("/buchung")
    public String buchung(Model model) {
        String currUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("konto", kontoService.findenByKontonummer(currUserName));
        model.addAttribute("kunde", kundeService.getKundeByKonto(kontoService.findenByKontonummer(currUserName)));
        return "transaktion";
    }

    @RequestMapping("/kontodaten")
    public String konto(Model model) {
        String currUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("konto", kontoService.findenByKontonummer(currUserName));
        model.addAttribute("kunde", kundeService.getKundeByKonto(kontoService.findenByKontonummer(currUserName)));
        return "konto";
    }

    @RequestMapping("/aenderung")
    public String aendern(
                        @ModelAttribute("vorname") String vorname,
                        @ModelAttribute("nachname") String nachname,
                        @ModelAttribute("email") String email,
                        @ModelAttribute("straße") String straße,
                        @ModelAttribute("hausnummer") String hausnummer,
                        @ModelAttribute("plz") String plz,
                        @ModelAttribute("ort") String ort,
                        @ModelAttribute("passwort") String passwort,
                        Model model
    ) {
        String currUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Adresse adresse = new Adresse(straße, hausnummer, plz, ort);
        Kunde kunde = new Kunde(vorname, nachname, email, adresse, passwort);
        Kunde tempKunde = kundeService.getKundeByKonto(kontoService.findenByKontonummer(currUserName));
        kunde.setKonto(tempKunde.getKonto());
        kunde.setId(tempKunde.getId());
        kundeService.aendern(kunde);

        model.addAttribute("konto", kontoService.findenByKontonummer(currUserName));
        model.addAttribute("kunde", kundeService.getKundeByKonto(kontoService.findenByKontonummer(currUserName)));
        return "kunde_aendern_erfolg";
    }

    @RequestMapping("/tan")
    public String tannummern(Model model) {
        String currUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("kunde", kundeService.getKundeByKonto(kontoService.findenByKontonummer(currUserName)));
        model.addAttribute("konto", kontoService.findenByKontonummer(currUserName));
        model.addAttribute("tannummern", tanService.getAllTansVonKonto(kontoService.findenByKontonummer(currUserName)));
        return "tan";
    }

    @RequestMapping("/tanhinzufuegen")
    public String tannummerHinzufuegen(Model model){
        String currUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Tan> tempList = tanService.getAllUnusedTansVonKonto(kontoService.findenByKontonummer(currUserName));
        if(tempList.size() < 5)
            tanService.erzeugen(kontoService.findenByKontonummer(currUserName));
        model.addAttribute("kunde", kundeService.getKundeByKonto(kontoService.findenByKontonummer(currUserName)));
        model.addAttribute("konto", kontoService.findenByKontonummer(currUserName));
        model.addAttribute("tannummern", tanService.getAllTansVonKonto(kontoService.findenByKontonummer(currUserName)));

        return "tan";
    }

    @RequestMapping("/registrieren")
    public String registrieren(
            @ModelAttribute("vorname") String vorname,
            @ModelAttribute("nachname") String nachname,
            @ModelAttribute("email") String email,
            @ModelAttribute("straße") String straße,
            @ModelAttribute("hausnummer") String hausnummer,
            @ModelAttribute("plz") String plz,
            @ModelAttribute("ort") String ort,
            @ModelAttribute("passwort") String passwort,
            Model model
    ) {
        Adresse adresse = new Adresse(straße, hausnummer, plz, ort);
        Kunde kunde = new Kunde(vorname, nachname, email, adresse, passwort);
        kunde = kundeService.registrieren(kunde);
        model.addAttribute("kontonummer", kunde.getKonto().getKontonummer());

        return "kundenkonto";
    }

    @RequestMapping("/transaktion")
    public String transaktion(
            @ModelAttribute("zu") String zu,
            @ModelAttribute("betrag") double betrag,
            @ModelAttribute("beschreibung") String beschreibung,
            @ModelAttribute("tan") String tannummer,
            Model model
    ) {
        String currUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("konto", kontoService.findenByKontonummer(currUserName));
        model.addAttribute("kunde", kundeService.getKundeByKonto(kontoService.findenByKontonummer(currUserName)));

        Konto kVon = kontoService.findenByKontonummer(currUserName);
        Konto kZu = kontoService.findenByKontonummer(zu);

        Tan  tempTan = tanService.getTanById(Long.parseLong(tannummer));
        List<Tan> tempTanList =  tanService.getAllUnusedTansVonKonto(kVon);

        if (kVon != null && kZu != null && !kVon.equals(kZu)) {
            if(kVon.getKontostand() > betrag) {
                if (tempTanList.contains(tempTan)) {
                    Transaktion transaktion = new Transaktion(kVon, kZu, betrag, beschreibung, tempTan);

                    tempTan.setBenutzungsDatum();
                    tempTan.setBenutzt(true);

                    kVon.setKontostand(kVon.getKontostand() - betrag);
                    kZu.setKontostand(kZu.getKontostand() + betrag);

                    tanService.update(tempTan);
                    transaktionService.buchen(transaktion);
                    kontoService.updateKonto(kVon);
                    kontoService.updateKonto(kZu);
                    model.addAttribute("transnr", transaktion.getTransId());

                    return "transaktionsnr";
                }
                return "transaktion_fehlgeschlagen_tan";
            }
            return "transaktion_fehlgeschlagen_kontostand";
        }
        return "transaktion_fehlgeschlagen_empfaenger";
    }
}
