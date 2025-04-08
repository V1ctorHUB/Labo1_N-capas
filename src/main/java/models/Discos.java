package models;

public class Discos {

    private static int contadorId = 1;
    private int id;
    private String titulo;
    private Artista artista;
    private int ventasTotales;

    public Discos(String titulo, Artista artista) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.artista = artista;
        this.ventasTotales = 0;
        artista.agregarDisco(this);
    }

    public void registrarVenta(int cantidad) {
        if (cantidad > 0) {
            this.ventasTotales += cantidad;
            artista.actualizarVentas(cantidad);
        }
    }

    // Getters y Setters

    public int getId() { return id; }

    public String getTitulo() { return titulo; }

    public Artista getArtista() { return artista; }

    public int getVentasTotales() { return ventasTotales; }


    public void setTitulo(String titulo) { this.titulo = titulo; }

    @Override
    public String toString() {
        return "Disco ID: " + id +
                "\nTitulo: " + titulo +
                "\nArtista: " + artista.getNombre() +
                "\nVentas Totales: " + ventasTotales;
    }
}
