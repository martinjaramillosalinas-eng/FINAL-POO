package finalpoo.excepciones;

import finalpoo.util.Utilidades;

public class CapacidadExcedidaException extends EnvioException {

    public CapacidadExcedidaException(String guia, String despacho, double exceso) {
        super("El paquete " + guia + " no se sube al despacho " + despacho + ", supera los 30 kg por " + Utilidades.kilos(exceso));
    }
}
