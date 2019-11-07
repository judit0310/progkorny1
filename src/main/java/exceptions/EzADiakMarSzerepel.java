package exceptions;

import progkorny.model.Diak;

public class EzADiakMarSzerepel extends Throwable {
    public EzADiakMarSzerepel(Diak diak) {
        super(diak.toString());
    }
}
