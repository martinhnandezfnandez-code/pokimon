package EjercicioClase.pokemon.services;

import EjercicioClase.pokemon.entities.Pokemon;
import org.springframework.stereotype.Service;

@Service
public interface PokemonService {
    Pokemon obtenerPokemonPorNombre(String nombre) throws Exception;
}
