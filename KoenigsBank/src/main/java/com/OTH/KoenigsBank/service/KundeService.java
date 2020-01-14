package com.OTH.KoenigsBank.service;

import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Kunde;

public interface KundeService {
    public Kunde registrieren(Kunde kunde);
    public Kunde aendern(Kunde kunde);
    public void loeschen(Kunde kunde);
    public Kunde getKundeById(long id);
    public Kunde getKundeByKonto(Konto konto);
}
