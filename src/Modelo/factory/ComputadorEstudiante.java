package Modelo.factory;

public class ComputadorEstudiante extends Computador {
    public ComputadorEstudiante() {
        this.nombre = "Laptop Estudiante";
        this.tipo = "Estudiante";
        this.procesador = "Intel Core i3";
        this.ram = "8 GB RAM";
        this.almacenamiento = "256 GB SSD";
        this.precio = 1800000;
        this.rutaImagen = "/img/laptop.jpg";
    }
}
