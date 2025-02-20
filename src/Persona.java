public class Persona implements Comparable<Persona> {

    // Esta clase es POJO (pequeña y no contenedora). No cambia

    private String sNombre;
    private String sApellidos;
    private String sTelefono;
    private String sEmail;
    private String sDni;

    public Persona(String sNombre, String sApellidos, String sTelefono, String sEmail, String sDni) {
        this.sNombre = sNombre;
        this.sApellidos = sApellidos;
        this.sTelefono = sTelefono;
        this.sEmail = sEmail;
        this.sDni = sDni;
    }

    public String getsNombre() {
        return sNombre;
    }

    public String getsApellidos() {
        return sApellidos;
    }

    public String getsTelefono() {
        return sTelefono;
    }

    public String getsEmail() {
        return sEmail;
    }

    public String getsDni() {
        return sDni;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "sNombre='" + sNombre + '\'' +
                ", sApellidos='" + sApellidos + '\'' +
                ", sTelefono='" + sTelefono + '\'' +
                ", sEmail='" + sEmail + '\'' +
                ", sDni='" + sDni + '\'' +
                '}';
    }

    @Override
    public int compareTo(Persona aP) {
        return (this.getsApellidos()+this.getsNombre()).compareTo(aP.getsApellidos()+aP.getsNombre());
    }
}
