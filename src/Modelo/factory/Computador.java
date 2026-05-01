package Modelo.factory;

public abstract class Computador {
    protected String nombre;
    protected String tipo;
    protected String procesador;
    protected String ram;
    protected String almacenamiento;
    protected String rutaImagen;
    protected double precio;

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getProcesador() {
        return procesador;
    }

    public String getRam() {
        return ram;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public double getPrecio() {
        return precio;
    }

    public String getEspecificaciones() {
        return procesador + " / " + ram + " / " + almacenamiento;
    }

    @Override
    public String toString() {
        return nombre + " - " + getEspecificaciones() + " - $" + precio;
    }
}
