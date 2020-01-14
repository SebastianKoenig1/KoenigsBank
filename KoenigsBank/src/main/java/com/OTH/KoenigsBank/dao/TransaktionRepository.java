package com.OTH.KoenigsBank.dao;

import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Transaktion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransaktionRepository extends CrudRepository <Transaktion, Long> {

    @Query(value = "FROM Transaktion WHERE von_konto_kontonummer = ?1 OR zu_konto_kontonummer = ?1")
    List<Transaktion> getAllTransaktionenByKontonummer(String kontonummer);
}
