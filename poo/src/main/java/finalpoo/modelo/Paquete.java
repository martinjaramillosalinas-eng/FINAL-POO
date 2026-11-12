package finalpoo.modelo;

import finalpoo.excepciones.GuiaInvalidaException;
import finalpoo.excepciones.PesoInvalidoException;
import finalpoo.util.Utilidades;

import java.util.Comparator;
import java.util.Objects;

public abstract class Paquete implements Comparable<Paquete> {

    private static final Comparator<Paquete> ORDEN_ENTREGA = Comparator
            .comparingInt(Paquete::getPrioridad)
            .thenComparing(Paquete::getZona)
            .thenComparing(Paquete::getGuia);

    private final String guia;
    private final double peso;
    private final Zona zona;

    protected Paquete(String guia, double peso, Zona zona) throws GuiaInvalidaException, PesoInvalidoException {
        if (guia == null || !guia.matches("EY\\d{4}")) {
            throw new GuiaInvalidaException(guia);
        }
        if (peso <= 0 || peso > 20) {
            throw new PesoInvalidoException(guia, peso);
        }
        this.guia = guia;
        this.peso = peso;
        this.zona = zona;
    }

    protected abstract double aplicarAjuste(double base);

    public abstract String getServicio();

    public abstract String getAjuste();

    public int getPrioridad() {
        return 1;
    }

    public double getCostoBase() {
        return zona.getTarifa() + 1000 * peso;
    }

    public double getCostoEnvio() {
        return aplicarAjuste(getCostoBase());
    }

    public String getGuia() {
        return guia;
    }

    public double getPeso() {
        return peso;
    }

    public Zona getZona() {
        return zona;
    }

    @Override
    public int compareTo(Paquete otro) {
        return ORDEN_ENTREGA.compare(this, otro);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Paquete otro && guia.equals(otro.guia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guia);
    }

    @Override
    public String toString() {
        return String.format("%-8s %-22s %-9s %-9s %-11s %-9s %s", guia, getServicio(), zona.getNombre(),
                Utilidades.kilos(peso), Utilidades.pesos(getCostoBase()), getAjuste(), Utilidades.pesos(getCostoEnvio()));
    }
}
