package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Estadisticas;
import EjercicioClase.pokemon.entities.Pokemon;
import EjercicioClase.pokemon.entities.Tipos;
import EjercicioClase.pokemon.services.ConexionApi;
import EjercicioClase.pokemon.services.EstadisticasServices;
import EjercicioClase.pokemon.services.PokemonService;
import EjercicioClase.pokemon.services.TiposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private ConexionApi conexionApi;
    @Autowired
    private EstadisticasServices estadisticasServices;
    @Autowired
    private TiposService tiposService;

    /**
     * Metodo que obtiene un pokemon dado su nombre
     *
     * @param nombre: Nombre del pokemon a buscar
     *
     */
    @Override
    public Pokemon obtenerPokemonPorNombre(String nombre) throws Exception {
        String jsonPoke = conexionApi.getPokeJson(nombre);

        Estadisticas estadisticas = estadisticasServices.obtenerStatBase(jsonPoke);
        Tipos tipo = tiposService.obtenerTipos(jsonPoke);
        return new Pokemon(estadisticas, nombre, tipo);
    }
}
