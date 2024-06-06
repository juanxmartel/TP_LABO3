import Enums.TipoArma;
import Modelos.Clases.*;
import Modelos.Objeto.Arma;
import Modelos.Objeto.ArmasMelee;
import Modelos.Objeto.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    private Scanner scanner;

    public Juego() {
        scanner = new Scanner(System.in);
    }

    public Personaje crearJugador() {
        System.out.println("Creación de personaje:");

        // Nombre del personaje
        System.out.print("Ingresa el nombre del personaje: ");
        String nombre = scanner.nextLine();

        // Clase del personaje
        System.out.print("Elige una clase (Enano, Humano, NoMuerto, Pandaren): ");
        String claseInput = scanner.nextLine();
        TodasLasClases clase = TodasLasClases.valueOf(claseInput);

        // Vida inicial
        System.out.print("Ingresa la vida inicial del personaje: ");

          double vida = 50;
        //  double vida = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        // Nivel inicial
        int nivel = 1; // Asumiendo que todos los personajes comienzan en nivel 1

        // Experiencia inicial
        int experiencia = 0;

        // Experiencia necesaria para subir de nivel
        int experienciaNecesariaParaSubir = 100; // Este valor puede variar según tus reglas de juego

        // Arma inicial
        System.out.print("Ingresa el nombre de tu arma inicial: ");
        String nombreArma = scanner.nextLine();
        System.out.print("Ingresa el daño del arma inicial: ");

        double danoArma = 20;
        //double danoArma = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Elige el tipo de arma (FUEGO, ELECTRICO, HIELO, AGUA): ");
        String tipoArmaInput = scanner.nextLine();
        TipoArma tipoArma = TipoArma.valueOf(tipoArmaInput);

        Arma armaInicial = new ArmasMelee(nombreArma, danoArma,danoArma,tipoArma);

        // Inventario inicial vacío
        ArrayList<Item> inventario = new ArrayList<>();

        // Crear el personaje
        Personaje nuevoPersonaje = null;
        switch (clase) {
            case Enano:
                nuevoPersonaje = new Enano(nombre, vida, nivel, experiencia, experienciaNecesariaParaSubir, armaInicial, inventario);
                break;
            case Humano:
                nuevoPersonaje = new Humano(nombre, vida, nivel, experiencia, experienciaNecesariaParaSubir, armaInicial, inventario);
                break;
            case NoMuerto:
                nuevoPersonaje = new NoMuerto(nombre, vida, nivel, experiencia, experienciaNecesariaParaSubir, armaInicial, inventario);
                break;
            case Pandaren:
                nuevoPersonaje = new Pandaren(nombre, vida, nivel, experiencia, experienciaNecesariaParaSubir, armaInicial, inventario);
                break;
            default:
                System.out.println("Clase no válida.");
                return null;
        }
        System.out.println("Personaje creado exitosamente:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Clase: " + clase);
        System.out.println("Vida: " + vida);
        System.out.println("Arma: " + armaInicial.getNombre() + " (" + armaInicial.getTipo() + ")");

        return nuevoPersonaje;
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        Personaje jugador = juego.crearJugador();
        // Continuar con la lógica del juego...
    }
    ////////////////////////////////////////////////////////////


}

