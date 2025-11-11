package EjercicioClase.pokemon.exceptions;

public class GenericException extends Exception {
    public static final int ERROR_GENERAL = 0;
    public static final int ERROR_OBTENCION_POKEMON = 1;
    public static final int ERROR_OBTENCION_HABILIDAD = 2;
    public static final int ERROR_OBTENCION_TIPO = 3;
    public static final int ERROR_LECTURA_JSON = 4;
    protected String mensaje;
    protected String servicio;

    public GenericException(String mensaje, Integer codigo) {
        super(mensaje);
        this.mensaje = mensaje;

        switch (codigo) {
            case ERROR_GENERAL:
                servicio = "ERR00 - Error General";
                break;
            case ERROR_OBTENCION_POKEMON:
                servicio = "ERR01 - Error Obtención Pokemon";
                break;
            case ERROR_OBTENCION_HABILIDAD:
                servicio = "ERR02 - Error Obtención Habilidad";
                break;
            case ERROR_OBTENCION_TIPO:
                servicio = "ERR03 - Error Obtención Tipo";
                break;
            case ERROR_LECTURA_JSON:
                servicio= "ERR04 - Error Lectura Json";
                break;
        }
    }

    public String print() {
        return servicio + " - " + mensaje;
    }

}
