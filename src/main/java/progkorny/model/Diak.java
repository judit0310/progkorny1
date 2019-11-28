package progkorny.model;

import exceptions.InvalidDate;
import exceptions.TooYoung;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Diak {
    private Logger logger= Logger.getLogger(Diak.class);
    protected static Map<String, Integer> kepzesek;
    static{
        kepzesek=new HashMap();
        kepzesek.put("Progterv", 6);
        kepzesek.put("Gazdinfo", 6);
        kepzesek.put("Borasz", 7);
    }
    private String neptun_kod;
    private String nev;
    private int kor;
    private Nem nem;
    private LocalDate beiratkozas_eve;
    private int kreditek_szama;

    public Diak(){

    }


    public Diak(String neptun_kod, String nev, int kor, Nem nem, LocalDate beiratkozas_eve, int kreditek_szama) {
        this.neptun_kod=neptun_kod;
        this.nev = nev;
        this.kor = kor;
        this.nem = nem;
        this.beiratkozas_eve = beiratkozas_eve;
        this.kreditek_szama = kreditek_szama;
        logger.info("Uj Diak objektum:" +this);
    }

    public String getNeptun_kod() {
        return neptun_kod;
    }

    public void setNeptun_kod(String neptun_kod) {
        this.neptun_kod = neptun_kod;
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

    public void setBeiratkozas_eve(LocalDate beiratkozas_eve)
            throws InvalidDate, TooYoung {
        if(beiratkozas_eve.isAfter(LocalDate.now())){
            throw new InvalidDate();
        }
        System.out.println((LocalDate.now().minusYears(kor)));
        System.out.println(LocalDate.now().minusYears(17));
        if(!(LocalDate.now().minusYears(kor)).isBefore(LocalDate.now().minusYears(17))){
            throw new TooYoung();
        }
        this.beiratkozas_eve=beiratkozas_eve;

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
                "neptun_kod='" + neptun_kod + '\'' +
                ", nev='" + nev + '\'' +
                ", kor=" + kor +
                ", nem=" + nem +
                ", beiratkozas_eve=" + beiratkozas_eve +
                ", kreditek_szama=" + kreditek_szama +
                '}';
    }

    public boolean csuszottE(int kepzes_feleveinek_szama){
        int elhasznalt_felevek =
                Math.abs(LocalDate.now().compareTo(beiratkozas_eve) *2);
        if(elhasznalt_felevek>kepzes_feleveinek_szama){
            return true;
        }
        return false;
    }

    public int teljesitettsegAranya(String kepzesneve){
        int kepzes_feleveinek_szama =(int) kepzesek.get(kepzesneve);
        int osszes = kepzes_feleveinek_szama*30;
        double arany = kreditek_szama/osszes;
        return (int) arany*100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diak diak = (Diak) o;
        return kor == diak.kor &&
                kreditek_szama == diak.kreditek_szama &&
                Objects.equals(logger, diak.logger) &&
                Objects.equals(neptun_kod, diak.neptun_kod) &&
                Objects.equals(nev, diak.nev) &&
                nem == diak.nem &&
                Objects.equals(beiratkozas_eve, diak.beiratkozas_eve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logger, neptun_kod, nev, kor, nem, beiratkozas_eve, kreditek_szama);
    }
}
