package Modelo.AbstractFactory;

public class MouseGamer implements Mouse{

    @Override
    public String getDescripcion() {
        return "Mouse Gamer RGB";
    }

    @Override
    public double precio() {
        return 150;
    }
}
