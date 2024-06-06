import Enums.TipoArma;
import Modelos.Objeto.Arma;
import Modelos.Objeto.ArmasDistancia;
import Modelos.Objeto.ArmasMagicas;
import Modelos.Objeto.ArmasMelee;

import java.util.Scanner;

public class Juego {
    private Scanner scanner;

    public Juego() {
        scanner = new Scanner(System.in);
    }

    public Arma crearArma() {
        System.out.println("Creación de arma:");

        // Nombre del arma
        System.out.print("Ingresa el nombre del arma: ");
        String nombre = scanner.nextLine();

        // Peso del arma
        System.out.print("Ingresa el peso del arma: ");
        double peso = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        // Daño del arma
        System.out.print("Ingresa el daño del arma: ");
        double dano = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        // Tipo de arma (elemental)
        System.out.print("Elige el tipo del arma (AGUA, FUEGO, ELECTRICO, HIELO): ");
        String tipoArmaInput = scanner.nextLine();
        TipoArma tipoArma = TipoArma.valueOf(tipoArmaInput);

        // Tipo de arma (melee, distancia, mágica)
        System.out.print("Elige el tipo de arma (melee, distancia, magica): ");
        String tipo = scanner.nextLine();

        Arma nuevaArma = null;
        switch (tipo.toLowerCase()) {
            case "melee":
                nuevaArma = new ArmasMelee(nombre, peso, dano, tipoArma);
                break;
            case "distancia":
                nuevaArma = new ArmasDistancia(nombre, peso, dano, tipoArma);
                break;
            case "magica":
                nuevaArma = new ArmasMagicas(nombre, peso, dano, tipoArma);
                break;
            default:
                System.out.println("Tipo de arma no válido.");
                return null;
        }

        System.out.println("Arma creada exitosamente:");
        System.out.println("Nombre: " + nuevaArma.getNombre());
        System.out.println("Peso: " + nuevaArma.getPeso());
        System.out.println("Daño: " + nuevaArma.getDano());
        System.out.println("Tipo Elemental: " + nuevaArma.getTipo());

        return nuevaArma;
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        Arma arma = juego.crearArma();
        // Continuar con la lógica del juego...
    }
}

