package com.OTH.KoenigsBank.dao;

import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Kunde;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KundeRepository extends CrudRepository<Kunde, Long> {
    Optional<Kunde> findByKonto(Konto konto);
}
