package exceptions;

public class DiakNotFound extends Throwable {
    public DiakNotFound(String neptun_kod) {
        super(neptun_kod);
    }
}
