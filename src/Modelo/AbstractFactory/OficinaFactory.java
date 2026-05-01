package Modelo.AbstractFactory;

public class OficinaFactory implements PerifericoFactory{
    @Override
    public Teclado crearTeclado() {
        return new TecladoOficina();
    }

    @Override
    public Mouse crearMouse() {
        return new MouseOficina();
    }

    @Override
    public Monitor crearMonitor() {
        return new MonitorOficina();
    }
}
