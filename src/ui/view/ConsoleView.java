package ui.view;

import java.util.List;
import java.util.Map;

import model.Participante;
import model.Reto;

/**
 * ConsoleView - Maneja la presentación de datos en consola
 *
 * Responsabilidades:
 * - Mostrar mensajes al usuario (éxito, error, info)
 * - Renderizar datos formateados usando Formatter
 * - Gestionar la interfaz visual (limpiar pantalla, pausas)
 * - Coordinar la salida sin lógica de negocio
 *
 * Delega formateo a Formatter, no maneja entrada de usuario.
 */
public class ConsoleView {
    private final Formatter formatter;

    public ConsoleView() {
        this.formatter = new Formatter();
    }

    /**
     * Muestra mensaje de éxito
     */
    public void showSuccess(String message) {
        System.out.println(formatter.success(message));
    }

    /**
     * Muestra mensaje de error
     */
    public void showError(String message) {
        System.out.println(formatter.error(message));
    }

    /**
     * Muestra mensaje informativo
     */
    public void showInfo(String message) {
        System.out.println(formatter.info(message));
    }

    /**
     * Muestra encabezado de sección
     */
    public void showHeader(String title) {
        System.out.println(formatter.header(title));
    }

    /**
     * Muestra lista de retos
     */
    public void showRetosList(List<Reto> retos) {
        // TODO: Implementar mostrar lista de retos
        // Usar formatter.formatRetosList()
        System.out.println("TODO: Mostrar lista de retos");
    }

    /**
     * Muestra detalles de un reto específico
     */
    public void showRetoDetails(Reto reto) {
        // TODO: Implementar mostrar detalles de reto
        // Usar formatter.formatRetoDetails()
        System.out.println("TODO: Mostrar detalles de reto");
    }

    /**
     * Muestra ranking de participantes
     */
    public void showRanking(List<Map.Entry<Participante, Integer>> ranking) {
        // TODO: Implementar mostrar ranking
        // Usar formatter.formatRanking()
        System.out.println("TODO: Mostrar ranking");
    }

    /**
     * Muestra lista de participantes
     */
    public void showParticipantesList(List<Participante> participantes) {
        // TODO: Implementar mostrar lista de participantes
        // Usar formatter.formatParticipantesList()
        System.out.println("TODO: Mostrar lista de participantes");
    }

    /**
     * Limpia la pantalla
     */
    public void clearScreen() {
        formatter.clearScreen();
    }

    /**
     * Hace una pausa
     */
    public void pause(int seconds) {
        formatter.pause(seconds);
    }

    /**
     * Muestra el prompt para comandos
     */
    public void showPrompt() {
        System.out.print(formatter.prompt("ranking"));
    }

    /**
     * Muestra mensaje de bienvenida
     */
    public void showWelcome() {
        clearScreen();
        showHeader("Sistema de Ranking - Retos");
        showInfo("Escribe 'ayuda' para ver comandos disponibles");
        showInfo("Escribe 'salir' para terminar");
        System.out.println();
    }

    /**
     * Muestra ayuda con comandos disponibles
     */
    public void showHelp() {
        showHeader("Comandos Disponibles");
        System.out.println("crear \"nombre\" [descripcion] [puntos] [tiempo]  - Crear nuevo reto");
        System.out.println("listar [retos|participantes]                  - Listar elementos");
        System.out.println("ver <id>                                       - Ver detalles de reto");
        System.out.println("eliminar <id>                                  - Eliminar reto");
        System.out.println("participante <subcomando>                      - Gestionar participantes");
        System.out.println("ranking                                        - Ver ranking general");
        System.out.println("ayuda                                          - Mostrar esta ayuda");
        System.out.println("salir                                          - Salir del programa");
        System.out.println();
    }
}