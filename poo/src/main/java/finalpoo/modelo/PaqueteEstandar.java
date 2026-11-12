package finalpoo.modelo;

import finalpoo.excepciones.GuiaInvalidaException;
import finalpoo.excepciones.PesoInvalidoException;

public class PaqueteEstandar extends Paquete {

    public PaqueteEstandar(String guia, double peso, Zona zona) throws GuiaInvalidaException, PesoInvalidoException {
        super(guia, peso, zona);
    }

    @Override
    protected double aplicarAjuste(double base) {
        return base;
    }

    @Override
    public String getServicio() {
        return "Estándar";
    }

    @Override
    public String getAjuste() {
        return "—";
    }
}
