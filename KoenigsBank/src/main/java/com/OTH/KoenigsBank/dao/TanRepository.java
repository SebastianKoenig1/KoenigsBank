package com.OTH.KoenigsBank.dao;

import com.OTH.KoenigsBank.entity.Tan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TanRepository extends CrudRepository<Tan, Long> {
    @Query(value = "FROM Tan WHERE konr = ?1 AND benutzt = false")
    List<Tan> getAllUnusedTansByKontonummer(String kontonummer);

    @Query(value = "FROM Tan WHERE konr = ?1")
    List<Tan> getAllTansByKontonummer(String kontonummer);

}
