package models;

import java.util.ArrayList;
import java.util.List;
import models.Discos;


public class Artista {
    private static int contadorIds = 1;

    private int id;
    private String nombre;
    private Discos discoMasVendido;
    private int ventasTotales;
    private List<Discos> discos;

    public Artista(String nombre) {
        this.id = contadorIds++;
        this.nombre = nombre;
        this.ventasTotales = 0;
        this.discos = new ArrayList<>();
    }

    public void agregarDisco(Discos disco) {
        discos.add(disco);
        actualizarDiscoMasVendido();
    }

    public void actualizarVentas(int cantidad) {
        this.ventasTotales += cantidad;
        actualizarDiscoMasVendido();
    }

    private void actualizarDiscoMasVendido() {
        if (discos.isEmpty()) return;

        Discos mejor = discos.get(0);
        for (Discos disco : discos) {
            if (disco.getVentasTotales() > mejor.getVentasTotales()) {
                mejor = disco;
            }
        }
        this.discoMasVendido = mejor;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public Discos getDiscoMasVendido() {
        return discoMasVendido;
    }
    public int getVentasTotales() {
        return ventasTotales;
    }
    public List<Discos> getDiscos() {
        return discos;
    }
}
