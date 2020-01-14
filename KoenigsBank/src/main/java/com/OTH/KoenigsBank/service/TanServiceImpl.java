package com.OTH.KoenigsBank.service;

import com.OTH.KoenigsBank.dao.TanRepository;
import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Tan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TanServiceImpl implements TanService {

    private TanRepository tanRepository;

    @Autowired
    private KontoService kontoService;

    @Autowired
    public TanServiceImpl(TanRepository tanRepository){
        this.tanRepository = tanRepository;
    }

    @Override
    public Tan erzeugen(Konto konto) {
        Tan tempTan = new  Tan();
        tanRepository.save(tempTan);
        konto.addTannummer(tempTan);
        kontoService.updateKonto(konto);
        return tempTan;
    }

    @Override
    public List<Tan> getAllUnusedTansVonKonto(Konto konto) {
        return tanRepository.getAllUnusedTansByKontonummer(konto.getKontonummer());
    }

    @Override
    public List<Tan> getAllTansVonKonto(Konto konto) {
        return tanRepository.getAllTansByKontonummer(konto.getKontonummer());
    }

    @Override
    public Tan getTanById(long id) {
        Optional<Tan> result = tanRepository.findById(id);

        Tan tempTan = null;

        if (result.isPresent())
            tempTan = result.get();

        return tempTan;
    }

    @Override
    public Tan update(Tan tan) {
        Tan tempTan = getTanById(tan.getId());

        if(tempTan == null)
            return null;

        return tanRepository.save(tan);
    }
}
