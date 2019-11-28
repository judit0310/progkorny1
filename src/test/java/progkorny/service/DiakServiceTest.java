package progkorny.service;

import exceptions.DiakNotFound;
import exceptions.EzADiakMarSzerepel;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import progkorny.dao.IDiakDAO;
import progkorny.model.Diak;
import progkorny.model.Nem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class DiakServiceTest {
    static DiakService service;

    @Before
    public void setUp() throws Exception, DiakNotFound, EzADiakMarSzerepel {
        IDiakDAO mock = Mockito.mock(IDiakDAO.class);
        service = new DiakService(mock);
        Diak diak = new Diak("ABCDER", "Kiss János",
                24, Nem.FERFI,
                LocalDate.of(2012,9,11),
                0);
        Diak diak2 = new Diak("123456", "Nagy Rózsa",
                23, Nem.NO,
                LocalDate.now(),
                0);
        Collection<Diak> diakok = new ArrayList<>();
        diakok.add(diak);
        diakok.add(diak2);
        Mockito.when(mock.readAllDiak()).thenReturn(diakok);
        Mockito.when(mock.readDiakByNeptunKod("ABCDER")).thenReturn(diak);
        Mockito.when(mock.readDiakByNeptunKod("AA")).thenThrow(DiakNotFound.class);
        Mockito.doThrow(EzADiakMarSzerepel.class).when(mock).addDiak(diak);
    }

    @Test
    public void testMockObject(){
        System.out.println(service.readAllDiak());
        System.out.println(service.readAllDiak());
    }

    @Test
    public void testReadDiakByNeptunKod() throws DiakNotFound {
        System.out.println(service.readDiakByNeptunKod("ABCDER"));
    }

    @Test
    public void testDelayed() throws DiakNotFound {
        Assert.assertEquals(1, service.readDelayedDiak(6).size());
        Assert.assertTrue(service.readDelayedDiak(6).contains(service.readDiakByNeptunKod("ABCDER")));
    }

    @Test(expected = DiakNotFound.class)
    public void testDiakNotFound() throws DiakNotFound {
        service.readDiakByNeptunKod("AA");
    }

    @Test(expected = EzADiakMarSzerepel.class)
    public void testDuplum() throws EzADiakMarSzerepel {
        Diak diak = new Diak("ABCDER", "Kiss János",
                24, Nem.FERFI,
                LocalDate.of(2012,9,11),
                0);
        service.addDiak(diak);
    }

    @AfterClass
    public static void test() throws DiakNotFound {
     //   Mockito.verify(service.diakDAO,Mockito.times(2)).readAllDiak();
     //   Mockito.verify(service.diakDAO,Mockito.never()).readDiakByNeptunKod("ABC123");
     //   Mockito.verify(service.diakDAO,Mockito.atLeastOnce()).readAllDiak();
    }
}