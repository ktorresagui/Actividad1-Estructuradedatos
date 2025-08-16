package DataStructure.LinkedList;

/**
 * Representa un contacto con nombre, dirección y teléfono.
 * Se usa como ejemplo de objeto complejo en la lista enlazada.
 */
public class Contactos {
    private String nombre;
    private String direccion;
    private String telefono;

    public Contactos(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + " | " + direccion + " | " + telefono;
    }
}
