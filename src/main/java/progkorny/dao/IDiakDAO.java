package progkorny.dao;

import exceptions.DiakNotFound;
import exceptions.EzADiakMarSzerepel;
import progkorny.model.Diak;

import java.util.Collection;

public interface IDiakDAO {

    void addDiak(Diak diak) throws EzADiakMarSzerepel;
    Diak readDiakByNeptunKod(String neptun_kod) throws DiakNotFound;
    Collection<Diak> readAllDiak();
}
