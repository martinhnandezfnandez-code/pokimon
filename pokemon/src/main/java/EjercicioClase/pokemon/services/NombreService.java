package EjercicioClase.pokemon.services;

import EjercicioClase.pokemon.entities.Nombre;
import EjercicioClase.pokemon.exceptions.CustomJsonExcepcion;
import org.springframework.stereotype.Service;

@Service
public interface NombreService {
    Nombre obtenerNombre(String jsonCompleto) throws CustomJsonExcepcion;
}
