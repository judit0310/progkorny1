import java.time.LocalDate;

public class Diak {

    private String nev;
    private int kor;
    private Nem nem;
    private LocalDate beiratkozas_eve;
    private int kreditek_szama;

    public Diak(){

    }


    public Diak(String nev, int kor, Nem nem, LocalDate beiratkozas_eve, int kreditek_szama) {
        this.nev = nev;
        this.kor = kor;
        this.nem = nem;
        this.beiratkozas_eve = beiratkozas_eve;
        this.kreditek_szama = kreditek_szama;
    }

    public void setNev(String nev){
        this.nev=nev;
    }

    public String getNev() {
        return nev;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public Nem getNem() {
        return nem;
    }

    public void setNem(Nem nem) {
        this.nem = nem;
    }

    public LocalDate getBeiratkozas_eve() {
        return beiratkozas_eve;
    }

    public void setBeiratkozas_eve(LocalDate beiratkozas_eve) {
        this.beiratkozas_eve = beiratkozas_eve;
    }

    public int getKreditek_szama() {
        return kreditek_szama;
    }

    public void setKreditek_szama(int kreditek_szama) {
        this.kreditek_szama = kreditek_szama;
    }

    @Override
    public String toString() {
        return "Diak{" +
                "nev='" + nev + '\'' +
                ", kor=" + kor +
                ", nem=" + nem +
                ", beiratkozas_eve=" + beiratkozas_eve +
                ", kreditek_szama=" + kreditek_szama +
                '}';
    }
}
