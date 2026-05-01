package Modelo.builder;

import Modelo.factory.Computador;

public interface ComputadorBuilder {
    ComputadorBuilder setProcesador(String procesador);
    ComputadorBuilder setRam(String ram);
    ComputadorBuilder setAlmacenamiento(String almacenamiento);
    ComputadorBuilder setGPU(String gpu);
    ComputadorBuilder setSO(String so);
    ComputadorBuilder setPrecio(double precio);

    Computador build();
}
