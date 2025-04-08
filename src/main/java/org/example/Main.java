package org.example;

import gestor.Gestor;
import models.Artista;
import models.Discos;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        Scanner scanner = new Scanner(System.in);

        // Datos precargados
        Artista a1 = new Artista("Queen");
        Artista a2 = new Artista("David Bowie");

        Discos d1 = new Discos("A Night at the Opera", a1);
        Discos d2 = new Discos("The Game", a1);
        Discos d3 = new Discos("Heroes", a2);

        gestor.agregarArtista(a1);
        gestor.agregarArtista(a2);
        gestor.agregarDisco(d1);
        gestor.agregarDisco(d2);
        gestor.agregarDisco(d3);

        int opcion;

        do {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Ver artistas y discos registrados");
            System.out.println("2. Comprar un disco");
            System.out.println("3. Mostrar discos con más de X ventas");
            System.out.println("4. Mostrar artista con más ventas");
            System.out.println("5. Agregar nuevo artista");
            System.out.println("6. Agregar nuevo disco a un artista");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n--- ARTISTAS Y SUS DISCOS ---");
                    for (Artista artista : gestor.getArtistas()) {
                        System.out.println("Artista: " + artista.getNombre());
                        for (Discos disco : artista.getDiscos()) {
                            System.out.println("  - " + disco.getTitulo() + " (Ventas: " + disco.getVentasTotales() + ")");
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- COMPRAR DISCO ---");
                    List<Artista> artistas = gestor.getArtistas();
                    for (int i = 0; i < artistas.size(); i++) {
                        System.out.println((i + 1) + ". " + artistas.get(i).getNombre());
                    }
                    System.out.print("Seleccione un artista por número: ");
                    int artistaIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (artistaIndex < 0 || artistaIndex >= artistas.size()) {
                        System.out.println("Opción inválida.");
                        break;
                    }

                    Artista artistaSeleccionado = artistas.get(artistaIndex);
                    List<Discos> discos = artistaSeleccionado.getDiscos();
                    for (int i = 0; i < discos.size(); i++) {
                        System.out.println((i + 1) + ". " + discos.get(i).getTitulo());
                    }

                    System.out.print("Seleccione un disco por número: ");
                    int discoIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (discoIndex < 0 || discoIndex >= discos.size()) {
                        System.out.println("Opción inválida.");
                        break;
                    }

                    System.out.print("Ingrese la cantidad de unidades a comprar: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();

                    discos.get(discoIndex).registrarVenta(cantidad);
                    System.out.println("¡Venta registrada con éxito!");
                    break;

                case 3:
                    System.out.print("\nIngrese el mínimo de ventas: ");
                    int minimoVentas = scanner.nextInt();
                    scanner.nextLine();

                    gestor.mostrarDiscosConMasDe(minimoVentas);
                    break;

                case 4:
                    Artista top = gestor.obtenerArtistaConMasVentas();
                    if (top != null) {
                        System.out.println("\nArtista con más ventas: " + top.getNombre());
                        System.out.println("Total de ventas: " + top.getVentasTotales());
                        if (top.getDiscoMasVendido() != null) {
                            System.out.println("Disco más vendido: " + top.getDiscoMasVendido().getTitulo() +
                                    " (" + top.getDiscoMasVendido().getVentasTotales() + " ventas)");
                        }
                    } else {
                        System.out.println("No hay artistas registrados.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- AGREGAR NUEVO ARTISTA ---");
                    System.out.print("Ingrese el nombre del nuevo artista: ");
                    String nombreArtista = scanner.nextLine().trim();

                    if (nombreArtista.isEmpty()) {
                        System.out.println("Nombre inválido.");
                        break;
                    }

                    Artista nuevoArtista = new Artista(nombreArtista);
                    gestor.agregarArtista(nuevoArtista);
                    System.out.println("Artista agregado exitosamente.");
                    break;

                case 6:
                    System.out.println("\n--- AGREGAR NUEVO DISCO ---");
                    List<Artista> listaArtistas = gestor.getArtistas();
                    if (listaArtistas.isEmpty()) {
                        System.out.println("No hay artistas disponibles. Agregue un artista primero.");
                        break;
                    }

                    for (int i = 0; i < listaArtistas.size(); i++) {
                        System.out.println((i + 1) + ". " + listaArtistas.get(i).getNombre());
                    }

                    System.out.print("Seleccione un artista por número: ");
                    int indexArtista = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (indexArtista < 0 || indexArtista >= listaArtistas.size()) {
                        System.out.println("Opción inválida.");
                        break;
                    }

                    System.out.print("Ingrese el nombre del nuevo disco: ");
                    String nombreDisco = scanner.nextLine().trim();

                    if (nombreDisco.isEmpty()) {
                        System.out.println("Nombre de disco inválido.");
                        break;
                    }

                    Discos nuevoDisco = new Discos(nombreDisco, listaArtistas.get(indexArtista));
                    gestor.agregarDisco(nuevoDisco);
                    System.out.println("Disco agregado exitosamente.");
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 0);
        scanner.close();
    }
}
