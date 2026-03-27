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
    private static final String ORANGE = "\u001B[38;2;255;165;0m";
    private static final String RESET = "\u001B[0m";
    private static final String BORDER_COLOR = "\u001B[38;2;201;103;82m"; // El tono bronce/cobre
    private static final String TEXT_COLOR = "\u001B[1;97m";

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
        System.out.println(formatter.formatRetosList(retos));
    }

    /**
     * Muestra detalles de un reto específico
     */
    public void showRetoDetails(Reto reto) {
        System.out.println(formatter.formatRetoDetails(reto));
    }

    /**
     * Muestra ranking de participantes
     */
    public void showRanking(List<Map.Entry<Participante, Integer>> ranking) {
        System.out.println(formatter.formatRanking(ranking));
    }

    /**
     * Muestra detalles de un participante específico
     */
    public void showParticipanteDetails(Participante participante) {
        System.out.println(formatter.formatParticipanteDetails(participante));
    }

    /**
     * Muestra lista de participantes
     */
    public void showParticipantesList(List<Participante> participantes) {
        System.out.println(formatter.formatParticipantesList(participantes));
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
        System.out.print("\u001B[32muser@english22/: \u001B[0m");
    }

    /**
     * Muestra mensaje de bienvenida
     */
    public void showWelcome() {
        clearScreen();
        printBoxedMessage("Welcome to the Challenge Code!!!");
        System.out.println(ORANGE);
        System.out.println(
                " ██████╗██╗  ██╗ █████╗ ██╗     ██╗     ███████╗███╗   ██╗ ██████╗ ███████╗     ██████╗ ██████╗ ██████╗ ███████╗");
        System.out.println(
                "██╔════╝██║  ██║██╔══██╗██║     ██║     ██╔════╝████╗  ██║██╔════╝ ██╔════╝    ██╔════╝██╔═══██╗██╔══██╗██╔════╝");
        System.out.println(
                "██║     ███████║███████║██║     ██║     █████╗  ██╔██╗ ██║██║  ███╗█████╗      ██║     ██║   ██║██║  ██║█████╗  ");
        System.out.println(
                "██║     ██╔══██║██╔══██║██║     ██║     ██╔══╝  ██║╚██╗██║██║   ██║██╔══╝      ██║     ██║   ██║██║  ██║██╔══╝  ");
        System.out.println(
                "╚██████╗██║  ██║██║  ██║███████╗███████╗███████╗██║ ╚████║╚██████╔╝███████╗    ╚██████╗╚██████╔╝██████╔╝███████╗");
        System.out.println(
                " ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝╚═╝  ╚═══╝ ╚═════╝ ╚══════╝     ╚═════╝ ╚═════╝ ╚═════╝ ╚══════╝");
        System.out.println(RESET);
        System.out.println("\033[34mLogin successful. Press Enter to continue\033[0m");
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
        System.out.println("iniciar <id>                                   - Iniciar un reto pendiente");
        System.out.println("eliminar <id>                                  - Eliminar reto");
        System.out.println("participante <subcomando>                      - Gestionar participantes");
        System.out.println("unir <reto_id> <participante_nombre>           - Agregar participante a reto");
        System.out.println("ranking                                        - Ver ranking general");
        System.out.println("ayuda                                          - Mostrar esta ayuda");
        System.out.println("salir                                          - Salir del programa");
        System.out.println();
    }

    public void printBoxedMessage(String message) {
        int padding = 4;
        int innerWidth = message.length() + (padding * 2);

        // Borde superior
        System.out.println(BORDER_COLOR + "╭" + "─".repeat(innerWidth) + "╮" + RESET);

        // Contenido
        System.out.println(BORDER_COLOR + "│" + " ".repeat(padding) + TEXT_COLOR + message + " ".repeat(padding) +
                BORDER_COLOR + "│" + RESET);

        // Borde inferior
        System.out.println(BORDER_COLOR + "╰" + "─".repeat(innerWidth) + "╯" + RESET);
    }
}