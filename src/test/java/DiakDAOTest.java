import org.junit.Test;

import static org.junit.Assert.*;

public class DiakDAOTest {

    @Test
    public void readAllDiak() {
        DiakDAO dao = new DiakDAO("kiscica.json");
    }
}