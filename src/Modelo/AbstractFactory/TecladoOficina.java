package Modelo.AbstractFactory;

public class TecladoOficina implements Teclado{
    @Override
    public String getDescripcion() {
        return "Teclado Estándar";
    }

    @Override
    public double precio() {
        return 60;
    }
}
