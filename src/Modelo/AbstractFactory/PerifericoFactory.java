package Modelo.AbstractFactory;

public interface PerifericoFactory {
    Teclado crearTeclado();
    Mouse crearMouse();
    Monitor crearMonitor();
}
