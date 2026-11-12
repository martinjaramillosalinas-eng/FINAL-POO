package finalpoo.excepciones;

public class GuiaInvalidaException extends EnvioException {

    public GuiaInvalidaException(String guia) {
        super("La guía " + guia + " no es válida, debe ser EY seguida de cuatro dígitos");
    }
}
