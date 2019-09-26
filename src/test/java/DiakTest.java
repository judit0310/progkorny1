import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;

import static org.junit.Assert.*;

public class DiakTest {
    @Test
    public void testEmptyConstructor(){
        Diak ujDiak = new Diak();
        System.out.println(ujDiak);
    }

    @Test
    public void testFullConstructor() throws Exception {
        Diak ujDiak = new Diak("Kiss János",
                24, Nem.FERFI,
                LocalDate.now(),
                0);
        System.out.println(ujDiak);
    }

//    @Test
//    public void testLocalDate(){
//        System.out.println(LocalDate.now());
//        System.out.println(LocalDate.of(2012,05,11));
//        System.out.println(LocalDate.MIN);
//        System.out.println(LocalDate.MAX);
//        LocalDate most = LocalDate.now();
//        LocalDate datum = LocalDate.parse("2010-05-05");
//        System.out.println(datum);
//        System.out.println(most.isBefore(datum));
//        System.out.println(most.isAfter(datum));
//        System.out.println(datum.plusMonths(3));
//        System.out.println(datum.minusYears(3));
//        datum = datum.minusYears(2);
//        System.out.println(datum);
//        System.out.println(most.compareTo(datum));
//        System.out.println(datum.compareTo(most));
//        System.out.println(datum.getYear());
//        System.out.println(datum.getDayOfWeek());
//        System.out.println(datum.getDayOfMonth());
//        System.out.println(datum.getDayOfYear());
//        System.out.println(datum.isLeapYear());
//        System.out.println(LocalDate.now(ZoneId.of("America/Asuncion")));
//        System.out.println(datum.until(most));
//
//    }

    @Test
    public void testMap(){
        System.out.println(Diak.kepzesek.values());
        System.out.println(Diak.kepzesek.keySet());
        Set<String> keys = Diak.kepzesek.keySet();
        for(String k: keys){
            System.out.println(k+": "+Diak.kepzesek.get(k));

        }
    }

}