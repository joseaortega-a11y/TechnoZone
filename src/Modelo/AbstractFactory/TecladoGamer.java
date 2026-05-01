package Modelo.AbstractFactory;

public class TecladoGamer implements Teclado{

    @Override
    public String getDescripcion() {
        return "Teclado Mecánico RGB";
    }

    @Override
    public double precio() {
        return 200;
    }
}

