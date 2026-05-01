package Modelo.builder;

public class DirectorComputador {
    public void construirPCGamer(ComputadorBuilder builder) {
        builder.setProcesador("Ryzen 9")
                .setRam("32GB")
                .setAlmacenamiento("1TB")
                .setGPU("RTX 4080")
                .setSO("Windows 11");
    }
}
