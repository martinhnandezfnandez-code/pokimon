package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Estadisticas;
import EjercicioClase.pokemon.services.EstadisticasServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class EstadisticasServicesImpl implements EstadisticasServices {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Metodo que obtiene las estadisticas base de un json obtenido
     *
     * @param jsonCompleto: Json con las estadisticas.
     * */
    @Override
    public Estadisticas obtenerStatBase(String jsonCompleto) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonCompleto);
        JsonNode statsArray = rootNode.get("stats");

        Estadisticas estadisticas = new Estadisticas();

        for (JsonNode stat : statsArray) {
            JsonNode baseStat = stat.get("base_stat");
            JsonNode nameStat = stat.get("stat");

            String nombreStat = String.valueOf(nameStat.get("name"));

            try {
                if (baseStat != null) {
                    switch (nombreStat.replace("\"", "")) {
                        case "hp":
                            estadisticas.setHp(baseStat.asDouble());
                            break;
                        case "attack":
                            estadisticas.setAtaque(baseStat.asDouble());
                            break;
                        case "defense":
                            estadisticas.setDefensa(baseStat.asDouble());
                            break;
                        case "special-attack":
                            estadisticas.setAtaqueEspecial(baseStat.asDouble());
                            break;
                        case "special-defense":
                            estadisticas.setDefensaEspecial(baseStat.asDouble());
                            break;
                        case "speed":
                            estadisticas.setVelocidad(baseStat.asDouble());
                            break;
                        default:
                            //No me importa
                            break;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Se ha producido un error al mapear las estadisticas del pokimo");
            }

        }

        return estadisticas;
    }

}
