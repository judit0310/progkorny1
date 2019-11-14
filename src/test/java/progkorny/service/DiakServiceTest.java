package progkorny.service;

import exceptions.DiakNotFound;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
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
    public void setUp() throws Exception {
        IDiakDAO mock = Mockito.mock(IDiakDAO.class);
        service = new DiakService(mock);
        Diak diak = new Diak("ABCDER", "Kiss János",
                24, Nem.FERFI,
                LocalDate.now(),
                0);
        Diak diak2 = new Diak("123456", "Nagy Rózsa",
                23, Nem.NO,
                LocalDate.now(),
                0);
        Collection<Diak> diakok = new ArrayList<>();
        diakok.add(diak);
        diakok.add(diak2);
        Mockito.when(mock.readAllDiak()).thenReturn(diakok);
    }

    @Test
    public void testMockObject(){
        System.out.println(service.readAllDiak());
        System.out.println(service.readAllDiak());
    }

    @AfterClass
    public static void test() throws DiakNotFound {
        Mockito.verify(service.diakDAO,Mockito.times(2)).readAllDiak();
        Mockito.verify(service.diakDAO,Mockito.never()).readDiakByNeptunKod("ABC123");
        Mockito.verify(service.diakDAO,Mockito.atLeastOnce()).readAllDiak();
    }
}