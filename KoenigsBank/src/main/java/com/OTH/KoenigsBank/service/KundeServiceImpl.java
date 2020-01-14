package com.OTH.KoenigsBank.service;

import com.OTH.KoenigsBank.dao.KundeRepository;
import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("labresources")
public class KundeServiceImpl implements KundeService, UserDetailsService {

    private KundeRepository kundeRepository;

    private KontoService kontoService;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public KundeServiceImpl(KundeRepository kundeRepository, KontoService kontoService, BCryptPasswordEncoder passwordEncoder) {
        this.kundeRepository = kundeRepository;
        this.kontoService = kontoService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Kunde registrieren(Kunde kunde) {
        kunde.setPasswort(passwordEncoder.encode(kunde.getPasswort()));
        return kundeRepository.save(kunde);
    }

    @Override
    public Kunde aendern(Kunde kunde) {
        Optional<Kunde> result = kundeRepository.findByKonto(kontoService.findenByKontonummer(SecurityContextHolder.getContext().getAuthentication().getName()));
        Kunde tempKunde = result.get();

        if(kunde.getPassword().isEmpty())
            kunde.setPasswort(passwordEncoder.encode(tempKunde.getPasswort()));

        if(!tempKunde.getPasswort().equals(kunde.getPasswort()))
            kunde.setPasswort(passwordEncoder.encode(kunde.getPasswort()));

        return kundeRepository.save(kunde);
    }

    @Override
    public void loeschen(Kunde kunde) {
        kundeRepository.delete(kunde);
    }

    @Override
    public Kunde getKundeById(long id) {
        Optional<Kunde> result = kundeRepository.findById(id);

        Kunde kunde = null;

        if (result.isPresent())
            kunde = result.get();

        return kunde;
    }

    @Override
    public Kunde getKundeByKonto(Konto konto) {
        Optional<Kunde> result = kundeRepository.findByKonto(konto);

        Kunde kunde = null;

        if (result.isPresent())
            kunde = result.get();

        return kunde;
    }

    @Override
    public UserDetails loadUserByUsername(String kontonummer) throws UsernameNotFoundException {
        Kunde tempKunde = getKundeByKonto(kontoService.findenByKontonummer(kontonummer));
        return tempKunde;
    }
}
