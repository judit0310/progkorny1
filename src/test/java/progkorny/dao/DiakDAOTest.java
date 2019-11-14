package progkorny.dao;

import exceptions.EzADiakMarSzerepel;
import exceptions.InvalidDate;
import exceptions.TooYoung;
import org.junit.*;
import progkorny.dao.DiakDAO;
import progkorny.model.Diak;
import progkorny.model.Nem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DiakDAOTest {
    IDiakDAO dao;
    static String file ="kiscica.json";

    @BeforeClass
    public static void setUp(){
        File jsonfile = new File(file);
        if (jsonfile.exists()){
            jsonfile.delete();
        }

    }

    @Before
    public void populate() throws TooYoung, InvalidDate, EzADiakMarSzerepel {
        dao= new DiakDAO(file);
        Diak diak = new Diak();
        diak.setNeptun_kod("AAA132");
        diak.setNev("Kiss Károly");
        diak.setKor(25);
        diak.setBeiratkozas_eve(LocalDate.of(2012,9,4));
        diak.setKreditek_szama(10);
        diak.setNem(Nem.EGYEB);
        dao.addDiak(diak);
        Diak diak2 = new Diak();
        diak2.setNeptun_kod("BBB111");
        diak2.setNev("Nagy Emese");
        diak2.setKor(25);
        diak2.setBeiratkozas_eve(LocalDate.of(2013,9,4));
        diak2.setKreditek_szama(7);
        diak2.setNem(Nem.NO);
        dao.addDiak(diak2);

    }

    @Test(expected = EzADiakMarSzerepel.class)
    public void readAllDiak() throws TooYoung, InvalidDate, EzADiakMarSzerepel {
        Diak diak = new Diak();
        diak.setNeptun_kod("AAA132");
        diak.setNev("Kiss Károly");
        diak.setKor(25);
        diak.setBeiratkozas_eve(LocalDate.of(2012,9,4));
        diak.setKreditek_szama(10);
        diak.setNem(Nem.EGYEB);
        dao.addDiak(diak);
    }
    @Test(expected = TooYoung.class)
    public void testTulFiatal() throws TooYoung, InvalidDate {
        Diak diak = new Diak();
        diak.setNeptun_kod("AAA132");
        diak.setNev("Kiss Károly");
        diak.setKor(12);
        diak.setBeiratkozas_eve(LocalDate.of(2019,9,4));
    }

    @Test(expected = InvalidDate.class)
    public void testFuture() throws TooYoung, InvalidDate {
        Diak diak = new Diak();
        diak.setKor(12);
        diak.setBeiratkozas_eve(LocalDate.of(2029,9,4));
    }

    @Test
    public void fileCorrupted() throws TooYoung, InvalidDate, EzADiakMarSzerepel, IOException {
        File fileobj = new File(file);
        if(fileobj.exists()){
        fileobj.setReadable(false);}
        Assert.assertEquals(0, dao.readAllDiak().size());
        FileWriter writer = new FileWriter(fileobj);
        writer.write("CICA]");
        writer.close();
        fileobj.setReadable(true);
        Assert.assertEquals(0, dao.readAllDiak().size());

    }

    @After
    public void tearDown(){
        File jsonfile = new File(file);
        if (jsonfile.exists()){
            jsonfile.delete();
        }
    }
}