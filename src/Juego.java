import Enums.TipoArma;
import Enums.Zona;
import Modelos.Clases.*;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.util.*;

public class Juego {
    private Scanner scanner;
    private Zona zonaActual;
    private HashMap<Zona, ArrayList<Personaje>> enemigosPorZona;
    private Personaje jugador;

    public Juego() {
        scanner = new Scanner(System.in);
        zonaActual = Zona.SELVA; // Zona inicial
        enemigosPorZona = inicializarEnemigos(); // Assign the returned map
        jugador = crearJugadors();
    }

    private HashMap<Zona, ArrayList<Personaje>> inicializarEnemigos() {
        HashMap<Zona, ArrayList<Personaje>> localEnemigosPorZona = new HashMap<>();

        localEnemigosPorZona.put(Zona.SELVA, new ArrayList<>(Arrays.asList(
                new Enano("Jaguar", 50, 1),
                new Enano("Serpiente", 30, 1),
                new Enano("Tribu Hostil", 70, 2)
        )));

        localEnemigosPorZona.put(Zona.DESIERTO, new ArrayList<>(Arrays.asList(
                new Enano("Escorpion", 40, 3),
                new Enano("Ladron del Desierto", 60, 4),
                new Enano("Serpiente de Arena", 50, 4)
        )));

        localEnemigosPorZona.put(Zona.CIUDAD, new ArrayList<>(Arrays.asList(
                new Enano("Pandillero", 45, 5),
                new Enano("Ladron", 35, 6),
                new Enano("Policia Corrupto", 70, 7)
        )));

        return localEnemigosPorZona;
    }

    private Personaje generarEnemigoAleatorio(Zona zona) {
        Random random = new Random();
        ArrayList<Personaje> enemigosZona = enemigosPorZona.get(zona);

        if (enemigosZona == null || enemigosZona.isEmpty()) {
            System.out.println("No hay más enemigos en la zona " + zona);
            return null;
        }

        int indiceAleatorio = random.nextInt(enemigosZona.size());
        Personaje enemigo = enemigosZona.remove(indiceAleatorio);
        return enemigo;
    }

    private void explorar() {
        Personaje enemigo = generarEnemigoAleatorio(zonaActual);
        if (enemigo == null) {
            cambiarZona();
        } else {
            System.out.println("¡Has encontrado un " + enemigo.getNombre() + " en la " + zonaActual + "!");
            iniciarCombate(enemigo);
        }
    }

    //frenar el juego hasta que aprete la tecla
    public void presionarParaContinuar(){
        System.out.println("apretar cualquier tecla para continuar");
        scanner.next();
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("Menú principal:");
            System.out.println("1. Explorar");
            System.out.println("2. Ver estadísticas");
            System.out.println("3. Inventario");
            System.out.println("4. Tampco");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    explorar();
                    break;
                case 2:
                    jugador.mostrarEstadisticas();
                    break;
                case 3:
                    jugador.verInventario();
                    break;
                case 4:
                    cambiarZona();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    public void iniciarCombate(Personaje enemigo) {
        Random random = new Random();
        boolean jugadorVivo = true;
        boolean enemigoVivo = true;

        while (jugadorVivo && enemigoVivo) {
            System.out.println("\nVida del jugador: " + jugador.getVida());
            System.out.println("Vida del enemigo (" + enemigo.getNombre() + "): " + enemigo.getVida());

            System.out.println("\nTurno del jugador:");
            System.out.println("1. Atacar");
            System.out.println("2. Huir (proximamente)");
            System.out.print("Elige una acción: ");
            int eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1:
                    jugador.atacar(enemigo);
                    presionarParaContinuar();
                    break;
                case 2:
                    jugador.subirNivel();

                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            System.out.println("\nVida del jugador: " + jugador.getVida());
            System.out.println("Vida del enemigo (" + enemigo.getNombre() + "): " + enemigo.getVida());

            if (enemigo.getVida() <= 0) {

                System.out.println(enemigo.getNombre() + " ha sido derrotado.");
                jugador.subirNivel();
               // jugador.agarrarObjeto(enemigo);
                enemigoVivo = false;
                break;
            }

            // Turno del enemigo
            System.out.println("\nTurno del enemigo:");
            if (random.nextBoolean()) {
                jugador.recibirDanio(enemigo.atacarConArma());
            } else {
                System.out.println(enemigo.getNombre()+ " fallo su ataque estrepitosamente");
            }
            presionarParaContinuar();


            if (jugador.getVida() <= 0) {
                jugadorVivo = false;
                System.out.println(jugador.getNombre() + " has sido derrotado.");
            }
        }
    }

    private void irATienda() {
        // Implementa la lógica de la tienda aquí
        System.out.println("Bienvenido a la tienda.");
    }

    private void verEstadisticas() {
        // Implementa la lógica para mostrar las estadísticas del jugador
        System.out.println("Mostrando estadísticas del jugador.");
    }

    private void cambiarZona() {
        switch (zonaActual) {
            case SELVA:
                zonaActual = Zona.DESIERTO;
                System.out.println("SELVA COMPLETADA!");
                break;
            case DESIERTO:
                zonaActual = Zona.CIUDAD;
                System.out.println("DESIERTO COMPLETADO!");
                break;
            case CIUDAD:
                System.out.println("¡Completaste todas las zonas!");
                return;
        }
        System.out.println("¡Has llegado a la " + zonaActual + "!");
    }

    public Personaje crearJugadors() {
        double vida=100;
        System.out.println("Creación de personaje:");

        // Nombre del personaje
        System.out.print("Ingresa el nombre del personaje: ");
        String nombre = scanner.nextLine();

        // Clase del personaje
        System.out.print("Elige una clase (Enano, Humano, NoMuerto, Pandaren): ");
        String claseInput = scanner.nextLine();
        TodasLasClases clase;
        try {
            clase = TodasLasClases.valueOf(claseInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Clase no válida.");
            return null;
        }



        // Crear el personaje
        Personaje nuevoPersonaje = null;
        switch (clase) {
            case Enano:
                nuevoPersonaje = new Enano(nombre, vida);
                break;
            case Humano:
                nuevoPersonaje = new Humano(nombre, vida);
                break;
            case NoMuerto:
                nuevoPersonaje = new NoMuerto(nombre, vida);
                break;
            case Pandaren:
                nuevoPersonaje = new Pandaren(nombre, vida);
                break;
            default:
                System.out.println("Clase no válida.");
                return null;
        }

        System.out.println("Personaje creado exitosamente:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Clase: " + clase);
        System.out.println("Vida: " + vida);
        //   System.out.println("Arma: " + armaInicial.getNombre() + " (" + armaInicial.getTipo() + ")");

        return nuevoPersonaje;
    }


}

