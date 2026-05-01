package Modelo.factory;

public class ComputadorFactoryImpl implements ComputadorFactory {
    @Override
    public Computador crearComputador(String tipo) {
        switch (tipo.toUpperCase()) {
            case "GAMER":
                return new ComputadorGamer();
            case "OFICINA":
                return new ComputadorOficina();
            case "DISENADOR":
                return new ComputadorDisenador();
            case "PROGRAMADOR":
                return new ComputadorProgramador();
            case "ESTUDIANTE":
                return new ComputadorEstudiante();
            default:
                throw new IllegalArgumentException("Tipo no valido: " + tipo);
        }
    }
}
