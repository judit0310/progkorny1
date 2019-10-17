import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import progkorny.model.Diak;
import progkorny.model.Nem;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class DiakTest {
    @Test
    public void testEmptyConstructor(){
        Diak ujDiak = new Diak();
        System.out.println(ujDiak);
    }

    @Test
    public void testFullConstructor() throws Exception {
        Diak ujDiak = new Diak("ABCDER","Kiss János",
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

    @Test
    public void tombTest(){
        String[] tomb= new String[5];
        for(int i=0;i<tomb.length;i++){
            tomb[i]=String.valueOf(i);
        }
        System.out.println(tomb);
        for(int i =0; i<tomb.length;i++){
            System.out.println(tomb[i]);
        }

    }

    @Test
    public void listTest(){
        List<String> lista;
        lista=new ArrayList<>();
        for(int i=0; i < 10; i++){
            lista.add(i, i+". cica");
        }
        for(int i=lista.size()-1; i >= 0; i--){
            System.out.println(lista.get(i));
        }
        System.out.println(lista.contains("3. cica"));
        System.out.println(lista.indexOf("6. cica"));

        for(String s: lista){
            System.out.println(s);
        }
        List ujlista =lista.subList(2, 4);
        System.out.println(ujlista);

        lista.set(8, "KACSA");
        System.out.println(lista);
    }

    @Test
    public void halmazTest(){
        Set<String> halmaz = new HashSet<>(10);
        halmaz.add("1");
        halmaz.add("KISCICA");
        for (int i =0; i< 10;i++){
            halmaz.add("i. 123");
            halmaz.add("i. 123");
        }
        System.out.println(halmaz);

    }

    @Test
    public void jsonTest(){
        File file;
        file=new File(".idea");
        if(file.exists()){
            System.out.println("CICA");
        }
        else{
            try {
                System.out.println(file.mkdir());
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(file.isDirectory());
        List<File> files =Arrays.asList(file.listFiles());
        for(File f: files){
            System.out.println(f.getName());
        }
        System.out.println(file.listFiles());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getName());

        File kiiro = new File("kiscica.json");
        if(!kiiro.exists()){
            try {
                kiiro.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        Collection<Diak> diakok = new ArrayList<>();
        Diak bela = new Diak();
        bela.setNev("KISS BELA");
        diakok.add(bela);
        try {
            mapper.writeValue(kiiro, diakok);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}