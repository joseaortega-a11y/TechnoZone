package Modelo.logica;

import Modelo.factory.Computador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private Cliente cliente;
    private Computador computador;
    private List<DetallePedido> perifericos;
    private double total;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.perifericos = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Computador getComputador() {
        return computador;
    }

    public List<DetallePedido> getPerifericos() {
        return Collections.unmodifiableList(perifericos);
    }

    public double getTotal() {
        return total;
    }

    public void setComputador(Computador computador) {
        this.computador = computador;
        this.total = computador.getPrecio();
    }

    public void agregarPeriferico(String descripcion, double precio) {
        perifericos.add(new DetallePedido(descripcion, precio));
        total += precio;
    }
}
