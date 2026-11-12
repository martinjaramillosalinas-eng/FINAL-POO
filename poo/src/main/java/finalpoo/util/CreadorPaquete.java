package finalpoo.util;

import finalpoo.excepciones.EnvioException;
import finalpoo.modelo.Paquete;

@FunctionalInterface
public interface CreadorPaquete {

    Paquete crear() throws EnvioException;
}
