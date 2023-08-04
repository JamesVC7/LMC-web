
package Modelo;

/**
 *
 * @author james
 */
public class ItemCarrito {
    private boolean esPlato;
    private Plato plato;
    private Promocion promocion;
    private int cantidad;

    public ItemCarrito(boolean esPlato, Plato plato, Promocion promocion, int cantidad) {
        this.esPlato = esPlato;
        this.plato = plato;
        this.promocion = promocion;
        this.cantidad = cantidad;
    }

    public ItemCarrito() {
        
    }

    public boolean isEsPlato() {
        return esPlato;
    }

    public void setEsPlato(boolean esPlato) {
        this.esPlato = esPlato;
    }


    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
