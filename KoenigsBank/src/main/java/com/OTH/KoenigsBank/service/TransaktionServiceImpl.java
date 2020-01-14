package com.OTH.KoenigsBank.service;

import com.OTH.KoenigsBank.dao.TransaktionRepository;
import com.OTH.KoenigsBank.entity.Transaktion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaktionServiceImpl implements TransaktionService {

    private TransaktionRepository transaktionRepository;

    @Autowired
    public TransaktionServiceImpl(TransaktionRepository transaktionRepository) {
        this.transaktionRepository = transaktionRepository;
    }

    @Override
    public Transaktion buchen(Transaktion transaktion) {
        return transaktionRepository.save(transaktion);
    }

    @Override
    public List<Transaktion> getAllTransaktionenByKontonummer(String kontonummer) {
        return transaktionRepository.getAllTransaktionenByKontonummer(kontonummer);
    }
}