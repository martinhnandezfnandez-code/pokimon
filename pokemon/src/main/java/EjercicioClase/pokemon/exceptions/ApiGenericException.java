package EjercicioClase.pokemon.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiGenericException extends GenericException {

    public ApiGenericException(String mensaje, Integer codigo) {
        super(mensaje, codigo);

    }

}

