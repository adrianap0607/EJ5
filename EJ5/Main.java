//Universidad del Valle de Guatemala	                                                                                            
//Adriana Palacios 
//CC2008 - Introducción a la Programación Orientada a Objetos 
//carné 23044
//Semestre II, 2023

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.text.DecimalFormat;


/**
 * Esta clase representa el programa principal que administra jugadores de voleibol y permite realizar diversas acciones.
 */

public class Main {
   /**
     * Lista que almacena a los jugadores registrados.
     */ 
    public static ArrayList<Jugador> jugadores = new ArrayList<>();

    //* Método principal que inicia el programa.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        jugadores = cargarDesdeCSV();
        while (!salir) {
            System.out.println("==== Menú ====");
            System.out.println("");
            System.out.println("====== Administración Jugadores ======");
            System.out.println("1. Registrar jugador");
            System.out.println("2. Mostrar todos los jugadores inscritos con efectividad");
            System.out.println("3. Mostrar los 3 mejores líberos");
            System.out.println("4. Conocer cantidad de pasadores con más de 80% de efectividad");
            System.out.println("");
            System.out.println("====== Administrar CSV ======");
            System.out.println("------ACCEDER A 5 CADA QUE AGREGUE UN NUEVO JUGADOR------");
            System.out.println("5. Guardar en CSV");
            System.out.println("6. Cargar desde CSV");
            System.out.println("7. Salir");
            System.out.println("");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarJugador(jugadores, scanner);
                    break;
                case 2:
                    calcularEfectividad(jugadores);
                    break;
                case 3:
                    mostrarMejoresLiberos(jugadores);
                    break;
                case 4:
                    int cuenta = 0;
                    for (Jugador jugador: jugadores){
                        if(jugador instanceof Pasador){
                            if(jugador.calcularEfectividad() >= 80){
                                cuenta++;
                            }
                        }
                    }
                    System.out.println("Existen " + cuenta + " pasadores con más de 80% de efectividad.");
                    System.out.println();
                    break;

                case 5:
                    guardarEnCSV(jugadores);
                    break;
                case 6:
                    jugadores = cargarDesdeCSV();
                    break;

                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Introduce un número del 1 al 6.");
                    break;
            }
        }

        System.out.println("Saliendo del programa...");
    }
    //* Registra un nuevo jugador en la lista de jugadores.
    private static void registrarJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.println("Ingresa el nombre del jugador: ");
        String nombre = scanner.next();
        System.out.println("Ingresa el país del jugador: ");
        String pais = scanner.next();
        System.out.println("Ingresa la cantidad de errores del jugador: ");
        int errores = scanner.nextInt();
        System.out.println("Ingresa la cantidad de aces del jugador: ");
        int aces = scanner.nextInt();
        System.out.println("Ingresa el total de servicios del jugador: ");
        int totalServicios = scanner.nextInt();

        System.out.println("Selecciona el tipo de jugador (Libero, Pasador, Auxiliar): ");
        int seleccion = 0;
        String tipoJugador = "";
        while(!(seleccion >= 1 && seleccion <= 3)){
            System.out.println("1. Libero");
            System.out.println("2. Pasador");
            System.out.println("3. Auxiliar/Opuesto");
            seleccion = scanner.nextInt();
            switch(seleccion){
                case 1:
                    tipoJugador = "Libero";
                    break;
                case 2:
                    tipoJugador = "Pasador";
                    break;
                case 3:
                    tipoJugador = "Auxiliar";
                    break;
                default:
                    System.out.println("Por favor, selecciona algo válido");
                    break;
            }
        }
        switch (tipoJugador) {
            case "Libero":
                System.out.println("Ingresa la cantidad de recibos del líbero: ");
                int recibos = scanner.nextInt();
                jugadores.add(new Libero(nombre, pais, errores, aces, totalServicios, recibos));
                break;
            case "Pasador":
                System.out.println("Ingresa la cantidad de pases del pasador: ");
                int pases = scanner.nextInt();
                System.out.println("Ingresa la cantidad de fintas del pasador: ");
                int fintas = scanner.nextInt();
                jugadores.add(new Pasador(nombre, pais, errores, aces, totalServicios, pases, fintas));
                break;
            case "Auxiliar":
                System.out.println("Ingresa la cantidad de ataques del auxiliar/opuesto: ");
                int ataques = scanner.nextInt();
                System.out.println("Ingresa la cantidad de bloqueos efectivos del auxiliar/opuesto: ");
                int bloqueosEfectivos = scanner.nextInt();
                System.out.println("Ingresa la cantidad de bloqueos fallidos del auxiliar/opuesto: ");
                int bloqueosFallidos = scanner.nextInt();
                jugadores.add(new Auxiliares(nombre, pais, errores, aces, totalServicios, ataques, bloqueosEfectivos, bloqueosFallidos));
                break;
            default:
                System.out.println("Tipo de jugador no válido.");
        }
        System.out.println("Jugador registrado con éxito.");
    }
//* Calcula y muestra la efectividad de todos los jugadores registrados.
    private static void calcularEfectividad(ArrayList<Jugador> jugadores) {
        DecimalFormat df = new DecimalFormat("###.##");
        for (Jugador jugador : jugadores) {
            double efectividad = jugador.calcularEfectividad();
            String efectividadFormateada = df.format(efectividad);
            System.out.println("-----------");;
            System.out.println(jugador.getNombre() + " - Efectividad: " + efectividadFormateada  + "%");
            System.out.println("--------------");
        }
        System.out.println("----------------------------------------------------------");
    }
