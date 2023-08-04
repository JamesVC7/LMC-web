
package Modelo;

/**
 *
 * @author james
 */
public class Admin {
    
    private int id_admin;
    private String nombre;
    private String clave;
    private byte[] imagen;
    
    public Admin() {

    }

    public Admin(int id_admin, String nombre, String clave, byte[] imagen) {
        this.id_admin = id_admin;
        this.nombre = nombre;
        this.clave = clave;
        this.imagen = imagen;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    
    
}
