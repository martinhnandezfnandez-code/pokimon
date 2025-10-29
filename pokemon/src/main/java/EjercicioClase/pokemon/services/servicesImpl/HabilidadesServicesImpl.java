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

            //Una vez que tenemos el nombre obtenemos la habilidad y su descripción
            Habilidad habilidad = new Habilidad(habilidadService.obtenerHabilidadPorNombre(nombreHabilidad));

            switch (nSlot) {
                case "1":
                    habilidades.setHabilidad(habilidad);
                    break;
                case "2":
                    habilidades.setHabilidad2(habilidad);
                    break;
                case "3":
                    habilidades.setHabilidadOculta(habilidad);
                    break;
                default:
                    //a luis no le interesa
            }
        }

        return habilidades;
    }
}
