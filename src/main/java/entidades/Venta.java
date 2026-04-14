package entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@AllArgsConstructor
@Data

public class Venta {

    private long id;
    private String producto;
    private String categoria;
    private String pais;
    private int cantidad;
    private double precioUnitario;
    private LocalDate fecha;
    private String metodoPago;

    public double getTotalLinea(){
        return this.cantidad * this.precioUnitario;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ID: ");
        sb.append(id);
        sb.append(" | ").append(producto);
        sb.append(" | ").append(categoria);
        sb.append(" | ").append(pais);
        sb.append(" | ").append(cantidad).append(" uds x ").append(precioUnitario).append(" = ").append(getTotalLinea());
        sb.append(" | ").append(fecha);
        sb.append(" | ").append(metodoPago);
        return sb.toString();
    }
}
