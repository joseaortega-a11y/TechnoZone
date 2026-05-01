package Modelo.AbstractFactory;

public class MonitorGamer implements Monitor{
    @Override
    public String getDescripcion() {
        return "Monitor 144Hz";
    }

    @Override
    public double precio() {
        return 800;
    }
}
