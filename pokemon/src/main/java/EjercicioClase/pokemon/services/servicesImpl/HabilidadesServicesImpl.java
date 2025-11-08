package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Habilidad;
import EjercicioClase.pokemon.entities.Habilidades;
import EjercicioClase.pokemon.services.HabilidadService;
import EjercicioClase.pokemon.services.HabilidadesServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabilidadesServicesImpl implements HabilidadesServices {
    public static final String HABILIDAD_PRINCIPAL = "1";
    public static final String HABILIDAD_SECUNDARIA = "2";
    public static final String HABILIDAD_OCULTA = "3";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private HabilidadService habilidadService;

    /**
     * * Metodo que obtiene las habilidades base de un json obtenido
     * * @param jsonCompleto: Json con las estadisticas.
     */
    @Override
    public Habilidades obtenerHabilidadesDeUnPoken(String jsonCompleto) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonCompleto);
        JsonNode habilidadesArray = rootNode.get("abilities");
        Habilidades habilidades = new Habilidades();

        for (JsonNode stat : habilidadesArray) {

            JsonNode nameAbility = stat.get("ability");

            String nombreHabilidad = String.valueOf(nameAbility.get("name")).replace("\"", "");
            String nSlot = String.valueOf(stat.get("slot"));

            //Obtenemos la habilidad y su descripción basándonos en el nombre que hemos obtenido
            Habilidad habilidad = new Habilidad(habilidadService.obtenerHabilidadPorNombre(nombreHabilidad));

            switch (nSlot) {
                case HABILIDAD_PRINCIPAL:
                    habilidades.setHabilidad(habilidad);
                    break;
                case HABILIDAD_SECUNDARIA:
                    habilidades.setHabilidad2(habilidad);
                    break;
                case HABILIDAD_OCULTA:
                    habilidades.setHabilidadOculta(habilidad);
                    break;
                default:
                    //a luis no le interesa
            }
        }

        return habilidades;
    }
}
