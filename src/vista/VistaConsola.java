package vista;

import model.Participante;
import model.Reto;
import java.util.List;
import java.util.Map;
import static util.Utils.*;

public class VistaConsola {

    public static void mostrarRanking(List<Map.Entry<Participante, Integer>> ranking) {

        String titulo = formatearTitulo("Ranking", 40);
        String[] encabezados = {"Posición", "Participante", "Puntos"};
        String[][] datos = new String[ranking.size()][3];

        for (int i = 0; i < ranking.size(); i++) {
            Map.Entry<Participante, Integer> rank = ranking.get(i);

            datos[i][0] = String.valueOf(i + 1);
            datos[i][1] = rank.getKey().getNombre();
            datos[i][2] = String.valueOf(rank.getValue());
        }

        String tablas = formatearTablas(datos, encabezados);
        System.out.println(titulo + "\n" + tablas);
    }

    public static void mostrarRetoEnCurso(Reto reto, String nombre) {

    }

    public static void mostrarHistorial(List<Reto> list) {

    }

    public static void mostrarListaParticipantes(List<Participante> list) {

    }
}
