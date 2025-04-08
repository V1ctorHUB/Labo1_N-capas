package gestor;

import models.Artista;
import models.Discos;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gestor {

    private List<Artista> artistas;
    private List<Discos> discos;

    public Gestor() {
        this.artistas = new ArrayList<>();
        this.discos = new ArrayList<>();
    }

    public void agregarArtista(Artista artista) {
        artistas.add(artista);
    }

    public void agregarDisco(Discos disco) {
        discos.add(disco);
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public Artista obtenerArtistaConMasVentas() {
        if (artistas.isEmpty()) return null;

        Artista top = artistas.get(0);

        for (Artista artista : artistas) {
            if (artista.getVentasTotales() > top.getVentasTotales()) {
                top = artista;
            }
        }

        return top;
    }

    public void mostrarDiscosConMasDe(int minimoVentas) {
        List<Discos> filtrados = discos.stream()
                .filter(disco -> disco.getVentasTotales() >= minimoVentas)
                .collect(Collectors.toList());

        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron discos con más de " + minimoVentas + " unidades vendidas.");
        } else {
            System.out.println("Discos con " + minimoVentas + " unidades vendidas:");
            for (Discos disco : filtrados) {
                System.out.println("-------------------------");
                System.out.println(disco);
            }
        }
    }
}
