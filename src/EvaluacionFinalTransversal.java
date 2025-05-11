/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.ArrayList;
import java.util.Scanner;

public class EvaluacionFinalTransversal{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mostrar mensaje de bienvenida
        System.out.println("Bienvenido al Teatro Moro\n");

        // Listas para simular estructura de datos
        ArrayList<String> ubicaciones = new ArrayList<>();
        ArrayList<Integer> asientos = new ArrayList<>();
        ArrayList<Double> preciosBase = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Integer> edades = new ArrayList<>();
        ArrayList<String> generos = new ArrayList<>();
        ArrayList<String> tiposCliente = new ArrayList<>();

        boolean repetir = true;

        while (repetir) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Reservar Ticket");
            System.out.println("2. Comprar Ticket");
            System.out.println("3. Imprimir Boletas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Reservar
                    System.out.println("Tipos de asiento disponibles:");
                    System.out.println("1. VIP ($30000)");
                    System.out.println("2. Platea baja ($15000)");
                    System.out.println("3. Platea alta ($10000)");
                    System.out.println("4. Palcos ($5000)");
                    System.out.println("5. Galería ($3000)");
                    System.out.print("Seleccione tipo (1-5): ");
                    int tipoUbicacion = scanner.nextInt();
                    scanner.nextLine();

                    String ubicacion = "";
                    double precio = 0;

                    switch (tipoUbicacion) {
                        case 1: ubicacion = "VIP"; precio = 30000; break;
                        case 2: ubicacion = "Platea baja"; precio = 15000; break;
                        case 3: ubicacion = "Platea alta"; precio = 10000; break;
                        case 4: ubicacion = "Palcos"; precio = 5000; break;
                        case 5: ubicacion = "Galería"; precio = 3000; break;
                        default:
                            System.out.println("Opción inválida."); continue;
                    }

                    System.out.print("Ingrese número de asiento (1-50): ");
                    int asiento = scanner.nextInt();
                    scanner.nextLine();
                    if (asiento < 1 || asiento > 50) {
                        System.out.println("Asiento inválido.");
                        continue;
                    }

                    ubicaciones.add(ubicacion);
                    asientos.add(asiento);
                    preciosBase.add(precio);
                    nombres.add(""); // vacío = no comprado
                    edades.add(0);
                    generos.add("");
                    tiposCliente.add("");
                    System.out.println("Ticket reservado exitosamente.");
                    break;

                case 2:
                    // Comprar
                    System.out.print("Ingrese número de asiento reservado: ");
                    int asientoBuscado = scanner.nextInt();
                    scanner.nextLine();

                    boolean encontrado = false;

                    for (int i = 0; i < asientos.size(); i++) {
                        if (asientos.get(i) == asientoBuscado && nombres.get(i).equals("")) {
                            System.out.print("Nombre del cliente: ");
                            String nombre = scanner.nextLine();
                            System.out.print("Edad: ");
                            int edad = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Género (M/F): ");
                            String genero = scanner.nextLine().toUpperCase();
                            System.out.print("¿Es estudiante? (s/n): ");
                            char esEstudiante = scanner.next().toLowerCase().charAt(0);
                            scanner.nextLine();

                            double descuento = 0;
                            String tipoCliente = "normal";

                            if (edad < 12) {
                                descuento = 0.10;
                                tipoCliente = "niño";
                            } else if (edad >= 65) {
                                descuento = 0.25;
                                tipoCliente = "tercera edad";
                            } else if (genero.equals("F")) {
                                descuento = 0.20;
                                tipoCliente = "mujer";
                            }

                            if (esEstudiante == 's' && descuento < 0.15) {
                                descuento = 0.15;
                                tipoCliente = "estudiante";
                            }

                            double precioFinal = preciosBase.get(i) * (1 - descuento);

                            nombres.set(i, nombre);
                            edades.set(i, edad);
                            generos.set(i, genero);
                            tiposCliente.set(i, tipoCliente);

                            System.out.println("Compra realizada con éxito.");
                            System.out.println("Descuento aplicado: " + (int)(descuento * 100) + "%");
                            System.out.println("Total a pagar: $" + precioFinal);
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Asiento no encontrado o ya comprado.");
                    }
                    break;

                case 3:
                    // Imprimir boletas
                    for (int i = 0; i < ubicaciones.size(); i++) {
                        System.out.println("\n--- BOLETA ---");
                        System.out.println("Ubicación: " + ubicaciones.get(i));
                        System.out.println("Asiento: " + asientos.get(i));
                        if (!nombres.get(i).equals("")) {
                            String tipoCliente = tiposCliente.get(i);
                            double descuento = 0;

                            switch (tipoCliente) {
                                case "niño": descuento = 0.10; break;
                                case "tercera edad": descuento = 0.25; break;
                                case "mujer": descuento = 0.20; break;
                                case "estudiante": descuento = 0.15; break;
                            }

                            double precioFinal = preciosBase.get(i) * (1 - descuento);

                            System.out.println("Cliente: " + nombres.get(i));
                            System.out.println("Edad: " + edades.get(i));
                            System.out.println("Género: " + generos.get(i));
                            System.out.println("Tipo: " + tipoCliente);
                            System.out.println("Precio final: $" + precioFinal);
                        } else {
                            System.out.println("Ticket reservado, aún no comprado.");
                            System.out.println("Precio base: $" + preciosBase.get(i));
                        }
                    }
                    break;

                case 4:
                    repetir = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}

