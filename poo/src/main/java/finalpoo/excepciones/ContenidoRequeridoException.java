package finalpoo.excepciones;

public class ContenidoRequeridoException extends EnvioException {

    public ContenidoRequeridoException(String guia) {
        super("El paquete frágil " + guia + " debe indicar qué contiene");
    }
}
