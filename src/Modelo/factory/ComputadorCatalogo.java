package Modelo.factory;

public class ComputadorCatalogo extends Computador {

    public ComputadorCatalogo(String nombre,
                              String tipo,
                              String procesador,
                              String ram,
                              String almacenamiento,
                              double precio,
                              String rutaImagen) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.procesador = procesador;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }
}
