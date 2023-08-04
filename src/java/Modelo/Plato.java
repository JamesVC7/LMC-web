package Modelo;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;

public class Plato implements Serializable {

    private int id_plato;
    private int id_categoria;
    private String nombre_plato;
    private float precio;
    private int cantidad;
    private String descripcion;
    private byte[] imagen;

    public Plato() {

    }

    public Plato(int id_plato, int id_categoria, String nombre_plato, float precio, int cantidad, String descripcion, byte[] imagen) {
        this.id_plato = id_plato;
        this.id_categoria = id_categoria;
        this.nombre_plato = nombre_plato;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getId_plato() {
        return id_plato;
    }

    public void setId_plato(int id_plato) {
        this.id_plato = id_plato;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_plato() {
        return nombre_plato;
    }

    public void setNombre_plato(String nombre_plato) {
        this.nombre_plato = nombre_plato;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Plato plato = (Plato) obj;
        return id_plato == plato.id_plato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_plato);
    }
}
