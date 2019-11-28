package progkorny.service;

import exceptions.DiakNotFound;
import exceptions.EzADiakMarSzerepel;
import progkorny.dao.IDiakDAO;
import progkorny.model.Diak;

import java.util.ArrayList;
import java.util.Collection;

public class DiakService {
    IDiakDAO diakDAO;

    public DiakService(IDiakDAO diakDAO) {
        this.diakDAO = diakDAO;
    }

    public void addDiak(Diak diak) throws EzADiakMarSzerepel {
        diakDAO.addDiak(diak);
    }

    public Diak readDiakByNeptunKod(String neptun_kod) throws DiakNotFound {
        return diakDAO.readDiakByNeptunKod(neptun_kod);
    }

    public Collection<Diak> readAllDiak() {
        return diakDAO.readAllDiak();
    }

    public Collection<Diak> readDelayedDiak(int kepzes_hossza) {
        Collection<Diak> result = new ArrayList<>();
        Collection<Diak> diakok = diakDAO.readAllDiak();
        for (Diak d : diakok) {
            if (d.csuszottE(kepzes_hossza)) {
                result.add(d);
            }

        }
        return result;
    }
}
