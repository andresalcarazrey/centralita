import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List; //importante: el IDE os pone el import
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
        listaLL.removeIf(n -> n.getId()==id); //SOLO UNA LINEA WOW!!!!



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

        //La edad me puede ya...
        //Arrays.asList es interesante también, cuando tenemos
        //que convertir una array puro y duro en List

        //Yo os dejaré siempre, al igual que en las empresas
        //Que consultéis las clases, interfaces de API Java
        //Eso sí, hay que tener los conceptos claros

        //Así no usamos ARRAYS. Que se me había pasado cambiarlo arriba en
        //la declaración.

        //Os voy a subir este código a repositorio para que lo clonéis,
        //Y arregléis vosotros los demás métodos del CRUD






        /*
        Llamada[] nuevaCopiaMala = new Llamada[listaLL.length];
        Llamada[] copiaBuena;

        int llamadasEncontradas = 0;
        for(int i =0;i<listaLL.length;i++) {
            if(listaLL[i]!=null) {
                nuevaCopiaMala[llamadasEncontradas] = listaLL[i];
                llamadasEncontradas++;
            }
        }

        copiaBuena = new Llamada[llamadasEncontradas];
        for(int i=0;i<llamadasEncontradas;i++) {
            copiaBuena[i] = nuevaCopiaMala[i];
        }
        return copiaBuena;

         */
    }

    public  Llamada[] listarUrgentes() {
        Llamada[] nuevaCopiaMala = new Llamada[listaLL.length];
        Llamada[] copiaBuena;

        int llamadasEncontradas = 0;
        for(int i =0;i<listaLL.length;i++) {
            if(listaLL[i]!=null && listaLL[i].isbUrgente()) {
                nuevaCopiaMala[llamadasEncontradas] = listaLL[i];
                llamadasEncontradas++;
            }
        }

        copiaBuena = new Llamada[llamadasEncontradas];
        for(int i=0;i<llamadasEncontradas;i++) {
            copiaBuena[i] = nuevaCopiaMala[i];
        }
        return copiaBuena;
    }

    public  Llamada buscarLlamada(long id) {
        for(int i =0;i<listaLL.length;i++) {
            if(listaLL[i].getId()==id) {
                return listaLL[i];
            }
        }
        return null;
    }

    public Llamada[] buscarLlamada(String nombre, boolean bDestinatario) {

        Llamada[] resultado = new Llamada[listaLL.length];
        int encontradas=0;
        for(int i =0;i<listaLL.length;i++) {
            if(bDestinatario) {
                if(listaLL[i].getDestino().getsNombre().compareTo(nombre)==0) {
                    resultado[encontradas] = listaLL[i];
                    encontradas++;
                }
            } else {
                if(listaLL[i].getOrigen().getsNombre().compareTo(nombre)==0) {
                    resultado[encontradas] = listaLL[i];
                    encontradas++;
                }

            }
        }

        Llamada[] bueno = new Llamada[encontradas];
        for(int i=0;i<encontradas;i++) {
            bueno[i] = resultado[i];
        }

        return bueno;
    }

    public boolean estaLlamada(long id) {
        return (this.buscarLlamada(id)!=null);
    }



}
