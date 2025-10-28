package EjercicioClase.pokemon.services;

import EjercicioClase.pokemon.entities.Tipos;
import org.springframework.stereotype.Service;

@Service
public interface TiposService {
    Tipos obtenerTipos(String jsonCompleto) throws Exception;

}
