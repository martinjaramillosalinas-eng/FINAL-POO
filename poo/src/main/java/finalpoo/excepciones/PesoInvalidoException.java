package finalpoo.excepciones;

import finalpoo.util.Utilidades;

public class PesoInvalidoException extends EnvioException {

    public PesoInvalidoException(String guia, double peso) {
        super("El paquete " + guia + " pesa " + Utilidades.kilos(peso) + ", debe ser mayor a 0 kg y máximo 20 kg");
    }
}
