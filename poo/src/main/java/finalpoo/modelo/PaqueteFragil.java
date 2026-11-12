package finalpoo.modelo;

import finalpoo.excepciones.ContenidoRequeridoException;
import finalpoo.excepciones.GuiaInvalidaException;
import finalpoo.excepciones.PesoInvalidoException;

public class PaqueteFragil extends Paquete implements ManejoEspecial {

    private final String contenido;

    public PaqueteFragil(String guia, double peso, Zona zona, String contenido)
            throws GuiaInvalidaException, PesoInvalidoException, ContenidoRequeridoException {
        super(guia, peso, zona);
        if (contenido == null || contenido.isBlank()) {
            throw new ContenidoRequeridoException(guia);
        }
        this.contenido = contenido;
    }

    @Override
    protected double aplicarAjuste(double base) {
        return base + 5000;
    }

    @Override
    public String getServicio() {
        return "Frágil (" + contenido + ")";
    }

    @Override
    public String getAjuste() {
        return "+5.000";
    }

    @Override
    public String getAdvertencia() {
        return getGuia() + " · FRÁGIL (" + contenido + ")";
    }
}
