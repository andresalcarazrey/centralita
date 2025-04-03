package view;

import controller.Controlador;
import model.ComparatorPersonaEmail;
import model.Entidad;
import model.Llamada;
import model.Persona;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int opcion;
        //Vista
        Controlador.getSingleton().setData(leerValor("Nombre de la entidad"),leerValor("Id de la entidad"),leerValor("Dirección de la entidad"));

        do {
            mostrarMenu();
            System.out.println("Introduzca la opción a realizar (0 salir)");
            opcion = leerValorInt();
            realizarOpcion(opcion);
        } while(opcion!=0);
    }

    public static void mostrarMenu() {
        System.out.println("--------------------------------");
        System.out.println("Menú de Gestión de la Centralita");
        System.out.println("1. Dar de alta una llamada");
        System.out.println("2. Dar de baja una llamada");
        System.out.println("3. Listar todas las llamadas");
        System.out.println("4. Listar todas las Personas. Por Apellidos-Nombre");
        System.out.println("5. Listar todas las Personas. Por email");
        System.out.println("6. Listar todas las Personas. Por dni");
        System.out.println("7. Listar llamadas atención urgente");
        System.out.println("8. Buscar una llamada por destinatario");
        System.out.println("9. Grabar entidad centralita a disco");
        System.out.println("--------------------------------");
    }

    public static int leerValorInt() {
        return (new Scanner(System.in)).nextInt();
    }

    public static long leerValorLong() {
        return (new Scanner(System.in)).nextLong();
    }

    public static String leerValor(String texto) {
        System.out.println("Introduzca " + texto);
        return (new Scanner(System.in)).nextLine();
    }

    public static Persona leerPersona() {
        Persona p = new Persona(leerValor("Nombre:"),leerValor("Apellidos:"),leerValor("Teléfono:"),leerValor("Email:"),leerValor("Dni:"));
        return p;
    }


    public static boolean leerValorBoolean(String texto) {
        System.out.println("Introduzca " + texto + " (True para sí, False para no)");
        return (new Scanner(System.in)).nextBoolean();
    }

    public static void realizarOpcion(int opcion) {
        switch (opcion) {
            case 1: altaLlamada();
                break;
            case 2: bajaLlamada();
                break;
            case 3: listarTodas();
                break;
            case 4: listarPersonasApeNombre();
                break;
            case 5: listarPersonasEmail();
                break;
            case 6: listarPersonasDni();
                break;
            case 7: listarUrgentes();
                break;
            case 8: buscarLlamada();
                break;
            case 9: grabarADisco();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println(("Opción no válida"));

        }
    }

    private static void grabarADisco() {
        String nombreFichero = "entidad.data";
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(nombreFichero));
            out.println(Controlador.getSingleton().getEntidadString());
        } catch (IOException excepcion) {
            System.out.println("Atención importante: No se ha podido grabar. Problema de sistema");
            System.out.println(excepcion.getMessage());
        } finally {
            if (out!=null) out.close();
        }
    }

    public static void altaLlamada() {
        //Vamos a coger los datos de la llamada
        Llamada ll = new Llamada(leerValor("Fecha (dd/mm/aaaa):"),leerValor("Hora (hh:mm);"),leerValorBoolean("Urgente?"),leerPersona(),leerPersona());
        if (Controlador.getSingleton().altaLlamada(ll)) {
            System.out.println("Llamada creada correctamente");
        } else {
            System.out.println("Hay un problema de almacenamiento o la llamada no se ha podido crear (error interno). \nConsulte con Administrador del sistema");
        }
    }

    public static void bajaLlamada() {
        System.out.println("Introduzca el Id de la llamada:");
        long id = leerValorLong();
        if(Controlador.getSingleton().bajaLlamada(id)) {
            System.out.println("llamada eliminada OK");
        } else {
            System.out.println("La llamada" + id + " no se encuentra");
        }
    }
    public static void listarTodas() {
        System.out.println(".................\nLlamadas activas:\n..............");
        for(Llamada ll: Controlador.getSingleton().listarTodas()) {
            System.out.println(ll.toString());
        }

    }
    public static void listarUrgentes() {
        System.out.println(".................\nLlamadas urgentes:\n..............");
        for(Llamada ll: Controlador.getSingleton().listarUrgentes()) {
            System.out.println(ll.toString());
        }
    }
    public static void buscarLlamada() {
        System.out.println("Desea buscar por Id o por nombre?: (1 para Id, 2 para buscar por nombre)");
        int opcion = leerValorInt();
        switch (opcion) {
            case 1: buscarPorId();
                break;
            case 2: buscarPorNombre();
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public static void buscarPorId() {
        System.out.println("Introduzca el id a buscar:");
        long id = leerValorLong();
        Llamada encontrada = Controlador.getSingleton().buscarLlamada(id);
        if(encontrada==null) {
            System.out.println("No hay ninguna llamada con ese Id");
        } else {
            System.out.println(encontrada.toString());
        }
    }

    public static void buscarPorNombre() {
        System.out.println("Introduzca el nombre a buscar:");
        String nombre = leerValor("Nombre:");
        System.out.println("Desea buscar por origen (1) o por destinatario (2)");
        int opcion = leerValorInt();
        boolean bDestinatario = (opcion==2);
        List<Llamada> encontradas = Controlador.getSingleton().buscarLlamada(nombre,bDestinatario);
        if(encontradas==null) {
            System.out.println("No hay ninguna llamada con " + nombre + " de destinatario == " + bDestinatario);
        } else {
            System.out.println(".................\nLlamadas encontradas:\n..............");
            for(Llamada ll: encontradas) {
                System.out.println(ll.toString());
            }
        }
    }

    private static void listarPersonasApeNombre() {
        System.out.println(".................\nPersonas x Apellidos-Nombre:\n..............");
        for(Persona p: Controlador.getSingleton().getAllPersonas(null) ) {
            System.out.println(p.toString());
        }
    }

    private static void listarPersonasEmail() {
        System.out.println(".................\nPersonas x Email:\n..............");
        for(Persona p: Controlador.getSingleton().getAllPersonas(new ComparatorPersonaEmail()) ) {
            System.out.println(p.toString());
        }
    }

    private static void listarPersonasDni() {
        System.out.println(".................\nPersonas x Dni:\n..............");
        for(Persona p: Controlador.getSingleton().getAllPersonas( new Comparator<Persona>() {
                @Override
                public int compare(Persona p1, Persona p2) {
                    return p1.getsDni().compareTo(p2.getsDni());
                }
            })) {
            System.out.println(p.toString());
        }
    }

    private static void listarPersonasApellidosNombreEmail() {
        System.out.println(".................\nPersonas x Dni:\n..............");
        for(Persona p: Controlador.getSingleton().getAllPersonas(  new Comparator<Persona>() {
            @Override
            public int compare(Persona p1, Persona p2) {
                return p1.getsDni().compareTo(p2.getsDni());
            }
        })) {
            System.out.println(p.toString());
        }
    }

    private static void listarPersonasTelefono() {
        System.out.println(".................\nPersonas x Teléfono:\n..............");
        for(Persona p: Controlador.getSingleton().getAllPersonas(new Comparator<Persona>() {
            @Override
            public int compare(Persona p1, Persona p2) {
                return p1.getsTelefono().compareTo(p2.getsTelefono());
            }
        })) {
            System.out.println(p.toString());
        }
    }


}