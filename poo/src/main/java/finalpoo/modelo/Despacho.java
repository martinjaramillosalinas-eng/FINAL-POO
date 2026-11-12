package finalpoo.modelo;

import finalpoo.excepciones.CapacidadExcedidaException;
import finalpoo.excepciones.GuiaDuplicadaException;
import finalpoo.util.Utilidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Despacho {

    private static int consecutivo = 0;

    private final String codigo;
    private final String mensajero;
    private final List<Paquete> paquetes = new ArrayList<>();

    public Despacho(String mensajero) {
        this.codigo = "D-" + (++consecutivo);
        this.mensajero = mensajero;
    }

    public void agregar(Paquete paquete) throws GuiaDuplicadaException, CapacidadExcedidaException {
        if (paquetes.contains(paquete)) {
            throw new GuiaDuplicadaException(paquete.getGuia(), codigo);
        }
        double nuevoPeso = getPesoTotal() + paquete.getPeso();
        if (nuevoPeso > 30) {
            throw new CapacidadExcedidaException(paquete.getGuia(), codigo, nuevoPeso - 30);
        }
        paquetes.add(paquete);
    }

    public double getPesoTotal() {
        return paquetes.stream().mapToDouble(Paquete::getPeso).sum();
    }

    public double getCostoTotal() {
        return paquetes.stream().mapToDouble(Paquete::getCostoEnvio).sum();
    }

    public String getCodigo() {
        return codigo;
    }

    public String manifiesto() {
        List<Paquete> orden = new ArrayList<>(paquetes);
        Collections.sort(orden);
        List<String> advertencias = new ArrayList<>();
        StringBuilder texto = new StringBuilder(this + "\n");
        texto.append(String.format("%-8s %-22s %-9s %-9s %-11s %-9s %s%n",
                "Guía", "Servicio", "Zona", "Peso", "Costo base", "Ajuste", "Costo envío"));
        for (Paquete paquete : orden) {
            texto.append(paquete).append("\n");
            if (paquete instanceof ManejoEspecial) {
                advertencias.add(((ManejoEspecial) paquete).getAdvertencia());
            }
        }
        return texto.append("Peso total: ").append(Utilidades.kilos(getPesoTotal()))
                .append(" · Costo total del despacho: $").append(Utilidades.pesos(getCostoTotal()))
                .append("\nAdvertencias: ").append(advertencias.isEmpty() ? "ninguna" : String.join(", ", advertencias))
                .toString();
    }

    @Override
    public String toString() {
        return "Despacho " + codigo + " – Mensajero: " + mensajero;
    }
}
