package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Nombre;
import EjercicioClase.pokemon.exceptions.ApiGenericException;
import EjercicioClase.pokemon.exceptions.CustomJsonExcepcion;
import EjercicioClase.pokemon.services.NombreService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class NombreServiceImpl implements NombreService {
    public static final int ERROR_LECTURA_JSON = 4;
    public static final int CODIGO_ERROR_POKEMON = 1;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Obtiene el nombre buscandolo en el Json
     *
     * @param jsonCompleto Json del nombre
     * @return String con el nombre del pokemon
     */
    @Override
    public Nombre obtenerNombre(String jsonCompleto) throws CustomJsonExcepcion {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonCompleto);

            Nombre nombre = new Nombre();

            JsonNode nombres = rootNode.get("name");
            JsonNode id = rootNode.get("id");
            nombre.setNombre(nombres.asText());
            nombre.setId(id.asText());

            return nombre;
        } catch (NullPointerException npe) {
            throw new CustomJsonExcepcion("El Json ha sido modificado en origen", ERROR_LECTURA_JSON, CODIGO_ERROR_POKEMON);
        } catch (Exception e) {
            throw new CustomJsonExcepcion(e.getMessage(), ERROR_LECTURA_JSON, CODIGO_ERROR_POKEMON);
        }

    }
}
