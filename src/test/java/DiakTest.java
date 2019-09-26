import org.junit.Test;

import java.time.LocalDate;

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

}