package ui.cli;

import service.GestorParticipantes;
import service.GestorRetos;
import ui.view.ConsoleView;

/**
 * CommandHandler - Interpreta y ejecuta comandos del usuario
 *
 * Responsabilidades:
 * - Parsear comandos y argumentos
 * - Ejecutar acciones correspondientes usando los servicios
 * - Coordinar con ConsoleView para mostrar resultados
 * - Manejar errores de comandos y validaciones
 *
 * No maneja entrada/salida directa, delega a InputReader y ConsoleView.
 */
public class CommandHandler {
    private final GestorRetos gestorRetos;
    private final GestorParticipantes gestorParticipantes;
    private final ConsoleView consoleView;
    private final InputReader inputReader;
    private boolean running;

    public CommandHandler(GestorRetos gestorRetos, GestorParticipantes gestorParticipantes, ConsoleView consoleView,
            InputReader inputReader) {
        this.gestorRetos = gestorRetos;
        this.gestorParticipantes = gestorParticipantes;
        this.consoleView = consoleView;
        this.inputReader = inputReader;
        this.running = true;
    }

    /**
     * Procesa un comando completo
     * 
     * @param command Comando a procesar
     * @return true si debe continuar, false para salir
     */
    public boolean processCommand(String command) {
        // TODO: Implementar procesamiento de comandos
        // Parsear comando y argumentos
        // Ejecutar acción correspondiente
        // Mostrar resultados vía consoleView

        if (command.isEmpty()) {
            return true;
        }

        String[] parts = command.split("\\s+", 2);
        String cmd = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (cmd) {
            case "crear":
                return handleCrear(args);
            case "listar":
                return handleListar(args);
            case "eliminar":
                return handleEliminar(args);
            case "ver":
                return handleVer(args);
            case "participante":
                return handleParticipante(args);
            case "ranking":
                return handleRanking();
            case "ayuda":
                return handleAyuda();
            case "salir":
                return handleSalir();
            default:
                consoleView.showError("Comando desconocido: " + cmd);
                return true;
        }
    }

    /**
     * Maneja comando 'crear'
     * Sintaxis: crear "nombre" [descripcion] [puntos] [tiempo]
     */
    private boolean handleCrear(String args) {
        String nombre;
        String descripcion;
        Integer puntos;
        Integer duracion;

        if (args.isEmpty()) {
            // MODO INTERACTIVO
            consoleView.showInfo("=== Crear nuevo reto ===");

            nombre = inputReader.readLine("Nombre: ");
            descripcion = inputReader.readLine("Descripción: ");
            puntos = Integer.valueOf(inputReader.readInt("Puntos: "));
            duracion = Integer.valueOf(inputReader.readInt("Duración:"));
        } else {
            // MODO RAPIDO
            String[] partes = args.split("\\s+");

            if (partes.length < 3) {
                consoleView.showError("Uso: crear <nombre> <puntos> <tiempo>");
                return true;
            }
            nombre = partes[0];
            descripcion = "Sin descripción";

            try {
                puntos = Integer.parseInt(partes[1]);
                duracion = Integer.parseInt(partes[2]);
            } catch (NumberFormatException e) {
                consoleView.showError("Duración inválida");
                return true;
            }
        }
        if (nombre == null || nombre.isEmpty()) {
            consoleView.showError("El nombre es obligatorio");
            return true;
        }

        if (puntos == null) {
            consoleView.showError("Puntos inválidos");
            return true;
        }

        if (duracion == null) {
            consoleView.showError("Duración inválida");
            return true;
        }

        gestorRetos.crearReto(nombre, descripcion, puntos, duracion);

        consoleView.showInfo("Reto creado: " + nombre);
        return true;
    }

    /**
     * Maneja comando 'listar'
     * Sintaxis: listar [retos|participantes]
     */
    private boolean handleListar(String args) {
        // TODO: Implementar listado
        // Si args vacío, listar retos; si "participantes", listar participantes

        consoleView.showInfo("Comando 'listar' - TODO: Implementar");
        return true;
    }

    /**
     * Maneja comando 'eliminar'
     * Sintaxis: eliminar <id>
     */
    private boolean handleEliminar(String args) {
        // TODO: Implementar eliminación
        // Parsear ID, confirmar eliminación, ejecutar
        consoleView.showInfo("Comando 'eliminar' - TODO: Implementar");
        return true;
    }

    /**
     * Maneja comando 'ver'
     * Sintaxis: ver <id>
     */
    private boolean handleVer(String args) {
        // TODO: Implementar ver detalles
        // Parsear ID, mostrar detalles del reto
        consoleView.showInfo("Comando 'ver' - TODO: Implementar");
        return true;
    }

    /**
     * Maneja comando 'participante'
     * Sintaxis: participante <subcomando> [args]
     */
    private boolean handleParticipante(String args) {
        // TODO: Implementar gestión de participantes
        // Subcomandos: agregar, eliminar, listar
        consoleView.showInfo("Comando 'participante' - TODO: Implementar");
        return true;
    }

    /**
     * Maneja comando 'ranking'
     * Sintaxis: ranking
     */
    private boolean handleRanking() {
        // TODO: Implementar mostrar ranking
        // Obtener ranking y mostrarlo
        consoleView.showInfo("Comando 'ranking' - TODO: Implementar");
        return true;
    }

    /**
     * Maneja comando 'ayuda'
     */
    private boolean handleAyuda() {
        // TODO: Implementar ayuda
        // Mostrar lista de comandos disponibles
        consoleView.showInfo("Comando 'ayuda' - TODO: Implementar");
        return true;
    }

    /**
     * Maneja comando 'salir'
     */
    private boolean handleSalir() {
        running = false;
        consoleView.showInfo("¡Hasta luego!");
        return false;
    }

    /**
     * Verifica si la aplicación debe continuar ejecutándose
     */
    public boolean isRunning() {
        return running;
    }
}