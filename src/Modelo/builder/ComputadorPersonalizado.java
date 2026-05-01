package Modelo.builder;

import Modelo.factory.Computador;

public class ComputadorPersonalizado extends Computador {

    private String gpu;
    private String sistemaOperativo;

    public ComputadorPersonalizado() {
        this.nombre = "Computador Personalizado";
        this.tipo = "Personalizado";
        this.rutaImagen = "/img/laptop.jpg";
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public void setGPU(String gpu) {
        this.gpu = gpu;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String getEspecificaciones() {
        return procesador + " / " + ram + " / " + almacenamiento + " / " + gpu + " / " + sistemaOperativo;
    }
}
