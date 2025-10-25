package util;

public class Utils {

    public static void limpiarPantalla() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50 ; i++) {
                System.out.println();
            }
        }
    }

    public static void emitirSonido() {
        java.awt.Toolkit.getDefaultToolkit().beep();
    }

    public static String formatearTablas(String[][] datos, String[] encabezados ) {
        StringBuilder tabla = new StringBuilder();

        // Calcular ancho las columnas
        int numColumnas = encabezados.length;
        int[] ancho = new int[numColumnas];

        for (int i = 0; i < numColumnas; i++) {
            ancho[i] = encabezados[i].length();
        }

        // Actualizamos por si un dato es más largo
        for (String[] fila : datos) {
            for (int i = 0; i < numColumnas; i++) {
                if (fila[i].length() > ancho[i]) {
                    ancho[i] = fila[i].length();
                }
            }
        }

        // Borde Superior
        tabla.append("╔");
        for (int i = 0; i < numColumnas; i++) {
            tabla.append("═".repeat(ancho[i] + 2));
            if (i < numColumnas - 1) {
                tabla.append("╦");
            }
        }
        tabla.append("╗\n");

        // Encabezados
        tabla.append("║");
        for (int i = 0; i < numColumnas; i++) {
            tabla.append(" ");
            tabla.append(centrarTexto(encabezados[i], ancho[i]));
        }
        tabla.append("\n");

        // Borde Medio
        tabla.append("╠");
        for (int i = 0; i < numColumnas; i++) {
            tabla.append("=".repeat(ancho[i] + 2));
            if (i < numColumnas - 1) {
                tabla.append("╬");
            }
        }
        tabla.append("╣\n");

        // Filas de Datos
        for (String[] fila : datos) {
            tabla.append("║");
            for (int i = 0; i < numColumnas; i++) {
                tabla.append(" ");
                tabla.append(String.format("%-" + ancho[i] + "s", fila[i]));
                tabla.append(" ║");
            }
            tabla.append("\n");
        }

        // Borde Inferior
        tabla.append("╚");
        for (int i = 0; i < numColumnas; i++) {
            tabla.append("=".repeat(ancho[i] + 2));
            if (i < numColumnas - 1) {
                tabla.append("╩");
            }
        }
        tabla.append("╝\n");

        return tabla.toString();
    }

    public static String centrarTexto(String texto, int ancho) {
        int izq = (ancho - texto.length()) /2;
        int der = ancho - texto.length() - izq;
        return " ".repeat(izq) + texto + " ".repeat(der);
    }

    public static void pausar(long segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            System.out.println("Pausa interrumpida: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
