package Modelo.factory;

public class ComputadorDisenador extends Computador {
    public ComputadorDisenador() {
        this.nombre = "HP All in One";
        this.tipo = "Diseno";
        this.procesador = "Intel Core i7";
        this.ram = "16 GB RAM";
        this.almacenamiento = "1 TB SSD";
        this.precio = 2900000;
        this.rutaImagen = "/img/all-in-one-hp.jpg";
    }
}
