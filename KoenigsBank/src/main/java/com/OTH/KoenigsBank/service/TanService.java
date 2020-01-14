package com.OTH.KoenigsBank.service;

import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Tan;

import java.util.List;

public interface TanService {
    Tan erzeugen(Konto konto);
    Tan update(Tan tan);
    Tan getTanById(long id);
    List<Tan> getAllUnusedTansVonKonto(Konto konto);
    List<Tan> getAllTansVonKonto(Konto konto);
    
}
