import java.lang.reflect.Array;
import java.util.*;
// de manera automática. Pero fijaros,
                        //estamos usando API Java.

public class Entidad {
    //Ya no necesito un máximo. No hay arrays
    //private static final int MAX_LLAMADAS = 600;

    //Aquí está el follón. Vamos a simplificar
    //Donde aparezca el atributo listaLL, hay que cambiar cosas

    private String nombre;
    private String id;
    private String direccion;

    //Atributos Derivados
    private List<Llamada> listaLL;
    //private int numLlamadasAlmacenadas;

    public Entidad(String nombre, String id, String direccion) {
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
        //Ahora no tenemos un array
        //listaLL = new Llamada[MAX_LLAMADAS];
        //Qué tamaño tendrá?? Ya no me preocupa, crece el solito...
        listaLL = new LinkedList<>(); //Apuesto por LinkedList
                                    //para hacer las inserciones/eliminaciones rápidas
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    //Sólo los AB

    @Override
    public String toString() {
        return "Entidad{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }


    //CRUD Llamada. Aquí empiezan las curvas. Pero suaves
    public boolean altaLlamada(Llamada unaLL) {
       if(unaLL == null) return false; //SE queda
       if(buscarLlamada(unaLL.getId()) != null) return false; //Se queda

       if (listaLL.contains(unaLL)) {
           return false;
       } else {
           listaLL.add(unaLL);
       }  //Sólo una condicional
       return true;
       //¿Dónde está el bucle? Raul Astorga...
        //está dentro de... la implementación encapsulada de... CONTAINS
        //Contains por INTUICIÓN tendrá que mirar si alguno de los objetos
        //añadidos ANTES es nuestra unaLL OK? Eso lo hace con un for.
        //Pero de pronto: Nosotros no usamos el for, nos lo dan. YAHOO!!!


        //Antes: bucle con condicional
        //más facilidad de equivocarnos...
       /*for(int i=0;i<listaLL.length;i++) {
           if(listaLL[i]==null) {
               listaLL[i]=unaLL;
               return true;
           }
       }*/

        //SE entiende!!!!???????
    }


    //Pablo Guerrero. Dime que cambio
    public boolean bajaLlamada(long id) {
        //Esta forma también me vale, por ahora...
        /*for(Llamada unaDeLasLlamadas: listaLL) { //Hazlo en modo PRO. For each. Ya somos grandes
            if(unaDeLasLlamadas.getId()==id) {
                listaLL.remove(unaDeLasLlamadas);
                return true;
            }
        }
        return false;*/
        //Al principio es más difícil de imaginar
        return listaLL.removeIf(n -> n.getId()==id); //SOLO UNA LINEA WOW!!!!



        //Con este tipo de programación, los bucles y condicionales se ocultan
        //Le decimos a este tipo de métodos, QUE queremos, no COMO
        //Aún así, Cristian tiene razoń. Es más lento. Pero es más seguro, menos
        //líneas de código, menos errores (probabilidad)

        //Se puede usar remove directamente??????
        //Cual remove


        //Bravo!
        /*for(int i=0;i<listaLL.length;i++) {
            if(listaLL[i].getId()==id) {
                listaLL[i]=null;
                return true;
            }
        }
        return false;*/
    }

    //Venga, que lo haga Pablo Perez. Hoy va de Pablosss
    public List<Llamada> listarTodas() {
        //Modo Pro ON!
        //Hay que devolverlas todas. Una List tiene un método para devolver
        //todo el contenido en forma de Array
        //¿cuántas líneas te imaginas que vamos a usar?
        //Ups. Esta vez la gente de Java nos lo pone un pelín escondido
        return new ArrayList<>(listaLL); //Creo una nueva lista con la mía
        //Crear una copia es buena idea para no dejar la lista original
        //en manos de nadie



    }

    public  List<Llamada> listarUrgentes() {
        ArrayList<Llamada> listaUrgentes = new ArrayList<>();
        for(Llamada ll:listaLL) {
            if(ll.isbUrgente()){
                listaUrgentes.add(ll);
            }
        }
        return listaUrgentes;
    }

    public  Llamada buscarLlamada(long id) {
        for(Llamada ll:listaLL)  {
            if(ll.getId()==id) {
                return ll;
            }
        }
        return null;
    }

    public List<Llamada> buscarLlamada(String nombre, boolean bDestinatario) {

        List<Llamada> resultadoList = new ArrayList<>();
        for(Llamada ll:listaLL) {
            Persona p;
            if(bDestinatario) {
                p = ll.getDestino();
            } else {
                p = ll.getOrigen();

            }

            if(p.getsNombre().equals(nombre)) {
                resultadoList.add(ll);
            }
        }
        return resultadoList;
    }

    public boolean estaLlamada(long id) {
        return (this.buscarLlamada(id)!=null);
    }

    public List<Persona> getAllPersonas() {
        Map<String,Persona> mapaPersonas = new HashMap<>();

        for (Llamada ll:this.listaLL) {
            mapaPersonas.put(ll.getOrigen().getsDni(),ll.getOrigen());
            mapaPersonas.put(ll.getDestino().getsDni(),ll.getDestino());

        }
        ArrayList<Persona> resultado = new ArrayList<Persona>(mapaPersonas.values());
        Collections.sort(resultado);
        return resultado;

    }



}
