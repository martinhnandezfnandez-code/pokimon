package EjercicioClase.pokemon.services;

import EjercicioClase.pokemon.entities.Habilidades;
import org.springframework.stereotype.Service;

@Service
public interface HabilidadesServices {
    Habilidades obtenerHabilidadesDeUnPoken(String jsonCompleto) throws Exception;
}
