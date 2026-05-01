package Modelo.factory;

public class ComputadorGamer extends Computador {
    public ComputadorGamer() {
        this.nombre = "PC Gamer RTX";
        this.tipo = "Gaming";
        this.procesador = "Ryzen 7";
        this.ram = "32 GB RAM";
        this.almacenamiento = "1 TB SSD";
        this.precio = 5800000;
        this.rutaImagen = "/img/pc-gamer-rtx.jpg";
    }
}
