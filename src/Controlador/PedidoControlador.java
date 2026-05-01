package Controlador;

import Modelo.AbstractFactory.*;
import Modelo.builder.*;
import Modelo.factory.*;
import Modelo.logica.*;

import java.util.ArrayList;
import java.util.List;

public class PedidoControlador {

    private ComputadorFactory factory;

    public PedidoControlador() {
        this.factory = new ComputadorFactoryImpl();
    }

    public List<Computador> obtenerComputadoresDestacados() {
        List<Computador> computadores = new ArrayList<>();

        computadores.add(factory.crearComputador("OFICINA"));
        computadores.add(factory.crearComputador("GAMER"));
        computadores.add(factory.crearComputador("DISENADOR"));
        computadores.add(factory.crearComputador("PROGRAMADOR"));

        return computadores;
    }

    public List<Computador> obtenerComputadoresPorCategoria(TipoCliente tipo) {
        List<Computador> computadores = new ArrayList<>();

        switch (tipo) {
            case GAMER:
                computadores.add(new ComputadorCatalogo("PC Gamer Alpha", "Gaming", "Ryzen 5", "16 GB RAM", "512 GB SSD", 4200000, "/img/pc-gamer-alpha.jpg"));
                computadores.add(new ComputadorCatalogo("PC Gamer Titan", "Gaming", "Ryzen 9", "32 GB RAM", "2 TB SSD", 7200000, "/img/pc-gamer-titan.jpg"));
                computadores.add(new ComputadorCatalogo("PC Gamer Nova", "Gaming", "Intel Core i7", "32 GB RAM", "1 TB SSD", 6100000, "/img/pc-gamer-nova.jpg"));
                break;

            case OFICINA:
                computadores.add(new ComputadorCatalogo("Dell Office Pro", "Oficina", "Intel Core i5", "8 GB RAM", "512 GB SSD", 2200000, "/img/dell-office-pro.jpg"));
                computadores.add(new ComputadorCatalogo("Lenovo Business", "Oficina", "Intel Core i5", "16 GB RAM", "512 GB SSD", 2600000, "/img/lenovo-business.jpg"));
                break;

            case DISENADOR:
                computadores.add(new ComputadorCatalogo("Workstation Creator", "Diseno", "Intel Core i7", "32 GB RAM", "1 TB SSD", 6100000, "/img/wokstation-creator.jpg"));
                computadores.add(new ComputadorCatalogo("HP Creator Studio", "Diseno", "Ryzen 7", "32 GB RAM", "2 TB SSD", 6800000, "/img/hp-creator-studio.jpg"));
                break;

            case PROGRAMADOR:
                computadores.add(new ComputadorCatalogo("Dev Mini Pro", "Programador", "Intel NUC", "32 GB RAM", "1 TB SSD", 3600000, "/img/dev-mini-pro.jpg"));
                computadores.add(new ComputadorCatalogo("Code Station", "Programador", "Intel Core i7", "32 GB RAM", "1 TB SSD", 4800000, "/img/code-station.jpg"));
                break;

            case ESTUDIANTE:
                computadores.add(new ComputadorCatalogo("Laptop Student Basic", "Estudiante", "Intel Core i5", "8 GB RAM", "256 GB SSD", 1700000, "/img/laptop-student-basic.jpg"));
                computadores.add(new ComputadorCatalogo("Laptop Student Plus", "Estudiante", "Intel Core i7", "16 GB RAM", "512 GB SSD", 2400000, "/img/laptop-student-plus.jpg"));
                break;
        }

        return computadores;
    }

    public Pedido crearPedidoPredefinido(Cliente cliente, TipoCliente tipo) {
        Pedido pedido = new Pedido(cliente);
        Computador computador = factory.crearComputador(tipo.name());

        pedido.setComputador(computador);

        return pedido;
    }

    public Pedido crearPedidoPersonalizado(Cliente cliente,
                                           String procesador,
                                           String ram,
                                           String almacenamiento,
                                           String gpu,
                                           String so,
                                           double precioBase) {
        Pedido pedido = new Pedido(cliente);

        ComputadorBuilder builder = new ComputadorPersonalizadoBuilder();

        Computador computador = builder.setProcesador(procesador)
                .setRam(ram)
                .setAlmacenamiento(almacenamiento)
                .setGPU(gpu)
                .setSO(so)
                .setPrecio(precioBase)
                .build();

        pedido.setComputador(computador);

        return pedido;
    }

    public void agregarPerifericosPorTipo(Pedido pedido, TipoCliente tipo) {
        PerifericoFactory perifericoFactory;

        switch (tipo) {
            case GAMER:
                perifericoFactory = new GamerFactory();
                break;

            case OFICINA:
            case DISENADOR:
            case PROGRAMADOR:
            case ESTUDIANTE:
            default:
                perifericoFactory = new OficinaFactory();
                break;
        }

        Teclado teclado = perifericoFactory.crearTeclado();
        Mouse mouse = perifericoFactory.crearMouse();
        Monitor monitor = perifericoFactory.crearMonitor();

        pedido.agregarPeriferico(teclado.getDescripcion(), teclado.precio());
        pedido.agregarPeriferico(mouse.getDescripcion(), mouse.precio());
        pedido.agregarPeriferico(monitor.getDescripcion(), monitor.precio());
    }
    public Pedido crearPedidoDesdeComputador(Cliente cliente, Computador computador) {
        Pedido pedido = new Pedido(cliente);
        pedido.setComputador(computador);
        return pedido;
    }

}
