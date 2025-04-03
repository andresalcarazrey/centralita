package model;

public class Llamada {

    //Llamada: fecha, hora y dos personas (las personas son la composición)
    //Persona al ser una clase, encapsula varios datos a su vez
    //No es un tipo básico. Una llamada tiene dos personas, origen-destino
    //SIEMPRE son dos personas. Nunca son 4, o 7, o ...
    //Y SIEMPRE las necesito, si no se quién ha llamado a quién
    //Es la sutil diferencia entre Composición y Agregación
    //Composición: obliga a la clase a tener un número determinado
    //de antemano de objetos los que sea.

    //Esta clase es contenedora, de dos personas, pero como es composición
    // no es agregación, sólo necesita dos variables atributos
    // no cambia. OK????
    // Es decir, EN LA COMPOSICIÓN, usaremos variables atributos sencillos
    // Y podemos usar ARRAYS. Es la excepción. Aunque si queremos, por ejemplo,
    // que un coche tenga cuatro objetos "Rueda", es buena idea usar la List
    // por comodidad. Entendido???

    //Variable global de clase
    private static long codigo = 0;

    //Atributos básicos
    private long id;
    private String fecha;
    private String horaMinutos;
    private boolean bUrgente;

    //Atributos derivados
    private Persona origen;
    private Persona destino;

    public Llamada(String fecha, String horaMinutos, boolean bUrgente, Persona origen, Persona destino) {
        this.fecha = fecha;
        this.horaMinutos = horaMinutos;
        this.bUrgente = bUrgente;
        this.origen = origen;
        this.destino = destino;
        this.id = codigo;
        codigo++;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHoraMinutos() {
        return horaMinutos;
    }

    public boolean isbUrgente() {
        return bUrgente;
    }

    public Persona getOrigen() {
        return origen;
    }

    public Persona getDestino() {
        return destino;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "origen=" + origen +
                ", destino=" + destino +
                ", bUrgente=" + bUrgente +
                ", horaMinutos='" + horaMinutos + '\'' +
                ", fecha='" + fecha + '\'' +
                "}\n";
    }


}
