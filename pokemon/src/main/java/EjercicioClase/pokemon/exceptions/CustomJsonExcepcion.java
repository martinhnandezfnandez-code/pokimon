package EjercicioClase.pokemon.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomJsonExcepcion extends GenericException {
    public static final int ERROR_POKEMON = 1;
    public static final int ERROR_HABILIDAD = 2;
    public static final int ERROR_TIPO = 3;
    private String mensajeErrorJson;

    public CustomJsonExcepcion(String message, Integer codigo, Integer codigoErrorJson) {
        super(message, codigo);
        switch (codigoErrorJson) {
            case ERROR_POKEMON:
                mensajeErrorJson = " - Pokemon";
                break;
            case ERROR_HABILIDAD:
                mensajeErrorJson = " - Habilidad";
                break;
            case ERROR_TIPO:
                mensajeErrorJson = " - Tipo";
                break;
        }
    }
    @Override
    public String print() {
        return servicio + mensajeErrorJson + " - " + mensaje ;
    }
}

