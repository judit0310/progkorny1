import org.junit.Test;
import progkorny.dao.DiakDAO;

public class DiakDAOTest {

    @Test
    public void readAllDiak() {
        DiakDAO dao = new DiakDAO("kiscica.json");
    }
}