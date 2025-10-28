package EjercicioClase.pokemon.services;

import EjercicioClase.pokemon.entities.Estadisticas;
import org.springframework.stereotype.Service;

@Service
public interface EstadisticasServices {
    Estadisticas obtenerStatBase(String jsonCompleto) throws Exception;
}
