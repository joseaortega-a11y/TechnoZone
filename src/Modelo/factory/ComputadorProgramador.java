package Modelo.factory;

public class ComputadorProgramador extends Computador {
    public ComputadorProgramador() {
        this.nombre = "Intel Mini PC";
        this.tipo = "Programador";
        this.procesador = "Intel NUC";
        this.ram = "16 GB RAM";
        this.almacenamiento = "1 TB SSD";
        this.precio = 1950000;
        this.rutaImagen = "/img/mini-pc-intel.jpg";
    }
}
