package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Estadisticas;
import EjercicioClase.pokemon.services.EstadisticasServices;
import org.springframework.stereotype.Service;

@Service
public class EstadisticasServicesImpl implements EstadisticasServices {
    /**
     * Metodo que rellena las estadisticas del pokemon con los datos recibidos
     */
    @Override
    public void rellenoEstadistica(double hp, double ataque, double defensa, double ataqueEspecial, double defensaEspecial, double velocidad) {
        Estadisticas estadistica = new Estadisticas(hp, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad);

    }

}
