package EjercicioClase.pokemon.services;

import EjercicioClase.pokemon.entities.Habilidades;
import EjercicioClase.pokemon.entities.Tipos;
import org.springframework.stereotype.Service;

@Service
public interface HabilidadesServices {
    Habilidades obtenerHabilidades(String jsonCompleto) throws Exception;
}
