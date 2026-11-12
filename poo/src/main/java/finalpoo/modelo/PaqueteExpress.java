package finalpoo.modelo;

import finalpoo.excepciones.GuiaInvalidaException;
import finalpoo.excepciones.PesoInvalidoException;

public class PaqueteExpress extends Paquete {

    public PaqueteExpress(String guia, double peso, Zona zona) throws GuiaInvalidaException, PesoInvalidoException {
        super(guia, peso, zona);
    }

    @Override
    protected double aplicarAjuste(double base) {
        return base * 1.5;
    }

    @Override
    public String getServicio() {
        return "Express";
    }

    @Override
    public String getAjuste() {
        return "+50%";
    }

    @Override
    public int getPrioridad() {
        return 0;
    }
}
