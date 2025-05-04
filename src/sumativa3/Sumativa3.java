/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sumativa3;
import java.util.ArrayList;
import java.util.Scanner;

public class Sumativa3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<String> tipoEntradas = new ArrayList<>();
        ArrayList<Integer> asientos = new ArrayList<>();
        ArrayList<Double> preciosBase = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<String> tiposCliente = new ArrayList<>();
        
        boolean repetir = true;

        while (repetir) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Reservar Ticket");
            System.out.println("2. Comprar Ticket");
            System.out.println("3. Imprimir Tickets");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Tipos de entrada:");
                    System.out.println("1. VIP ($30000)");
                    System.out.println("2. Platea baja ($15000)");
                    System.out.println("3. Platea alta ($10000)");
                    System.out.println("4. Palcos ($5000)");
                    System.out.print("Elija tipo: ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();
                    String tipoEntrada = "";
                    double precio = 0;

                    switch (tipo) {
                        case 1: tipoEntrada = "VIP"; precio = 30000; break;
                        case 2: tipoEntrada = "Platea baja"; precio = 15000; break;
                        case 3: tipoEntrada = "Platea alta"; precio = 10000; break;
                        case 4: tipoEntrada = "Palcos"; precio = 5000; break;
                        default:
                            System.out.println("Opción inválida."); continue;
                    }

                    System.out.print("Número de asiento (1-20): ");
                    int asiento = scanner.nextInt();
                    scanner.nextLine();
                    if (asiento < 1 || asiento > 20) {
                        System.out.println("Asiento inválido.");
                        continue;
                    }

                    tipoEntradas.add(tipoEntrada);
                    asientos.add(asiento);
                    preciosBase.add(precio);
                    nombres.add(""); // vacío = no comprado
                    tiposCliente.add(""); // vacío = no comprado
                    System.out.println("Ticket reservado.");
                    break;

                case 2:
                    if (tipoEntradas.isEmpty()) {
                        System.out.println("No hay tickets reservados.");
                        break;
                    }

                    System.out.print("Ingrese número de asiento reservado: ");
                    int asientoBuscado = scanner.nextInt();
                    scanner.nextLine();

                    boolean encontrado = false;

                    for (int i = 0; i < asientos.size(); i++) {
                        if (asientos.get(i) == asientoBuscado && nombres.get(i).equals("")) {
                            System.out.print("Nombre del cliente: ");
                            String nombre = scanner.nextLine();
                            System.out.print("Tipo de cliente (normal, estudiante, tercera edad): ");
                            String tipoCliente = scanner.nextLine().toLowerCase();

                            double descuento = 0;
                            if (tipoCliente.equals("estudiante")) {
                                descuento = 0.10;
                            } else if (tipoCliente.equals("tercera edad")) {
                                descuento = 0.15;
                            }

                            double precioFinal = preciosBase.get(i) * (1 - descuento);
                            nombres.set(i, nombre);
                            tiposCliente.set(i, tipoCliente);

                            System.out.println("Compra realizada. Precio final: $" + precioFinal);
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Asiento no encontrado o ya comprado.");
                    }
                    break;

                case 3:
                    for (int i = 0; i < tipoEntradas.size(); i++) {
                        System.out.println("\n--- Ticket ---");
                        System.out.println("Entrada: " + tipoEntradas.get(i));
                        System.out.println("Asiento: " + asientos.get(i));
                        if (!nombres.get(i).equals("")) {
                            double descuento = 0;
                            String tipoCliente = tiposCliente.get(i);
                            if (tipoCliente.equals("estudiante")) descuento = 0.10;
                            if (tipoCliente.equals("tercera edad")) descuento = 0.15;

                            double precioFinal = preciosBase.get(i) * (1 - descuento);

                            System.out.println("Cliente: " + nombres.get(i) + " (" + tipoCliente + ")");
                            System.out.println("Precio final: $" + precioFinal);
                        } else {
                            System.out.println("Reservado (no comprado aún)");
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