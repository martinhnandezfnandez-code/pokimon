package EjercicioClase.pokemon.entities;


import lombok.Getter;
import lombok.Setter;


/**
 * Constructor de clase que busca el nombre del pokemon en el Json
 */
@Getter
@Setter
public class Nombre {
    private String nombre;

    public Nombre() {
        super();
    }


    @Override
    public String toString() {
        return nombre;
    }
}

