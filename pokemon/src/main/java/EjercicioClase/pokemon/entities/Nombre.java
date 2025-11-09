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
    private String id;

    public Nombre() {
        super();
    }

    /**
     * @return String con el nombre del pokemon
     */
    @Override
    public String toString() {
        return nombre + "\n Su id es: " +id;
    }
}

