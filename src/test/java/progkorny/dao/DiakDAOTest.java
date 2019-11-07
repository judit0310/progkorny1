package progkorny.dao;

import exceptions.InvalidDate;
import exceptions.TooYoung;
import org.junit.Test;
import progkorny.dao.DiakDAO;
import progkorny.model.Diak;
import progkorny.model.Nem;

import java.time.LocalDate;

public class DiakDAOTest {

    @Test
    public void readAllDiak() throws TooYoung, InvalidDate {
        DiakDAO dao = new DiakDAO("kiscica.json");
        Diak diak = new Diak();
        diak.setNeptun_kod("AAA132");
        diak.setNev("Kiss Károly");
        diak.setBeiratkozas_eve(LocalDate.of(2018,9,4));
        diak.setKor(20);
        diak.setKreditek_szama(10);
        diak.setNem(Nem.EGYEB);
        dao.addDiak(diak);

    }
}