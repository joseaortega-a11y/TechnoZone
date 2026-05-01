package Modelo.AbstractFactory;

public class GamerFactory implements PerifericoFactory{
    @Override
    public Teclado crearTeclado() {
        return new TecladoGamer();
    }

    @Override
    public Mouse crearMouse() {
        return new MouseGamer();
    }

    @Override
    public Monitor crearMonitor() {
        return new MonitorGamer();
    }
}
