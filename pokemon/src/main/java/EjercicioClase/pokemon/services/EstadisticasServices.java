package EjercicioClase.pokemon.services;

import org.springframework.stereotype.Service;

@Service
public interface EstadisticasServices {
    void rellenoEstadistica(double hp, double ataque, double defensa, double ataqueEspecial, double defensaEspecial, double velocidad);

}