// * Muestra los 3 mejores líberos de la lista de jugadores.
    private static void mostrarMejoresLiberos(ArrayList<Jugador> jugadores) {
        DecimalFormat df = new DecimalFormat("###.##");
        ArrayList<Libero> liberos = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            if (jugador instanceof Libero) {
                liberos.add((Libero) jugador);
            }
        }
        if (liberos.isEmpty()) {
            System.out.println("No hay líberos registrados.");
        } else {
            Collections.sort(liberos, Comparator.comparing(Libero::calcularEfectividad).reversed());
            System.out.println("Los 3 mejores líberos son:");
            int contador = 0;
            System.out.println("**************************");
            for (Libero libero : liberos) {
                if (contador < 3) {
                    double efectividad = libero.calcularEfectividad();
                    String efectividadFormateada = df.format(efectividad);
                    System.out.println(libero.getNombre() + " - Efectividad: " + efectividadFormateada + "%");
                    contador++;
                } else {
                    break;
                }
            }
            System.out.println("**************************"); 
        }
    }
//Guarda a todos los jugaodres registrados en un archivo csv
    private static void guardarEnCSV(ArrayList<Jugador> jugadores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Jugadores.csv"))) {
            // Escribe la cabecera de las columnas (la primera fila) si es necesario
            writer.println("Nombre,Pais,Errores,Aces,TotalServicios,Recibos,Pases,Fintas,Ataques,BloqueosEfectivos,BloqueosFallidos,TipoJugador");
            
            for (Jugador jugador : jugadores) {
                String tipoJugador = jugador.getTipoJugador();
                StringBuilder fila = new StringBuilder();
                fila.append(jugador.getNombre()).append(",");
                fila.append(jugador.getPais()).append(",");
                fila.append(jugador.getErrores()).append(",");
                fila.append(jugador.getAces()).append(",");
                fila.append(jugador.getTotalServicios()).append(",");
                
                if ("Libero".equals(tipoJugador)) {
                    Libero libero = (Libero) jugador;
                    fila.append(libero.getRecibos()).append(", , , , , ,").append(tipoJugador);
                } else if ("Pasador".equals(tipoJugador)) {
                    Pasador pasador = (Pasador) jugador;
                    fila.append(" , ").append(pasador.getPases()).append(",").append(pasador.getFintas()).append(", , , ,").append(tipoJugador);
                } else if ("Auxiliar".equals(tipoJugador)) {
                    Auxiliares auxiliar = (Auxiliares) jugador;
                    fila.append(" , , , ").append(auxiliar.getAtaques()).append(",").append(auxiliar.getBloqueosEfectivos()).append(",").append(auxiliar.getBloqueosFallidos()).append(",").append(tipoJugador);
                }
                
                writer.println(fila.toString());
            }
            System.out.println("Datos guardados en CSV exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

        private static ArrayList<Jugador> cargarDesdeCSV() {
            ArrayList<Jugador> jugadores = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("Jugadores.csv"))) {
                String line;
                reader.readLine(); // Lee la primera línea de encabezado y descártala
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 12) {
                        String nombre = parts[0].trim();
                        String pais = parts[1].trim();
                        int errores = Integer.parseInt(parts[2].trim());
                        int aces = Integer.parseInt(parts[3].trim());
                        int totalServicios = Integer.parseInt(parts[4].trim());
                        String tipoJugador = parts[11].trim();
        
                        if (tipoJugador.equals("Libero")) {
                            if (parts.length >= 6) {
                                int recibos = Integer.parseInt(parts[5].trim());
                                jugadores.add(new Libero(nombre, pais, errores, aces, totalServicios, recibos));
                            
                            } else {
                                System.out.println("Faltan datos para crear un jugador Libero en la línea: " + line);
                            }
                        } else if (tipoJugador.equals("Pasador")) {
                            if (parts.length >= 8) {
                                int pases = Integer.parseInt(parts[6].trim());
                                int fintas = Integer.parseInt(parts[7].trim());
                                jugadores.add(new Pasador(nombre, pais, errores, aces, totalServicios, pases, fintas));
                            } else {
                                System.out.println("Faltan datos para crear un jugador Pasador en la línea: " + line);
                            }
                        } else if (tipoJugador.equals("Auxiliar")) {
                            if (parts.length >= 11) {
                                int ataques = Integer.parseInt(parts[8].trim());
                                int bloqueosEfectivos = Integer.parseInt(parts[9].trim());
                                int bloqueosFallidos = Integer.parseInt(parts[10].trim());
                                jugadores.add(new Auxiliares(nombre, pais, errores, aces, totalServicios, ataques, bloqueosEfectivos, bloqueosFallidos));
                            } else {
                                System.out.println("Faltan datos para crear un jugador Auxiliar en la línea: " + line);
                            }
                        } else {
                            System.out.println("Tipo de jugador desconocido en la línea: " + line);
                        }
                    } else {
                        System.out.println("Datos insuficientes en la línea: " + line);
                    }
                }
                System.out.println("Datos cargados desde CSV exitosamente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jugadores;
        }
        
    }
