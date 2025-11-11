package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.*;
import EjercicioClase.pokemon.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {
    public static final int OBTENER_POKEMON_ID = 1;

    @Autowired
    private ConexionApi conexionApi;
    @Autowired
    private EstadisticasServices estadisticasServices;
    @Autowired
    private TiposService tiposService;
    @Autowired
    private HabilidadesServices habilidadesServices;
    @Autowired
    private NombreService nombreService;

    /**
     * Metodo que obtiene un pokemon dado su nombre
     *
     * @param nombre: Nombre del pokemon a buscar
     *
     */
    @Override
    public Pokemon obtenerPokemonPorNombre(String nombre) throws Exception {
        String jsonPoke = conexionApi.getPokeJsonByName(nombre, OBTENER_POKEMON_ID);

        Estadisticas estadisticas = estadisticasServices.obtenerStatBase(jsonPoke);
        Tipos tipo = tiposService.obtenerTipos(jsonPoke);
        Habilidades habilidades = habilidadesServices.obtenerHabilidadesDeUnPoken(jsonPoke);
        Nombre id = nombreService.obtenerNombre(jsonPoke);
        return new Pokemon(estadisticas, nombre, tipo, habilidades, id);
    }
}
