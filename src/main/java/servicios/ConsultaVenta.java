package servicios;

import entidades.Venta;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class ConsultaVenta {

    private List<Venta> ventas;

    public ConsultaVenta(List<Venta> ventas) {
        this.ventas = ventas;
    }

    //Devuelve las ventas con importe total mayor a 100@
    public List<Venta> getVentasMayor100(){
        return ventas.stream()
                .filter(venta -> venta.getTotalLinea() > 100)
                .sorted(Comparator.comparing(Venta::getTotalLinea).reversed())
                .toList();
    }

    //Devuelve las ventas de la categoria Electronics
    public  List<Venta> getVentasByCategoriaElectronica(){
        return ventas.stream()
                .filter(v -> v.getCategoria().equals("Electronics"))
                .sorted(Comparator.comparing(Venta::getFecha))
                .toList();
    }

    //Devuelve los productos vendidos ordenados por nombre. Sin repetidos
    public List<String> getProductosVendidosOrder(){
        return ventas.stream()
                .map(Venta::getProducto)
                .distinct()
                .sorted()
                .toList();
    }

    //Devuelve la primera venta de España
    public Optional<Venta> getPrimeraVentaSpain(){
        return ventas.stream()
                .filter(v -> v.getPais().equals("Spain"))
                .findFirst();
    }

    //Devuelve el Top 10 ventas por importe total
    public List<Venta> getTop10VentasPorImporteTotal(){
        return ventas.stream()
                .sorted(Comparator.comparing(Venta::getTotalLinea).reversed())
                .limit(10)
                .toList();
    }

    //Devuelve el importe total de las ventas
    public double getFacturacionTotal(){
        return ventas.stream()
                .mapToDouble(Venta::getTotalLinea)
                .sum();
    }

    //Estadisticas de precio unitario
    public DoubleSummaryStatistics getEstadisticasPrecioUnitario(){
        return ventas.stream()
                .collect(Collectors.summarizingDouble(Venta::getPrecioUnitario));
    }

    //Devuelve las ventas agrupadas por categoria contando cuantas hay de cada una
    public Map<String, Long> getNumeroDeVentasPorCategoria(){
        return ventas.stream()
                .collect(Collectors.groupingBy(Venta::getCategoria, Collectors.counting()));
    }

    //Ventas agrupadas por pais. Muestra pais y total facturacion de ese pais
    public Map<String, Double> getFacturacionTotalPorPais(){
        return  ventas.stream()
                .collect(Collectors.groupingBy(Venta::getPais, Collectors.summingDouble(Venta::getTotalLinea)));
    }

    //Devuelve las ventas agrupadas por metodo de pago, contando cuantas hay de cada una
    public Map<String, Long> getnNumeroVentasPorMetodoPago(){
        return ventas.stream()
                .collect(Collectors.groupingBy(Venta::getMetodoPago, Collectors.counting()));
    }

    //Facturacion total por categoria
    public Map<String, Double> getFacturacionTotalPorCtegoria(){
        return ventas.stream()
                .collect(Collectors.groupingBy(Venta::getCategoria, Collectors.summingDouble(Venta::getTotalLinea)));
    }

    //Ventas agrupadas por mes
    public Map<String, List<Venta>> getVentasAgrupadasPorMes(){
        return ventas.stream()
                .collect(Collectors.groupingBy(v -> v.getFecha().getMonth().toString()));
    }

    //Indica si todas las ventas por bizum son menores de 200€
    public boolean getTodasVentasBizumMenor200(){
        return ventas.stream()
                .allMatch(v -> v.getMetodoPago().equals("BIZUM") && v.getTotalLinea() < 200);
    }

    //Porcentaje de ventas con tarjeta
    public double getPorcentajeVentasTarjeta() {
        long totalTarjeta = ventas.stream()
                .filter(v -> v.getMetodoPago().equals("TARJETA"))
                .count();
        return ((double) totalTarjeta / ventas.size()) * 100.0;
    }
}
