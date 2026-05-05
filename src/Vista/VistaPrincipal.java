package Vista;

import Controlador.PedidoControlador;
import Modelo.factory.Computador;
import Modelo.logica.Cliente;
import Modelo.logica.DetallePedido;
import Modelo.logica.Factura;
import Modelo.logica.Pedido;
import Modelo.logica.TipoCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VistaPrincipal extends JFrame {

    private static final String PLACEHOLDER_BUSQUEDA = "Buscar computadores...";

    private JPanel rootPanel;
    private JPanel panelEncabezado;
    private JPanel panelContenido;
    private JPanel panelLogo;
    private JPanel panelBusqueda;
    private JPanel panelUsuario;
    private JPanel panelInicio;
    private JPanel panelProductos;

    private JLabel logoCompletoLabel;
    private JLabel lblTitulo;
    private JTextField txtBuscar;
    private JButton btnCategorias;
    private JButton btnPersonalizar;
    private JButton btnCarrito;
    private JButton btnCuenta;

    private PedidoControlador controlador;
    private Cliente clienteActual;
    private List<Pedido> carrito;
    private List<Computador> computadoresDisponibles;

    public VistaPrincipal() {
        controlador = new PedidoControlador();
        clienteActual = new Cliente("Usuario invitado", "203457", TipoCliente.ESTUDIANTE);
        carrito = new ArrayList<>();
        computadoresDisponibles = new ArrayList<>();

        construirInterfaz();
        activarClickFueraDelBuscador();

        setContentPane(rootPanel);
        setTitle("TechnoZone Solutions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 760);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void construirInterfaz() {
        rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(Color.WHITE);
        rootPanel.setFocusable(true);

        construirEncabezado();

        panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(Color.WHITE);

        construirContenidoInicio();

        rootPanel.add(panelEncabezado, BorderLayout.NORTH);
        rootPanel.add(panelContenido, BorderLayout.CENTER);
    }

    private void construirEncabezado() {
        panelEncabezado = new JPanel(new BorderLayout());
        panelEncabezado.setPreferredSize(new Dimension(0, 110));
        panelEncabezado.setBackground(Color.WHITE);
        panelEncabezado.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(229, 231, 235)));

        panelLogo = new JPanel(new GridBagLayout());
        panelLogo.setPreferredSize(new Dimension(420, 110));
        panelLogo.setBackground(Color.WHITE);

        logoCompletoLabel = new JLabel();
        cargarLogo();
        panelLogo.add(logoCompletoLabel);

        panelBusqueda = new JPanel(new GridBagLayout());
        panelBusqueda.setBackground(Color.WHITE);

        txtBuscar = new JTextField();
        configurarBuscador();
        panelBusqueda.add(txtBuscar);

        panelUsuario = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 36));
        panelUsuario.setPreferredSize(new Dimension(520, 110));
        panelUsuario.setBackground(Color.WHITE);

        btnCategorias = new JButton("Categorias");
        btnPersonalizar = new JButton("Personalizar");
        btnCarrito = new JButton("Carrito (0)");
        btnCuenta = new JButton("Mi cuenta");

        configurarBotonHeader(btnCategorias);
        configurarBotonHeader(btnPersonalizar);
        configurarBotonHeader(btnCarrito);
        configurarBotonHeader(btnCuenta);

        btnCategorias.addActionListener(e -> seleccionarCategoria());
        btnPersonalizar.addActionListener(e -> mostrarVentanaPersonalizar());
        btnCarrito.addActionListener(e -> mostrarCarrito());
        btnCuenta.addActionListener(e -> mostrarClienteActual());

        panelUsuario.add(btnCategorias);
        panelUsuario.add(btnPersonalizar);
        panelUsuario.add(btnCarrito);
        panelUsuario.add(btnCuenta);

        panelEncabezado.add(panelLogo, BorderLayout.WEST);
        panelEncabezado.add(panelBusqueda, BorderLayout.CENTER);
        panelEncabezado.add(panelUsuario, BorderLayout.EAST);
    }

    private void construirContenidoInicio() {
        panelInicio = new JPanel(new BorderLayout(0, 22));
        panelInicio.setBackground(Color.WHITE);
        panelInicio.setBorder(BorderFactory.createEmptyBorder(32, 42, 32, 42));

        lblTitulo = new JLabel("Computadores destacados");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(17, 24, 39));

        panelProductos = new JPanel(new GridLayout(1, 4, 22, 22));
        panelProductos.setBackground(Color.WHITE);

        computadoresDisponibles = controlador.obtenerComputadoresDestacados();
        mostrarProductos(computadoresDisponibles);

        panelInicio.add(lblTitulo, BorderLayout.NORTH);
        panelInicio.add(panelProductos, BorderLayout.CENTER);

        panelContenido.add(panelInicio, BorderLayout.CENTER);
    }

    private void mostrarProductos(List<Computador> computadores) {
        panelProductos.removeAll();

        if (computadores.isEmpty()) {
            JLabel lblSinResultados = new JLabel("No se encontraron productos.");
            lblSinResultados.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            lblSinResultados.setForeground(new Color(107, 114, 128));
            lblSinResultados.setHorizontalAlignment(SwingConstants.CENTER);

            panelProductos.setLayout(new BorderLayout());
            panelProductos.add(lblSinResultados, BorderLayout.CENTER);
        } else {
            panelProductos.setLayout(new GridLayout(1, 4, 22, 22));

            for (Computador computador : computadores) {
                panelProductos.add(crearTarjetaProducto(computador));
            }
        }

        panelProductos.revalidate();
        panelProductos.repaint();
    }

    private JPanel crearTarjetaProducto(Computador computador) {
        JPanel tarjeta = new JPanel(new BorderLayout(0, 12));
        tarjeta.setBackground(new Color(248, 250, 252));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(190, 140));

        java.net.URL url = getClass().getResource(computador.getRutaImagen());

        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image image = icon.getImage().getScaledInstance(180, 125, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(image));
        } else {
            lblImagen.setText("Sin imagen");
            lblImagen.setForeground(new Color(107, 114, 128));
        }

        JLabel lblNombre = new JLabel(computador.getNombre());
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNombre.setForeground(new Color(17, 24, 39));

        JLabel lblTipo = new JLabel(computador.getTipo());
        lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTipo.setForeground(new Color(37, 99, 235));

        JLabel lblSpecs = new JLabel("<html>" + computador.getEspecificaciones() + "</html>");
        lblSpecs.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSpecs.setForeground(new Color(75, 85, 99));

        JLabel lblPrecio = new JLabel(formatearPrecio(computador.getPrecio()));
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblPrecio.setForeground(new Color(22, 163, 74));

        JButton btnAgregar = new JButton("Agregar");
        configurarBotonProducto(btnAgregar);
        btnAgregar.addActionListener(e -> agregarAlCarrito(computador));

        JPanel panelInfo = new JPanel(new GridLayout(4, 1, 0, 4));
        panelInfo.setOpaque(false);
        panelInfo.add(lblNombre);
        panelInfo.add(lblTipo);
        panelInfo.add(lblSpecs);
        panelInfo.add(lblPrecio);

        tarjeta.add(lblImagen, BorderLayout.NORTH);
        tarjeta.add(panelInfo, BorderLayout.CENTER);
        tarjeta.add(btnAgregar, BorderLayout.SOUTH);

        return tarjeta;
    }

    private void seleccionarCategoria() {
        String[] categorias = {"GAMER", "OFICINA", "DISENADOR", "PROGRAMADOR", "ESTUDIANTE"};

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona una categoria:",
                "Categorias",
                JOptionPane.QUESTION_MESSAGE,
                null,
                categorias,
                categorias[0]
        );

        if (seleccion == null) {
            return;
        }

        TipoCliente tipo = TipoCliente.valueOf(seleccion);
        mostrarVentanaCategoria(tipo);
    }

    private void mostrarVentanaCategoria(TipoCliente tipo) {
        JDialog dialogo = new JDialog(this, "Categoria: " + tipo, true);
        dialogo.setSize(980, 620);
        dialogo.setLocationRelativeTo(this);

        JPanel panelPrincipal = new JPanel(new BorderLayout(0, 22));
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 38, 30, 38));

        JLabel titulo = new JLabel("Categoria: " + tipo);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(17, 24, 39));

        JPanel panelCategoriaProductos = new JPanel(new GridLayout(1, 3, 22, 22));
        panelCategoriaProductos.setBackground(Color.WHITE);

        for (Computador computador : controlador.obtenerComputadoresPorCategoria(tipo)) {
            panelCategoriaProductos.add(crearTarjetaProducto(computador));
        }

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCategoriaProductos, BorderLayout.CENTER);

        dialogo.setContentPane(panelPrincipal);
        dialogo.setVisible(true);
    }

    private static class OpcionProducto {
        String nombre;
        String tipo;
        double precio;
        JCheckBox checkBox;

        OpcionProducto(String nombre, String tipo, double precio) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.precio = precio;
            this.checkBox = new JCheckBox(tipo + ": " + nombre + " - $" + String.format("%,.0f", precio).replace(",", "."));
            this.checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            this.checkBox.setBackground(Color.WHITE);
        }
    }

    private void mostrarVentanaPersonalizar() {
        JDialog dialogo = new JDialog(this, "Crear computador personalizado", true);
        dialogo.setSize(820, 650);
        dialogo.setLocationRelativeTo(this);

        JPanel panelPrincipal = new JPanel(new BorderLayout(0, 18));
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));

        JLabel titulo = new JLabel("Selecciona los componentes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(17, 24, 39));

        JPanel panelOpciones = new JPanel(new GridLayout(1, 2, 24, 0));
        panelOpciones.setBackground(Color.WHITE);

        List<OpcionProducto> componentes = new ArrayList<>();
        componentes.add(new OpcionProducto("Intel Core i5", "Procesador", 850000));
        componentes.add(new OpcionProducto("Intel Core i7", "Procesador", 1300000));
        componentes.add(new OpcionProducto("Ryzen 7", "Procesador", 1200000));
        componentes.add(new OpcionProducto("16 GB RAM", "Memoria RAM", 350000));
        componentes.add(new OpcionProducto("32 GB RAM", "Memoria RAM", 620000));
        componentes.add(new OpcionProducto("512 GB SSD", "Almacenamiento", 300000));
        componentes.add(new OpcionProducto("1 TB SSD", "Almacenamiento", 520000));
        componentes.add(new OpcionProducto("RTX 4060", "Tarjeta grafica", 1800000));
        componentes.add(new OpcionProducto("RTX 4070", "Tarjeta grafica", 2600000));
        componentes.add(new OpcionProducto("Windows 11", "Sistema operativo", 450000));
        componentes.add(new OpcionProducto("Linux Ubuntu", "Sistema operativo", 0));

        List<OpcionProducto> accesorios = new ArrayList<>();
        accesorios.add(new OpcionProducto("Mouse Gamer RGB", "Mouse", 150000));
        accesorios.add(new OpcionProducto("Teclado mecanico", "Teclado", 220000));
        accesorios.add(new OpcionProducto("Monitor 144Hz", "Monitor", 850000));
        accesorios.add(new OpcionProducto("Audifonos gamer", "Audio", 180000));
        accesorios.add(new OpcionProducto("Base refrigerante", "Refrigeracion", 120000));

        panelOpciones.add(crearPanelOpciones("Componentes internos", componentes));
        panelOpciones.add(crearPanelOpciones("Accesorios adicionales", accesorios));

        JButton btnConfirmar = new JButton("Agregar al carrito");
        configurarBotonProducto(btnConfirmar);

        btnConfirmar.addActionListener(e -> {
            List<OpcionProducto> seleccionadosComponentes = obtenerSeleccionados(componentes);
            List<OpcionProducto> seleccionadosAccesorios = obtenerSeleccionados(accesorios);

            String procesador = obtenerNombrePorTipo(seleccionadosComponentes, "Procesador");
            String ram = obtenerNombrePorTipo(seleccionadosComponentes, "Memoria RAM");
            String almacenamiento = obtenerNombrePorTipo(seleccionadosComponentes, "Almacenamiento");
            String gpu = obtenerNombrePorTipo(seleccionadosComponentes, "Tarjeta grafica");
            String so = obtenerNombrePorTipo(seleccionadosComponentes, "Sistema operativo");

            if (procesador == null || ram == null || almacenamiento == null || gpu == null || so == null) {
                JOptionPane.showMessageDialog(dialogo,
                        "Debes seleccionar procesador, RAM, almacenamiento, tarjeta grafica y sistema operativo.");
                return;
            }

            double precioBase = calcularTotal(seleccionadosComponentes);

            Cliente clientePedido = new Cliente(
                    clienteActual.getNombre(),
                    clienteActual.getIdentificacion(),
                    TipoCliente.PROGRAMADOR
            );

            Pedido pedido = controlador.crearPedidoPersonalizado(
                    clientePedido,
                    procesador,
                    ram,
                    almacenamiento,
                    gpu,
                    so,
                    precioBase
            );

            for (OpcionProducto accesorio : seleccionadosAccesorios) {
                pedido.agregarPeriferico(textoAccesorio(accesorio), accesorio.precio);
            }

            carrito.add(pedido);
            btnCarrito.setText("Carrito (" + carrito.size() + ")");

            JOptionPane.showMessageDialog(dialogo,
                    "Computador personalizado agregado al carrito.\nTotal: " + formatearPrecio(pedido.getTotal()));

            dialogo.dispose();
        });

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(panelOpciones, BorderLayout.CENTER);
        panelPrincipal.add(btnConfirmar, BorderLayout.SOUTH);

        dialogo.setContentPane(panelPrincipal);
        dialogo.setVisible(true);
    }

    private String textoAccesorio(OpcionProducto accesorio) {
        return accesorio.tipo + ": " + accesorio.nombre;
    }

    private JPanel crearPanelOpciones(String titulo, List<OpcionProducto> opciones) {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(17, 24, 39));

        JPanel lista = new JPanel(new GridLayout(0, 1, 0, 8));
        lista.setBackground(Color.WHITE);

        for (OpcionProducto opcion : opciones) {
            lista.add(opcion.checkBox);
        }

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lista, BorderLayout.CENTER);

        return panel;
    }

    private List<OpcionProducto> obtenerSeleccionados(List<OpcionProducto> opciones) {
        List<OpcionProducto> seleccionados = new ArrayList<>();

        for (OpcionProducto opcion : opciones) {
            if (opcion.checkBox.isSelected()) {
                seleccionados.add(opcion);
            }
        }

        return seleccionados;
    }

    private String obtenerNombrePorTipo(List<OpcionProducto> opciones, String tipo) {
        for (OpcionProducto opcion : opciones) {
            if (opcion.tipo.equals(tipo)) {
                return opcion.nombre;
            }
        }

        return null;
    }

    private double calcularTotal(List<OpcionProducto> opciones) {
        double total = 0;

        for (OpcionProducto opcion : opciones) {
            total += opcion.precio;
        }

        return total;
    }

    private void configurarBuscador() {
        txtBuscar.setPreferredSize(new Dimension(420, 42));
        txtBuscar.setMinimumSize(new Dimension(420, 42));
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtBuscar.setText(PLACEHOLDER_BUSQUEDA);
        txtBuscar.setForeground(new Color(107, 114, 128));
        txtBuscar.setBackground(new Color(245, 247, 250));
        txtBuscar.setCaretColor(new Color(17, 24, 39));

        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
                BorderFactory.createEmptyBorder(0, 14, 0, 14)
        ));

        txtBuscar.addFocusListener(new FocusAdapter() {
            @Override
            //borra el texto cuando hacen click en el buscador
            public void focusGained(FocusEvent e) {
                if (txtBuscar.getText().equals(PLACEHOLDER_BUSQUEDA)) {
                    txtBuscar.setText("");
                    txtBuscar.setForeground(new Color(17, 24, 39));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //al hacer click por fuera del buscador si no se escribió nada, restablece el texto del placeholder
                if (txtBuscar.getText().trim().isEmpty()) {
                    txtBuscar.setText(PLACEHOLDER_BUSQUEDA);
                    txtBuscar.setForeground(new Color(107, 114, 128));
                    mostrarProductos(computadoresDisponibles);
                }
            }
        });

        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarProductos();
            }
        });
    }

    private void filtrarProductos() {
        String texto = txtBuscar.getText().trim().toLowerCase();

        if (texto.isEmpty() || texto.equals(PLACEHOLDER_BUSQUEDA.toLowerCase())) {
            mostrarProductos(computadoresDisponibles);
            return;
        }

        List<Computador> filtrados = new ArrayList<>();

        for (Computador computador : computadoresDisponibles) {
            String nombre = computador.getNombre().toLowerCase();
            String tipo = computador.getTipo().toLowerCase();
            String specs = computador.getEspecificaciones().toLowerCase();

            if (nombre.contains(texto) || tipo.contains(texto) || specs.contains(texto)) {
                filtrados.add(computador);
            }
        }

        mostrarProductos(filtrados);
    }

    private void agregarAlCarrito(Computador computador) {
        TipoCliente tipo = obtenerTipoCliente(computador);

        Cliente clientePedido = new Cliente(
                clienteActual.getNombre(),
                clienteActual.getIdentificacion(),
                tipo
        );

        Pedido pedido = controlador.crearPedidoDesdeComputador(clientePedido, computador);
        controlador.agregarPerifericosPorTipo(pedido, tipo);

        carrito.add(pedido);
        btnCarrito.setText("Carrito (" + carrito.size() + ")");

        JOptionPane.showMessageDialog(this,
                computador.getNombre() + " agregado al carrito.\nTotal con perifericos: "
                        + formatearPrecio(pedido.getTotal()));
    }

    private TipoCliente obtenerTipoCliente(Computador computador) {
        String tipo = computador.getTipo().toUpperCase();

        if (tipo.contains("GAMING")) {
            return TipoCliente.GAMER;
        }
        if (tipo.contains("OFICINA")) {
            return TipoCliente.OFICINA;
        }
        if (tipo.contains("DISENO")) {
            return TipoCliente.DISENADOR;
        }
        if (tipo.contains("PROGRAMADOR")) {
            return TipoCliente.PROGRAMADOR;
        }
        if (tipo.contains("ESTUDIANTE")) {
            return TipoCliente.ESTUDIANTE;
        }

        return TipoCliente.OFICINA;
    }

    private void mostrarClienteActual() {
        String texto = "Cliente actual\n\n"
                + "Nombre: " + clienteActual.getNombre() + "\n"
                + "Identificacion: " + clienteActual.getIdentificacion() + "\n"
                + "Tipo por defecto: " + clienteActual.getTipo() + "\n\n"
                + "Nota: En la factura, el tipo se ajusta segun el computador comprado.";

        JOptionPane.showMessageDialog(this, texto, "Mi cuenta", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarCarrito() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito esta vacio.");
            return;
        }

        StringBuilder texto = new StringBuilder();
        double totalGeneral = 0;

        texto.append("Cliente actual: ")
                .append(clienteActual.getNombre())
                .append("\n\n");

        for (int i = 0; i < carrito.size(); i++) {
            Pedido pedido = carrito.get(i);

            texto.append(i + 1)
                    .append(". ")
                    .append(pedido.getComputador().getNombre())
                    .append(" - ")
                    .append(formatearPrecio(pedido.getComputador().getPrecio()))
                    .append("\n");

            texto.append("Tipo de pedido: ")
                    .append(pedido.getCliente().getTipo())
                    .append("\n");

            for (DetallePedido detalle : pedido.getPerifericos()) {
                texto.append("  + ")
                        .append(detalle.getDescripcion())
                        .append(" - ")
                        .append(formatearPrecio(detalle.getPrecio()))
                        .append("\n");
            }

            texto.append("Subtotal: ")
                    .append(formatearPrecio(pedido.getTotal()))
                    .append("\n\n");

            totalGeneral += pedido.getTotal();
        }

        texto.append("Total general: ").append(formatearPrecio(totalGeneral));

        Object[] opciones = {"Ver factura", "Cerrar"};

        int opcion = JOptionPane.showOptionDialog(
                this,
                texto.toString(),
                "Carrito",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == 0) {
            mostrarFactura();
        }
    }

    private void mostrarFactura() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay pedidos para facturar.");
            return;
        }

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < carrito.size(); i++) {
            Factura factura = new Factura(carrito.get(i));

            texto.append("PEDIDO ")
                    .append(i + 1)
                    .append("\n");

            texto.append(factura.generarTextoFactura());

            if (i < carrito.size() - 1) {
                texto.append("\n\n");
            }
        }

        JTextArea areaFactura = new JTextArea(texto.toString());
        areaFactura.setEditable(false);
        areaFactura.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(areaFactura);
        scroll.setPreferredSize(new Dimension(620, 460));

        JOptionPane.showMessageDialog(
                this,
                scroll,
                "Factura",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void cargarLogo() {
        java.net.URL url = getClass().getResource("/img/logo-technozone.png");

        if (url == null) {
            logoCompletoLabel.setText("LOGO NO ENCONTRADO");
            return;
        }

        ImageIcon icon = new ImageIcon(url);
        Image image = icon.getImage().getScaledInstance(360, 72, Image.SCALE_SMOOTH);

        logoCompletoLabel.setIcon(new ImageIcon(image));
        logoCompletoLabel.setPreferredSize(new Dimension(360, 72));
    }

    private void activarClickFueraDelBuscador() {
        MouseAdapter quitarFoco = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getComponent() != txtBuscar) {
                    rootPanel.requestFocusInWindow();
                }
            }
        };

        rootPanel.addMouseListener(quitarFoco);
        panelContenido.addMouseListener(quitarFoco);
        panelInicio.addMouseListener(quitarFoco);
        panelProductos.addMouseListener(quitarFoco);
        panelEncabezado.addMouseListener(quitarFoco);
        panelLogo.addMouseListener(quitarFoco);
        panelBusqueda.addMouseListener(quitarFoco);
        panelUsuario.addMouseListener(quitarFoco);
    }

    private void configurarBotonHeader(JButton boton) {
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setPreferredSize(new Dimension(112, 38));
        boton.setBackground(new Color(37, 99, 235));
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void configurarBotonProducto(JButton boton) {
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setPreferredSize(new Dimension(0, 36));
        boton.setBackground(new Color(37, 99, 235));
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private String formatearPrecio(double precio) {
        return "$" + String.format("%,.0f", precio).replace(",", ".");
    }
}
