package Modelo.logica;

public class Cliente {
    private String nombre;
    private String identificacion;
    private TipoCliente tipo;

    public Cliente(String nombre, String identificacion, TipoCliente tipo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    @Override
    public String toString(){
        return nombre + "(" + tipo + ")";
    }
}
