package Modelo.factory;

public class ComputadorOficina extends Computador {
    public ComputadorOficina() {
        this.nombre = "Lenovo IdeaPad 3";
        this.tipo = "Oficina";
        this.procesador = "Intel Core i5";
        this.ram = "16 GB RAM";
        this.almacenamiento = "512 GB SSD";
        this.precio = 2500000;
        this.rutaImagen = "/img/lenovo-ideapad.jpg";
    }
}
