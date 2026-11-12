package finalpoo.excepciones;

public class GuiaDuplicadaException extends EnvioException {

    public GuiaDuplicadaException(String guia, String despacho) {
        super("El despacho " + despacho + " ya tiene un paquete con la guía " + guia);
    }
}
