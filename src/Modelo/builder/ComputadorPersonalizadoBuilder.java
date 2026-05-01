package Modelo.builder;

public class ComputadorPersonalizadoBuilder implements ComputadorBuilder {

    private ComputadorPersonalizado computador;

    public ComputadorPersonalizadoBuilder() {
        computador = new ComputadorPersonalizado();
    }

    @Override
    public ComputadorBuilder setProcesador(String procesador) {
        computador.setProcesador(procesador);
        return this;
    }

    @Override
    public ComputadorBuilder setRam(String ram) {
        computador.setRam(ram);
        return this;
    }

    @Override
    public ComputadorBuilder setAlmacenamiento(String almacenamiento) {
        computador.setAlmacenamiento(almacenamiento);
        return this;
    }

    @Override
    public ComputadorBuilder setGPU(String gpu) {
        computador.setGPU(gpu);
        return this;
    }

    @Override
    public ComputadorBuilder setSO(String so) {
        computador.setSistemaOperativo(so);
        return this;
    }

    @Override
    public ComputadorBuilder setPrecio(double precio) {
        computador.setPrecio(precio);
        return this;
    }

    @Override
    public ComputadorPersonalizado build() {
        return computador;
    }
}
