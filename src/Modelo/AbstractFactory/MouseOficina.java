package Modelo.AbstractFactory;

public class MouseOficina implements Mouse{
    @Override
    public String getDescripcion() {
        return "Mouse básico";
    }

    @Override
    public double precio() {
        return 300000;
    }
}
