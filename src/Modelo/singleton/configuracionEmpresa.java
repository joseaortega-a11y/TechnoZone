package Modelo.singleton;

public class configuracionEmpresa {
    private static configuracionEmpresa instancia;

    private String nombreEmpresa;
    private String nit;

    private configuracionEmpresa() {
        nombreEmpresa = "TechZone Solutions";
        nit = "1823465";

    }

    public static configuracionEmpresa getInstancia(){
        {
            if (instancia == null) {
                instancia = new configuracionEmpresa();
            }
            return instancia;
        }
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getNit() {
        return nit;
    }
}
