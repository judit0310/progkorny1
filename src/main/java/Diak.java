import exceptions.InvalidDate;
import exceptions.TooYoung;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Diak {
    protected static Map<String, Integer> kepzesek;
    static{
        kepzesek=new HashMap();
        kepzesek.put("Progterv", 6);
        kepzesek.put("Gazdinfo", 6);
        kepzesek.put("Borasz", 7);
    }
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

    public void setBeiratkozas_eve(LocalDate beiratkozas_eve)
            throws InvalidDate, TooYoung {
        if(beiratkozas_eve.isAfter(LocalDate.now())){
            throw new InvalidDate();
        }
        if((LocalDate.now().minusYears(kor)).isBefore(LocalDate.now().minusYears(17))){
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
                "nev='" + nev + '\'' +
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
}
