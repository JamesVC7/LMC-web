package Modelo;

import java.util.Date;

/**
 *
 * @author james
 */
public class Cliente {
    private int id_cliente;
    private String Nombre;
    private String Apellido;
    private int Celular;
    private Date Fecha_Nacimiento;
    private String direccion;
    private String correo;
    private String clave;

    public Cliente() {
    }

    public Cliente(int id_cliente, String Nombre, String Apellido, int Celular, Date Fecha_Nacimiento, String direccion, String correo, String clave) {
        this.id_cliente = id_cliente;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Celular = Celular;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.direccion = direccion;
        this.correo = correo;
        this.clave = clave;
    }

    public Cliente(int id_cliente, String Nombre, String Apellido, int Celular, String direccion, String correo) {
        this.id_cliente = id_cliente;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Celular = Celular;
        this.direccion = direccion;
        this.correo = correo;
    }

    

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public int getCelular() {
        return Celular;
    }

    public void setCelular(int Celular) {
        this.Celular = Celular;
    }

    public Date getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(Date Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
}
