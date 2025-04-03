package controller;

import model.Entidad;
import model.Llamada;
import model.Persona;

import java.util.Comparator;
import java.util.List;

public class Controlador {

    //Singleton

    private static Controlador singleton;

    private Entidad ayuntamientoMalaga;

    private Controlador() {
        ayuntamientoMalaga = new Entidad("","","");
    }

    public static Controlador getSingleton() {
        if(singleton==null) singleton = new Controlador();
        return singleton;
    }

    //CRUD de Entidad y gestión de enlace entre VistaModelo

    public void setData(String nombre, String id, String direccion) {
        ayuntamientoMalaga.setNombre(nombre);
        ayuntamientoMalaga.setId(id);
        ayuntamientoMalaga.setDireccion(direccion);
    }

    public String getEntidadString() {
        return ayuntamientoMalaga.toString();
    }

    public boolean altaLlamada(Llamada ll) {
        return ayuntamientoMalaga.altaLlamada(ll);
    }

    public boolean bajaLlamada(long id) {
        return ayuntamientoMalaga.bajaLlamada(id);
    }

    public List<Llamada> listarTodas() {
        return ayuntamientoMalaga.listarTodas();
    }

    public  List<Llamada> listarUrgentes() {
        return ayuntamientoMalaga.listarUrgentes();
    }

    public  Llamada buscarLlamada(long id) {
        return ayuntamientoMalaga.buscarLlamada(id);
    }

    public List<Llamada> buscarLlamada(String nombre, boolean bDestinatario) {
        return ayuntamientoMalaga.buscarLlamada(nombre,bDestinatario);
    }

    public List<Persona> getAllPersonas(Comparator<Persona> comparador) {
        return ayuntamientoMalaga.getAllPersonas(comparador);
    }



}
