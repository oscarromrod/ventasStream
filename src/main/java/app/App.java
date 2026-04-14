package app;

import entidades.Venta;
import servicios.ConsultaVenta;
import utilidades.CsvLoader;

import java.util.List;

public class App {
    static void main() {

        //Cargar fichero ventas.csv
        List<Venta> ventas = CsvLoader.cargarVentas();
        ventas.forEach(System.out::println);

        //Crear servicio COnsultasVenta
        ConsultaVenta consVenta = new ConsultaVenta(ventas);

        //Hacer consultas
        IO.println("1. -> getVentasMayor100");
        consVenta.getVentasMayor100()
                .forEach(System.out::println);

        IO.println("2. -> getVentasByCategoriaElectronica");
        consVenta.getVentasByCategoriaElectronica()
                        .forEach(System.out::println);

        IO.println("3. -> getProductosVendidosOrder");
        consVenta.getProductosVendidosOrder()
                        .forEach(System.out::println);

        IO.println("4. -> getPrimeraVentaSpain");
        consVenta.getPrimeraVentaSpain()
                        .ifPresent(System.out::println);

        IO.println("5. -> getTop10VentasPorImporteTotal");
        consVenta.getTop10VentasPorImporteTotal()
                .forEach(System.out::println);

        IO.println("6. -> getFacturacionTotal");
        IO.println(consVenta.getFacturacionTotal());

        IO.println("7. -> getEstadisticasPrecioUnitario");
        IO.println(consVenta.getEstadisticasPrecioUnitario());

        IO.println("8. -> getNumeroDeVentasPorCategoria");
        consVenta.getNumeroDeVentasPorCategoria().forEach((k, v) -> IO.println(k + ": " + v));

        IO.println("9. -> getFacturacionTotalPorPais");
        consVenta.getFacturacionTotalPorPais().forEach((k, v) -> IO.println(k + ": " + v + "€"));

        IO.println("10. -> getnNumeroVentasPorMetodoPago");
        consVenta.getnNumeroVentasPorMetodoPago().forEach((k, v) -> IO.println(k + ": " + v));

        IO.println("11. -> getFacturacionTotalPorCtegoria");
        consVenta.getFacturacionTotalPorCtegoria().forEach((k, v) -> IO.println(k + ": " + v));

        IO.println("12. -> getVentasAgrupadasPorMes");
        consVenta.getVentasAgrupadasPorMes().forEach((k, v) -> IO.println(k + ": " + v.size() + " ventas"));

        IO.println("14. -> getTodasVentasBizumMenor200");
        IO.println(consVenta.getTodasVentasBizumMenor200());

        IO.println("15. -> getPorcentajeVentasTarjeta");
        IO.println(consVenta.getPorcentajeVentasTarjeta() + "%");

    }
}
