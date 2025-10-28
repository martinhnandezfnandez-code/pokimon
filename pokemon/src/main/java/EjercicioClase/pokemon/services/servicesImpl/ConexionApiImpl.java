package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.services.ConexionApi;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ConexionApiImpl implements ConexionApi {


    /**
     * @param nombre Busca el pokemon que le digas para buscarlo en pokeapi
     */
    @Override
    public String getPokeJson(String nombre) throws IOException {
        String url = "https://pokeapi.co/api/v2/pokemon/";
        url = url + nombre;
        URL urlPokemon = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlPokemon.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();

            String respuesta;

            while ((respuesta = reader.readLine()) != null) {
                response.append(respuesta);
            }

            reader.close();
            return response.toString();
        } else {
            throw new RuntimeException("Error de connexion con la api Codigo: " + responseCode);
        }
    }
}
