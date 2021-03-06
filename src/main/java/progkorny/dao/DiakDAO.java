package progkorny.dao;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import exceptions.DiakNotFound;
import exceptions.EzADiakMarSzerepel;
import org.apache.log4j.Logger;
import progkorny.model.Diak;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class DiakDAO implements IDiakDAO{

    File jsonFile;
    ObjectMapper mapper;
    TypeReference listReference;
    private Logger logger = Logger.getLogger(DiakDAO.class);

    public DiakDAO(String jsonFilepath) {
        jsonFile= new File(jsonFilepath);
        if(!jsonFile.exists()){
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.close();
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
        } catch (JsonParseException e) {
            e.printStackTrace();
        }catch (IOException e) {
                e.printStackTrace();
            }
        logger.info(result.size()+" diak van az adatbazisban");
        return result;
    }

    public Diak readDiakByNeptunKod(String neptun_kod) throws DiakNotFound {
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
        throw new DiakNotFound(neptun_kod);

    }

    public void addDiak(Diak diak) throws EzADiakMarSzerepel {
        try {
            readDiakByNeptunKod(diak.getNeptun_kod());
            throw new EzADiakMarSzerepel(diak);
        } catch (DiakNotFound diakNotFound) {
            Collection<Diak> result = readAllDiak();
            result.add(diak);
            try {
                mapper.writeValue(jsonFile, result);
                logger.info(diak+" hozzaadva, igy a diakok szama:"+result.size());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }

}
