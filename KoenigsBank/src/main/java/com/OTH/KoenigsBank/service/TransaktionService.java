package com.OTH.KoenigsBank.service;

import com.OTH.KoenigsBank.entity.Transaktion;

import java.util.List;

public interface TransaktionService {
    Transaktion buchen(Transaktion ueberweisung);
    List<Transaktion> getAllTransaktionenByKontonummer(String kontonummer);

}
