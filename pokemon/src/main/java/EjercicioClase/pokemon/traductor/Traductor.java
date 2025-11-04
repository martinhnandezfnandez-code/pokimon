package EjercicioClase.pokemon.traductor;

import java.util.HashMap;
import java.util.Map;

/**
 * Constructor de clase de traduccion de tipos pokemon
 */
public class Traductor {

    private static final Map<String, String> diccionarioEN_ES;

    static {
        diccionarioEN_ES = new HashMap<>();

        diccionarioEN_ES.put("normal", "Normal");
        diccionarioEN_ES.put("steel", "Acero");
        diccionarioEN_ES.put("water", "Agua");
        diccionarioEN_ES.put("bug", "Bicho");
        diccionarioEN_ES.put("dragon", "Dragón");
        diccionarioEN_ES.put("electric", "Eléctrico");
        diccionarioEN_ES.put("ghost", "Fantasma");
        diccionarioEN_ES.put("fire", "Fuego");
        diccionarioEN_ES.put("fairy", "Hada");
        diccionarioEN_ES.put("ice", "Hielo");
        diccionarioEN_ES.put("fighting", "Lucha");
        diccionarioEN_ES.put("grass", "Planta");
        diccionarioEN_ES.put("psychic", "Psíquico");
        diccionarioEN_ES.put("rock", "Roca");
        diccionarioEN_ES.put("dark", "Siniestro");
        diccionarioEN_ES.put("ground", "Tierra");
        diccionarioEN_ES.put("poison", "Veneno");
        diccionarioEN_ES.put("flying", "Volador");
    }


    private Traductor() {
        super();
    }

    /**
     * Un tipo de pokemon en inglés lo traduce a español
     *
     * @param palabra String del tipo pokemon en ingles
     * @return String tipo pokemon en español
     */
    public static String traducirIngles(String palabra) {
        String palabraMinuscula = palabra.toLowerCase().trim();
        return diccionarioEN_ES.getOrDefault(palabraMinuscula,
                "Traducción no disponible para: " + palabra);
    }
}