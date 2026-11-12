package finalpoo;

import finalpoo.excepciones.EnvioException;
import finalpoo.modelo.Despacho;
import finalpoo.modelo.Paquete;
import finalpoo.modelo.PaqueteEstandar;
import finalpoo.modelo.PaqueteExpress;
import finalpoo.modelo.PaqueteFragil;
import finalpoo.util.CreadorPaquete;

import static finalpoo.modelo.Zona.CENTRO;
import static finalpoo.modelo.Zona.NORTE;
import static finalpoo.modelo.Zona.ORIENTE;
import static finalpoo.modelo.Zona.SUR;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== Caso 1 =====");
        Despacho andres = new Despacho("Andrés");
        registrar(andres, () -> new PaqueteEstandar("EY2001", 5, ORIENTE));
        registrar(andres, () -> new PaqueteExpress("EY2002", 1.5, NORTE));
        registrar(andres, () -> new PaqueteFragil("EY2003", 2, CENTRO, "vajilla"));
        registrar(andres, () -> new PaqueteEstandar("EY2004", 8, CENTRO));
        registrar(andres, () -> new PaqueteExpress("EY2005", 6, ORIENTE));
        registrar(andres, () -> new PaqueteFragil("EY2006", 3.5, SUR, "televisor"));
        System.out.println(andres.manifiesto());

        System.out.println("\n===== Caso 2 =====");
        crear(() -> new PaqueteEstandar("EY3001", -2, CENTRO));
        crear(() -> new PaqueteFragil("EY3002", 2, NORTE, ""));
        crear(() -> new PaqueteEstandar("EY12A4", 3, SUR));
        crear(() -> new PaqueteExpress("EY3003", 22, NORTE));

        System.out.println("\n===== Caso 3 =====");
        Despacho camila = new Despacho("Camila");
        registrar(camila, () -> new PaqueteEstandar("EY4001", 12, CENTRO));
        registrar(camila, () -> new PaqueteExpress("EY4002", 10, SUR));
        registrar(camila, () -> new PaqueteFragil("EY4003", 9, NORTE, "espejo"));
        registrar(camila, () -> new PaqueteEstandar("EY4004", 8, ORIENTE));
        System.out.println(camila.manifiesto());

        System.out.println("\n===== Caso 4 =====");
        Despacho mateo = new Despacho("Mateo");
        registrar(mateo, () -> new PaqueteFragil("EY5001", 3, SUR, "lámpara"));
        registrar(mateo, () -> new PaqueteEstandar("EY5001", 1, CENTRO));
        registrar(mateo, () -> new PaqueteExpress("EY5002", 2, CENTRO));
        System.out.println(mateo.manifiesto());
    }

    private static void registrar(Despacho despacho, CreadorPaquete creador) {
        try {
            Paquete paquete = creador.crear();
            despacho.agregar(paquete);
            System.out.println("Paquete " + paquete.getGuia() + " registrado en " + despacho.getCodigo());
        } catch (EnvioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crear(CreadorPaquete creador) {
        try {
            System.out.println("Paquete " + creador.crear().getGuia() + " creado");
        } catch (EnvioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
