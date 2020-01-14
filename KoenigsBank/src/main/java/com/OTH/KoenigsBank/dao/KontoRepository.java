package com.OTH.KoenigsBank.dao;

import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Tan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Optional;

public interface KontoRepository extends CrudRepository<Konto, String> {
    Optional<Konto> findByKontonummer(String kontonummer);
}
