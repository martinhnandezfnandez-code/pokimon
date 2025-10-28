package EjercicioClase.pokemon.entities;

import lombok.Data;

/**
 * Clase que define que es un pokemon
 */
@Data
public class Pokemon {
    private String nombre;
    private Estadisticas estadistica;

    public Pokemon() {
        super();
    }

    public Pokemon(Estadisticas estadistica, String nombre) {
        this.estadistica = estadistica;
        this.nombre = nombre;
    }
}
