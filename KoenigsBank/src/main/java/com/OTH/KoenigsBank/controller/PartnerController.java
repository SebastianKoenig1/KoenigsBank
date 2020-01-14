package com.OTH.KoenigsBank.controller;

import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Kunde;
import com.OTH.KoenigsBank.entity.Tan;
import com.OTH.KoenigsBank.entity.Transaktion;
import com.OTH.KoenigsBank.service.KontoService;
import com.OTH.KoenigsBank.service.KundeService;
import com.OTH.KoenigsBank.service.TanService;
import com.OTH.KoenigsBank.service.TransaktionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    private TransaktionService transaktionService;

    @Autowired
    private KontoService kontoService;

    @Autowired
    private TanService tanService;

    @Autowired
    private KundeService kundeService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/transaktion/buchung")
    @PostMapping
    public String buchungTransaktion(@RequestBody Transaktion transaktion,
                                         @RequestParam() String passwort) {

        Konto von = kontoService.findenByKontonummer(transaktion.getVonKonto().getKontonummer());
        Konto zu = kontoService.findenByKontonummer(transaktion.getZuKonto().getKontonummer());

        Kunde partner = kundeService.getKundeByKonto(von);

        List<Tan> tempTanList = tanService.getAllUnusedTansVonKonto(von);
        Tan tempTan = tanService.getTanById(transaktion.getTannummer().getId());

        if(von != null && zu != null && !von.equals(zu)) {
            if(von.getKontostand() > transaktion.getBetrag()) {
                if (passwordEncoder.matches(passwort, partner.getPassword())) {
                    if (tempTanList.contains(tempTan)) {
                        Transaktion tempTrans = new Transaktion(von, zu, transaktion.getBetrag(), transaktion.getBeschreibung(), tempTan);

                        von.setKontostand(von.getKontostand() - tempTrans.getBetrag());
                        zu.setKontostand(zu.getKontostand() + tempTrans.getBetrag());

                        transaktionService.buchen(tempTrans);
                        kontoService.updateKonto(von);
                        kontoService.updateKonto(zu);
                        return "Transaktion erfolgreich!";
                    }
                    return "Tannummer ist falsch !";
                }
                return "Passwort des Sender-Kontos ist falsch!";
            }
            return "Sender-Konto hat zu wenig Geld auf dem Konto!";
        }
        return "Eines oder beide Tranksaktions-Konten existieren nicht. Empfänger darf auch nicht gleich Sender sein!";
    }

    @RequestMapping("/transaktion/einzug")
    @PostMapping
    public Transaktion einzugTransaktion(@RequestBody Transaktion transaktion,
                                         @RequestParam() String password) {

        Konto von = kontoService.findenByKontonummer(transaktion.getVonKonto().getKontonummer());
        Konto zu = kontoService.findenByKontonummer(transaktion.getZuKonto().getKontonummer());

        Kunde partner = kundeService.getKundeByKonto(von);

        if(von != null && zu != null && password.equals(partner.getPasswort())) {
            transaktionService.buchen(transaktion);
            return transaktion;
        }
        return null;
    }
}
