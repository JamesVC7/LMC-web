
package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author james
 */
public class Detalle  implements Serializable{
    
    private int id_detalle;
    private int id_cliente;
    private String tipo_entrega;
    private Date fecha;
    private double total;

    public Detalle() {
    }

    public Detalle(int id_detalle, int id_cliente, String tipo_entrega, Date fecha, double total) {
        this.id_detalle = id_detalle;
        this.id_cliente = id_cliente;
        this.tipo_entrega = tipo_entrega;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getTipo_entrega() {
        return tipo_entrega;
    }

    public void setTipo_entrega(String tipo_entrega) {
        this.tipo_entrega = tipo_entrega;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
