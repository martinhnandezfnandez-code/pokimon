package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Habilidades;
import EjercicioClase.pokemon.entities.Tipos;
import EjercicioClase.pokemon.services.HabilidadesServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class HabilidadesServicesImpl implements HabilidadesServices {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * * Metodo que obtiene las habilidades base de un json obtenido
     * * @param jsonCompleto: Json con las estadisticas.
     */
    @Override
    public Habilidades obtenerHabilidades(String jsonCompleto) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonCompleto);
        JsonNode habilidadesArray = rootNode.get("abilities");
        Habilidades habilidades = new Habilidades();

        for (JsonNode stat : habilidadesArray) {

            JsonNode nameAbility = stat.get("ability");


            String nombreHabilidad = String.valueOf(nameAbility.get("name"));
            String nSlot = String.valueOf(stat.get("slot"));
            switch (nSlot) {
                case "1":
                    habilidades.setHabilidad(nombreHabilidad.replace("\"", ""));
                    break;
                case "2":
                    habilidades.setHabilidad2(nombreHabilidad.replace("\"", ""));
                    break;
                case "3":
                    habilidades.setHabilidadOculta(nombreHabilidad.replace("\"", ""));
                    break;
                default://a luis no le interesa
            }
        }

        return habilidades;
    }
}
