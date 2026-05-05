package Modelo.AbstractFactory;

public class MonitorOficina implements Monitor{
    @Override
    public String getDescripcion() {
        return "Monitor 60Hz";
    }

    @Override
    public double precio() {
        return 300000;
    }
}
