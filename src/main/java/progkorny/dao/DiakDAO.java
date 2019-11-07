package progkorny.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import progkorny.model.Diak;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class DiakDAO {

    File jsonFile;
    ObjectMapper mapper;
    TypeReference listReference;
    private Logger logger = Logger.getLogger(DiakDAO.class);

    public DiakDAO(String jsonFilepath) {
        jsonFile= new File(jsonFilepath);
        if(!jsonFile.exists()){
            try {
                jsonFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        listReference = new TypeReference<ArrayList<Diak>>(){};
        logger.debug("DAO initialized");
        logger.info("CICCCA");
    }

    public Collection<Diak> readAllDiak(){
        Collection<Diak> result = new ArrayList();
        try {
            result = mapper.readValue(jsonFile, listReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(result.size()+" diak van az adatbazisban");
        return result;
    }

    public Diak readDiakByNeptunKod(String neptun_kod){
        Collection<Diak> diakok = new ArrayList();
        try {
            diakok = mapper.readValue(jsonFile, listReference);
            for(Diak d: diakok){
                if (d.getNeptun_kod().equalsIgnoreCase(neptun_kod)){
                    return d;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addDiak(Diak diak){
        Collection<Diak> result = new ArrayList();
        try {
            result = mapper.readValue(jsonFile, listReference);
            result.add(diak);
            mapper.writeValue(jsonFile, result);
            logger.info(diak+" hozzaadva, igy a diakok szama:"+result.size());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
