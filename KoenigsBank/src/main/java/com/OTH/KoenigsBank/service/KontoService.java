package com.OTH.KoenigsBank.service;

import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Tan;

import java.util.List;

public interface KontoService {
    Konto findenByKontonummer(String kontonummer);
    Konto updateKonto(Konto konto);
    List<Tan> getAllUnusedTans(String kontonummer);
}
