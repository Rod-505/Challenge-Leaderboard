package service;

import java.util.ArrayList;
import java.util.List;

import model.EstadoReto;
import model.Resultado;
import model.Reto;

public class GestorRetos {

    private GestorDatos gestorDatos;
    private GestorParticipantes gestorParticipantes;
    private int ultimoId;

    public GestorRetos(GestorDatos gestorDatos) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        this.gestorDatos = gestorDatos;
        this.ultimoId = datos.ultimoIdReto;
    }

    public void setGestorParticipantes(GestorParticipantes gestorParticipantes) {
        this.gestorParticipantes = gestorParticipantes;
    }

    public Reto crearReto(String nombre, String descripcion, int puntosMax, int tiempo) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        ultimoId++;
        Reto reto = new Reto(ultimoId, nombre, descripcion, puntosMax, tiempo);
        datos.retos.add(reto);
        datos.ultimoIdReto = ultimoId;
        gestorDatos.guardarDatos(datos);
        return reto;
    }

    public Reto crearReto(String nombre, String descripcion, int puntosMax) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        ultimoId++;
        Reto reto = new Reto(ultimoId, nombre, descripcion, puntosMax, 0);
        datos.retos.add(reto);
        datos.ultimoIdReto = ultimoId;
        gestorDatos.guardarDatos(datos);
        return reto;
    }

    public Reto obtenerReto(int id) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        for (Reto r : datos.retos) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public List<Reto> listarRetosPendientes() {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        List<Reto> pendientes = new ArrayList<>();
        for (Reto r : datos.retos) {
            if (r.getEstado() == EstadoReto.PENDIENTE) {
                pendientes.add(r);
            }
        }
        return pendientes;
    }

    public boolean iniciarReto(int id) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        for (Reto r : datos.retos) {
            if (r.getId() == id && r.getEstado() == EstadoReto.PENDIENTE) {
                r.setEstado(EstadoReto.EN_CURSO);
                gestorDatos.guardarDatos(datos);
                return true;
            }
        }
        return false;
    }

    public boolean agregarParticipanteAlReto(int retoId, String nombreParticipante) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();

        // Buscar el reto específico por ID
        Reto retoTarget = null;
        for (Reto r : datos.retos) {
            if (r.getId() == retoId) {
                retoTarget = r;
                break;
            }
        }

        // Si no existe el reto, retornar false
        if (retoTarget == null) {
            return false;
        }

        // Verificar que el participante esté registrado
        if (gestorParticipantes == null || gestorParticipantes.obtenerParticipante(nombreParticipante) == null) {
            return false; // Participante no registrado
        }

        // Verificar si el participante ya existe en ESTE reto
        for (Resultado res : retoTarget.getResultados()) {
            if (res.getNombreParticipante().equals(nombreParticipante)) {
                return false; // Participante ya existe en este reto
            }
        }

        // Agregar participante a este reto específico
        int posicion = retoTarget.getResultados().size() + 1;
        int puntos = calcularPuntosDecrecientes(retoTarget.getPuntosMaximos(), posicion);
        Resultado resultado = new Resultado(nombreParticipante, puntos, posicion);
        retoTarget.agregarResultado(resultado);
        gestorDatos.guardarDatos(datos);
        return true;
    }

    public void finalizarReto(int id) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        for (Reto r : datos.retos) {
            if (r.getId() == id) {
                r.setEstado(EstadoReto.COMPLETADO);
                gestorDatos.guardarDatos(datos);
                return;
            }
        }
    }

    public int calcularPuntosDecrecientes(int puntosMax, int posicion) {
        int puntos = puntosMax - (posicion - 1);
        return Math.max(puntos, 1);
    }

    public int obtenerPuntosParticipanteEnReto(int retoId, String nombreParticipante) {
        Reto reto = obtenerReto(retoId);
        if (reto == null)
            return 0;

        for (Resultado r : reto.getResultados()) {
            if (r.getNombreParticipante().equals(nombreParticipante)) {
                return r.getPuntosObtenidos();
            }
        }
        return 0;
    }

    public List<Reto> listarRetosCompletados() {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        List<Reto> completados = new ArrayList<>();

        for (Reto reto : datos.retos) {
            if (reto.getEstado() == EstadoReto.COMPLETADO) {
                completados.add(reto);
            }
        }
        return completados;
    }

    public List<Reto> listarRetos() {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        List<Reto> retos = new ArrayList<>();

        for (Reto reto : datos.retos) {
            retos.add(reto);
        }
        return retos;
    }

    public boolean eliminarReto(int id) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();

        for (Reto reto : datos.retos) {
            if (reto.getId() == id) {
                datos.retos.remove(reto);
                gestorDatos.guardarDatos(datos);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarParticipanteDelReto(int retoId, String nombreParticipante) {
        if (nombreParticipante == null || nombreParticipante.trim().isEmpty()) {
            return false;
        }

        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();

        for (Reto reto : datos.retos) {
            if (reto.getId() == retoId) {
                for (int i = 0; i < reto.getResultados().size(); i++) {
                    if (reto.getResultados().get(i).getNombreParticipante().equals(nombreParticipante)) {
                        reto.getResultados().remove(i);
                        gestorDatos.guardarDatos(datos);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public boolean actualizarReto(int id, String nombre) {
        return actualizarReto(id, nombre, null, -1, -1);
    }

    public boolean actualizarReto(int id, String nombre, String descripcion) {
        return actualizarReto(id, nombre, descripcion, -1, -1);
    }

    public boolean actualizarReto(int id, String nombre, String descripcion, int puntosMax) {
        return actualizarReto(id, nombre, descripcion, puntosMax, -1);
    }

    public boolean actualizarReto(int id, String nombre, String descripcion, int puntosMax, int tiempo) {
        if (id <= 0 || nombre == null || nombre.trim().isEmpty() || descripcion == null
                || descripcion.trim().isEmpty()) {
            return false;
        }

        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();

        for (Reto reto : datos.retos) {
            if (reto.getId() == id) {
                // Modificar solo si reto está pendiente o en curso
                if (reto.getEstado() != EstadoReto.PENDIENTE || reto.getEstado() != EstadoReto.EN_CURSO) {
                    return false;
                }
                reto.setNombre(nombre);
                reto.setDescripcion(descripcion);
                if (puntosMax > 0) {
                    reto.setPuntosMaximos(puntosMax);
                }
                if (tiempo >= 0) {
                    reto.setTiempoMinutos(tiempo);
                }
                gestorDatos.guardarDatos(datos);
                return true;
            }
        }
        return false;
    }
}
