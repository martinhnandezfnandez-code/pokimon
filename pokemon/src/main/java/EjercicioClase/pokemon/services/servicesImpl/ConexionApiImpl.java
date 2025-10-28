package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.services.ConexionApi;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;


@Service
public class ConexionApiImpl implements ConexionApi {
    public static final int STATUS_CODE_OK = 200;
    public static final String REQUEST_METHOD = "GET";

    /**
     * Opciones de URL de la API
     */
    public static final String BASE_URL = "https://pokeapi.co/api/v2/";
    public static final String POKEMON_URL = "pokemon/";
    public static final String HABILIDAD_URL = "ability/";


    /**
     * Tipos de busquedas en la API
     * */
    public static final int BUSQUEDA_NOMBRE_ID = 1;
    public static final int BUSQUEDA_HABILIDAD_ID = 2;


    /**
     * Metodo que obtiene un JSON dado el nombre del pokemon.
     *
     * @param valorConsulta Busca el valor que le digas en la API según el tipo enviado.
     * @param tipoConsulta: ID con el tipo de consulta que deseamos realizar:
     *                      1 = POKEMON
     *                      2 = HABILIDAD
     *
     * @return Objeto STRING con el JSON obtenido para la llamada a la API en base al tipo de consulta y el valor dado.
     */
    @Override
    public String getPokeJsonByName(String valorConsulta, Integer tipoConsulta) throws IOException, URISyntaxException {
        if (valorConsulta != null) {
            String url = BASE_URL;

            switch (tipoConsulta) {
                case BUSQUEDA_NOMBRE_ID:
                    url += POKEMON_URL + valorConsulta;
                    break;
                case BUSQUEDA_HABILIDAD_ID:
                    url += HABILIDAD_URL + valorConsulta;
                    break;
                default:
                    System.out.println("No se ha introducido un tipo valido");
                    System.out.println("Consulta los posibles tipos aqui: " + url);
            }

            return getJsonResponse(url);
        }

        System.out.println("No se ha introducido valor de consulta");
        return "";
    }

    /**
     * Metodo que llama a la API y obtiene el JSON resultante.
     *
     * @param url: URL de la llamada.
     * @return Objeto tipo STRING con el JSON obtenido
     *
     */
    private static String getJsonResponse(String url) throws URISyntaxException, IOException {
        URI urlPokemon = new URI(url);

        HttpURLConnection connection = (HttpURLConnection) urlPokemon.toURL().openConnection();
        connection.setRequestMethod(REQUEST_METHOD);

        int responseCode = connection.getResponseCode();

        if (responseCode == STATUS_CODE_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();

            String respuesta;

            while ((respuesta = reader.readLine()) != null) {
                response.append(respuesta);
            }

            reader.close();
            return response.toString();
        } else {
            throw new RuntimeException("Error de connexion con la api Código: " + responseCode);
        }
    }
}
