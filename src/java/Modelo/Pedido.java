
package Modelo;

/**
 *
 * @author james
 */
public class Pedido {
    private int id_pedido;
    private int id_detalle;
    private Integer id_plato; 
    private Integer id_promo;
    private String nombre_plato; 
    private String nombre_promo;

    public Pedido() {
    }

    public Pedido(int id_pedido, int id_detalle, Integer id_plato, Integer id_promo) {
        this.id_pedido = id_pedido;
        this.id_detalle = id_detalle;
        this.id_plato = id_plato;
        this.id_promo = id_promo;
    }

    public Pedido(int id_detalle, Integer id_plato, Integer id_promo, String nombre_plato, String nombre_promo) {
        this.id_detalle = id_detalle;
        this.id_plato = id_plato;
        this.id_promo = id_promo;
        this.nombre_plato = nombre_plato;
        this.nombre_promo = nombre_promo;
    }

    
    public Pedido(int id_pedido, int id_detalle, Integer id_plato, Integer id_promo, String nombre_plato, String nombre_promo) {
        this.id_pedido = id_pedido;
        this.id_detalle = id_detalle;
        this.id_plato = id_plato;
        this.id_promo = id_promo;
        this.nombre_plato = nombre_plato;
        this.nombre_promo = nombre_promo;
    }
    
    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public Integer getId_plato() {
        return id_plato;
    }

    public void setId_plato(Integer id_plato) {
        this.id_plato = id_plato;
    }

    public Integer getId_promo() {
        return id_promo;
    }

    public void setId_promo(Integer id_promo) {
        this.id_promo = id_promo;
    }
    
    public String getNombre_plato() {
        return nombre_plato;
    }

    public void setNombre_plato(String nombre_plato) {
        this.nombre_plato = nombre_plato;
    }

    public String getNombre_promo() {
        return nombre_promo;
    }

    public void setNombre_promo(String nombre_promo) {
        this.nombre_promo = nombre_promo;
    }
    
}
