package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Tipos {
    private String tipoPrimario, tipoSecundario;

    public Tipos() {
        super();
    }


    @Override
    public String toString() {
        String response = "\ttipoPrimario= " + tipoPrimario + '\n';

        if (tipoSecundario!=null){
            response +=  "\ttipoSegundario= " + tipoSecundario + '\'';
        }
        return response;
    }
}
