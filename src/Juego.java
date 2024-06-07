import APIclima.ClimaAPI;
import APIclima.DatosClima;
import Enums.TipoArma;
import Enums.Zona;
import Modelos.Clases.*;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;
import Genericas.ListaGenerica;
import Persistencia.Archivos;

import java.util.*;

import static Persistencia.Archivos.*;

public class Juego {
    private Scanner scanner;
    private Zona zonaActual;
    private HashMap<Zona, ArrayList<Personaje>> enemigosPorZona;
    private Personaje jugador;

    Item viniloSorpresa = new Item("Fragmento de Vinilo de Help!", 5.0);


    public Juego() {
        scanner = new Scanner(System.in);
        zonaActual = Zona.SELVA; // Zona inicial
        enemigosPorZona = inicializarEnemigos();
        System.out.println("1. Crear Jugador");
        System.out.println("2. Cargar Partida");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                jugador = crearJugadors();
                break;
            case 2:
                System.out.print("Ingresa el nombre del personaje: ");
                String nombre = scanner.nextLine();
                jugador = cargarPersonaje(nombre);
                if (jugador == null) {
                    System.out.println("No se pudo cargar el personaje. Creando un nuevo personaje.");
                    jugador = crearJugadors();
                }
        }
    }

    private HashMap<Zona, ArrayList<Personaje>> inicializarEnemigos() {
        HashMap<Zona, ArrayList<Personaje>> localEnemigosPorZona = new HashMap<>();



        localEnemigosPorZona.put(Zona.SELVA, new ArrayList<>(Arrays.asList(
                new Goblin("Goblin errante", 50, 1),
                new NoMuerto("Doctor Pocovivo", 30, 1),
                new Pandaren("Panda bimbo", 70, 2)


        )));

        localEnemigosPorZona.put(Zona.DESIERTO, new ArrayList<>(Arrays.asList(
                new ElfoMago("El mago cocina del desierto", 40, 3),
                new Enano("Pequeño Ladron del Desierto", 60, 4),
                new Goblin("Goblin de las Arenas", 50, 4)
        )));

        localEnemigosPorZona.put(Zona.CIUDAD, new ArrayList<>(Arrays.asList(
                new Humano("Pandillero", 45, 5),
                new Humano("Ladron", 35, 6),
                new Humano("Policia Corrupto", 70, 7)
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
        crearArchivos("juani");
        boolean menu= true;
        while (menu) {
            limpiarConsola();
            System.out.println("Menú principal:");
            System.out.println("1. Explorar");
            System.out.println("2. Ver estadísticas");
            System.out.println("3. Inventario");
            System.out.println("4. Ir a la tienda");
            System.out.println("5. Guardar Partida");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    explorar();
                    limpiarConsola();
                    break;
                case 2:
                    jugador.mostrarEstadisticas();
                    presionarParaContinuar();
                    limpiarConsola();
                    break;
                case 3:
                    jugador.verInventario();
                    presionarParaContinuar();
                    limpiarConsola();
                    break;
                case 4:
                    irTienda();
                    limpiarConsola();
                    break;
                case 5:
                    System.out.println("Guardado");
                    guardarPersonaje(jugador,"juani");
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
                    limpiarConsola();
                    jugador.atacar(enemigo);
                    presionarParaContinuar();
                    break;
                case 2:
                    limpiarConsola();
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
                enemigo.getInventario().agregarElemento(viniloSorpresa);
                jugador.agarrarObjeto(enemigo);
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



    public void irTienda()
    {
        System.out.println("Hola Viajero necesito que me traigas mis fragmentos de vinilo para poder escuchar mi musica");
        System.out.println("Mata a esos malditas alimandrias para recuperarlos, te dare una recompensa cuando me traigas 3 y 9 fragementos");
        presionarParaContinuar();
        limpiarConsola();
        if(jugador.inventario.contarElementosEspecifico(viniloSorpresa)>=3){
            Arma poderosa = new Arma("Gibson J-160E ",2,50, TipoArma.HIELO);
            System.out.println("Toma esta gran guitarra sin cuerdas, te ayudara a golpearlos mejor");
            jugador.setArma(poderosa);

        } else if (jugador.inventario.contarElementosEspecifico(viniloSorpresa)>=9) {
            Arma poderosaElectrica = new Arma("Bajo Iconico Hofner de P. Mc",2,100, TipoArma.ELECTRICO);
            System.out.println("Toma este iconico Bajo Hofner electrico, te servira en tu viaje");
            jugador.setArma(poderosaElectrica);

        }else {
            System.out.println("Sigues aqui? Ve a por mis vinilos");
        }
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
                System.out.println("Gracias por jugar!!");
                return;
        }
        System.out.println("¡Has llegado a la " + zonaActual + "!");
    }

    public void limpiarConsola()
    {
        for(int i=0;i<100;i++){
            System.out.println();
        }
    }


    public String leerUnInputString () {
        String opcion= "escribirNuevamente";
        scanner= new Scanner(System.in);

        do{

            try {
                opcion= scanner.nextLine();

            }catch (Exception e)
            {
                opcion="escrbirNuevamente";
                System.out.println("Escribi una palabra por favor!");
            }
        }
        while ("escribirNuevamente".equals(opcion));

        return opcion;
    }

    public Personaje crearJugadors() {
        double vida=100;
        System.out.println("Creación de personaje:");

        // Nombre del personaje
        System.out.print("Ingresa el nombre del personaje: ");
        String nombre = leerUnInputString();

        // Clase del personaje
        System.out.print("Elige una clase (Enano, Humano, NoMuerto, Pandaren): ");
        String claseInput = leerUnInputString();
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

