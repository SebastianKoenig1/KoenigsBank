package com.OTH.KoenigsBank;

import com.OTH.KoenigsBank.entity.Adresse;
import com.OTH.KoenigsBank.entity.Konto;
import com.OTH.KoenigsBank.entity.Kunde;
import com.OTH.KoenigsBank.entity.Tan;
import com.OTH.KoenigsBank.service.KontoService;
import com.OTH.KoenigsBank.service.KundeService;
import com.OTH.KoenigsBank.service.TanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  KoenigsBankApplication implements CommandLineRunner {

	@Autowired
	private TanService tanService;

	@Autowired
	private KundeService kundeService;

	@Autowired
	private KontoService kontoService;

	public static void main(String[] args) {
		SpringApplication.run(KoenigsBankApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// Partner anlegen
		Adresse payPalA = new Adresse("Boulevard Royal", "22-24", "L-2449", "Luxembourg");
		Konto payPalK = new Konto("0000000001", 100000000.00);
		Kunde payPalKu = new Kunde("Sean", "Byrne", "Sean.Byrne@web.com", payPalA, "diesesPasswortIstNichtSicher", payPalK);
		kundeService.registrieren(payPalKu);
		tanService.erzeugen(payPalK);

		// Testpersonen anlegen
		Adresse a = new Adresse("Landshuterstraße", "21 A", "93053", "Regensburg");
		Adresse b = new Adresse("Münchnerstraße", "233", "93054", "Regensburg");
		Konto aKo = new Konto("0000000005", 10000.00);
		Konto bKo = new Konto("0000000006", 10000.00);
		Kunde aKu = new Kunde("Hans", "Maier", "Hans.Maier@web.de", a, "test", aKo);
		Kunde bKu = new Kunde("Johann", "Huber", "Huber.Johann78@hotmail.com", a, "test2", bKo);

		kundeService.registrieren(aKu);
		kundeService.registrieren(bKu);
		tanService.erzeugen(aKu.getKonto());
		tanService.erzeugen(bKu.getKonto());
		tanService.erzeugen(aKu.getKonto());
		tanService.erzeugen(aKu.getKonto());
		//System.out.println("Tans von A" + tanService.getAllTansVonKonto(kontoService.findenByKontonummer(aKo.getKontonummer())));
	}
}
