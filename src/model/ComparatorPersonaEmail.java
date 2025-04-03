package model;

import java.util.Comparator;

public class ComparatorPersonaEmail implements Comparator<Persona> {

    //Atributos???

    //¿? Constructor¿?

    //Métodos implementación

    @Override
    public int compare(Persona p1, Persona p2) {
        return p1.getsEmail().compareTo(p2.getsEmail());
    }
}
