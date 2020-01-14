package com.OTH.KoenigsBank.service;

import com.OTH.KoenigsBank.dao.KontoRepository;
import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Tan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KontoServiceImpl implements KontoService{

    private KontoRepository kontoRepository;

    private TanService tanService;

    @Autowired
    public KontoServiceImpl(KontoRepository kontoRepository, TanService tanService) {
        this.kontoRepository = kontoRepository;
        this.tanService = tanService;
    }

    @Override
    public Konto findenByKontonummer(String kontonummer) {
        Optional<Konto> result = kontoRepository.findByKontonummer(kontonummer);

        Konto konto = null;

        if (result.isPresent())
            konto = result.get();

        return konto;
    }

    @Override
    public Konto updateKonto(Konto konto) {
        Konto tempKonto = findenByKontonummer(konto.getKontonummer());

        if(tempKonto == null)
            return null;

        if(konto.getTannummern() != null)
            tempKonto.setTannummern(konto.getTannummern());
        if(konto.getKontostand() != tempKonto.getKontostand())
            tempKonto.setKontostand(konto.getKontostand());

        return kontoRepository.save(tempKonto);
    }

    @Override
    public List<Tan> getAllUnusedTans(String kontonummer) {
        return tanService.getAllUnusedTansVonKonto(findenByKontonummer(kontonummer));
    }
}
