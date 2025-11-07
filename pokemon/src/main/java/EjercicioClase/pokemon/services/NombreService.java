package EjercicioClase.pokemon.services;

import EjercicioClase.pokemon.entities.Nombre;
import org.springframework.stereotype.Service;

@Service
public interface NombreService {
    Nombre obtenerNombre(String jsonCompleto) throws Exception;

}
