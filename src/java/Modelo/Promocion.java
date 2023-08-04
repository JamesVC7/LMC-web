
package Modelo;

import java.util.Objects;

public class Promocion {
    private int IdPromocion;
    private String nombre_promocion;
    private float precio;
    private int cantidad;
    private String descripcion;
    private byte[] imagen;
    
    public Promocion() {

    }

    public Promocion(int IdPromocion, String nombre_promocion, String descripcion, float precio, int cantidad,  byte[] imagen) {
        this.IdPromocion = IdPromocion;
        this.nombre_promocion = nombre_promocion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public int getIdPromocion() {
        return IdPromocion;
    }

    public void setIdPromocion(int IdPromocion) {
        this.IdPromocion = IdPromocion;
    }

    public String getNombre_promocion() {
        return nombre_promocion;
    }

    public void setNombre_promocion(String nombre_promocion) {
        this.nombre_promocion = nombre_promocion;
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
        Promocion promocion = (Promocion) obj;
        return IdPromocion == promocion.IdPromocion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdPromocion);
    }
}
