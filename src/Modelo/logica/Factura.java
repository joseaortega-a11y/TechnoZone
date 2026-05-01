package Modelo.logica;

import Modelo.singleton.configuracionEmpresa;

public class Factura {

    private Pedido pedido;

    public Factura(Pedido pedido) {
        this.pedido = pedido;
    }

    public String generarTextoFactura() {
        configuracionEmpresa config = configuracionEmpresa.getInstancia();

        StringBuilder texto = new StringBuilder();

        texto.append("===== FACTURA =====\n");
        texto.append("Empresa: ").append(config.getNombreEmpresa()).append("\n");
        texto.append("NIT: ").append(config.getNit()).append("\n");
        texto.append("-----------------------------\n");

        texto.append("Cliente: ").append(pedido.getCliente().getNombre()).append("\n");
        texto.append("Identificacion: ").append(pedido.getCliente().getIdentificacion()).append("\n");
        texto.append("Tipo cliente: ").append(pedido.getCliente().getTipo()).append("\n");
        texto.append("-----------------------------\n");

        texto.append("Computador:\n");
        texto.append("Nombre: ").append(pedido.getComputador().getNombre()).append("\n");
        texto.append("Tipo: ").append(pedido.getComputador().getTipo()).append("\n");
        texto.append("Especificaciones: ").append(pedido.getComputador().getEspecificaciones()).append("\n");
        texto.append("Precio: $").append(formatearPrecio(pedido.getComputador().getPrecio())).append("\n");
        texto.append("-----------------------------\n");

        if (!pedido.getPerifericos().isEmpty()) {
            texto.append("Accesorios / perifericos:\n");

            for (DetallePedido detalle : pedido.getPerifericos()) {
                texto.append("- ")
                        .append(detalle.getDescripcion())
                        .append(": $")
                        .append(formatearPrecio(detalle.getPrecio()))
                        .append("\n");
            }

            texto.append("-----------------------------\n");
        }

        texto.append("TOTAL: $").append(formatearPrecio(pedido.getTotal())).append("\n");

        return texto.toString();
    }

    private String formatearPrecio(double precio) {
        return String.format("%,.0f", precio).replace(",", ".");
    }
}
