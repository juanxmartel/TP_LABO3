import Enums.TipoArma;
import Enums.TodasLasClases;
import Enums.Zona;
import Modelos.Clases.*;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.io.Serializable;
import java.util.*;

import static Persistencia.Archivos.*;

/**
 * Clase base donde se desarrolla todo el juego y se llaman todas las funciones necesarias para llevarlo a cabo
 * */
public class Juego implements Serializable {
    private Zona zonaActual;
    private HashMap<Zona, ArrayList<Personaje>> enemigosPorZona;
    private Personaje jugador = new Personaje();

    Item viniloSorpresa = new Item("Fragmento de Vinilo de Help!", 5.0);
    HashMap<Zona, ArrayList<Personaje>> localEnemigosPorZona = new HashMap<>();
    Scanner scanner;


    /**
     * Inicio del juego */
    public Juego() {

        scanner = new Scanner(System.in);
        System.out.println("1. Crear Jugador");
        System.out.println("2. Cargar Partida");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                jugador = crearJugadors();
                zonaActual = Zona.SELVA; // Zona inicial
                enemigosPorZona = inicializarEnemigos(jugador.getNivel());
                break;
            case 2:
                System.out.println("Ingrese el nombre de su personaje");
                String nombrePersonaje = scanner.nextLine();
                jugador = cargarPersonaje(nombrePersonaje);
                enemigosPorZona= inicializarEnemigos(jugador.getNivel());
                zonaActual = Zona.SELVA;
                if (jugador == null) {
                    System.out.println("No se pudo cargar el personaje. Creando un nuevo personaje.");
                    jugador = crearJugadors();
                }
                break;
            default:
                System.out.println("Opción no válida. Creando un nuevo jugador por defecto.");
                jugador = crearJugadors();
                zonaActual = Zona.SELVA; // Zona inicial
                enemigosPorZona = inicializarEnemigos(jugador.getNivel());
                break;
        }
    }

    /**
     * Crea e inicializa todos los enemigos dentro del juego nuevo
     * @param nivel
     * @return localEnemigosPorZona*/

    private HashMap<Zona, ArrayList<Personaje>> inicializarEnemigos(int nivel) {
        HashMap<Zona, ArrayList<Personaje>> localEnemigosPorZona = new HashMap<>();

        ArrayList<Zona> zonasACargar = new ArrayList<>();

        if (nivel < 3) {
            zonasACargar.add(Zona.SELVA);
            zonasACargar.add(Zona.DESIERTO);
            zonasACargar.add(Zona.CIUDAD);
        } else if (nivel < 6) {
            zonasACargar.add(Zona.DESIERTO);
            zonasACargar.add(Zona.CIUDAD);
        } else {
            zonasACargar.add(Zona.CIUDAD);
        }

        if (zonasACargar.contains(Zona.SELVA)) {
            localEnemigosPorZona.put(Zona.SELVA, new ArrayList<>(Arrays.asList(
                    new Goblin("Goblin errante", 50, 1),
                    new NoMuerto("Doctor Pocovivo", 30, 1),
                    new Pandaren("Panda bimbo", 70, 2)
            )));
        }

        if (zonasACargar.contains(Zona.DESIERTO)) {
            localEnemigosPorZona.put(Zona.DESIERTO, new ArrayList<>(Arrays.asList(
                    new ElfoMago("El mago cocina del desierto", 40, 3),
                    new Enano("Pequeño Ladron del Desierto", 60, 4),
                    new Goblin("Goblin de las Arenas", 50, 4)
            )));
        }

        if (zonasACargar.contains(Zona.CIUDAD)) {
            localEnemigosPorZona.put(Zona.CIUDAD, new ArrayList<>(Arrays.asList(
                    new Humano("Pandillero", 45, 5),
                    new Humano("Ladron", 35, 6),
                    new Humano("Policia Corrupto", 70, 7)
            )));
        }

        return localEnemigosPorZona;
    }

    /**
     * Genera un enemigo de forma aleatoria dentro de las posibilidades
     * @param zona
     * @return enemigo*/
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
    /**
     * Simula una accion dentro del menu y crea el ecsenario de pelea con el enemigo nuevo*/
    private void explorar() {
        Personaje enemigo = generarEnemigoAleatorio(zonaActual);
        if (enemigo == null) {
            cambiarZona();
        } else {
            System.out.println("¡Has encontrado un " + enemigo.getNombre() + " en la " + zonaActual + "!");
            iniciarCombate(enemigo);
        }
    }
    /**
     * Utiliza la opcion next del scanner para frenar la consola en cuanto se llame en el codigo*/
    public void presionarParaContinuar() {
        System.out.println("Apreta cualquier tecla para continuar");
        scanner.next();
    }

    /**
     * Muestra el menu una vez ya iniciado sesion llamando todas las funciones anteriores*/
    public void mostrarMenu() {
        //crearArchivos();
        boolean menu = true;
        while (menu) {
            limpiarConsola();
            System.out.println("Menú principal:");
            System.out.println("1. Explorar");
            System.out.println("2. Ver estadísticas");
            System.out.println("3. Inventario");
            System.out.println("4. Ir a la tienda");
            System.out.println("5. Cambiar nombre de jugador");
            System.out.println("6. Guardar Partida");
            System.out.println("7. Salir");
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
                        cambiarNombreAnciano();
                    break;
                case 6:
                    System.out.println("Guardado");
                    guardarPersonaje(jugador);
                    break;
                case 7:
                    System.out.println("Saliendo del juego....");
                    menu=false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    /**
     * Utiliza el setNombre del jugador para cambiar el nombre del mismo*/
    public void cambiarNombreAnciano() throws RuntimeException{
        String dialogo =
                "El anciano dijo:\n" +
                        "Hijo mío, cambiar tu nombre no es una tarea sencilla. Requiere un sacrificio, algo que muestre tu compromiso y tu deseo de transformación.\n" +
        "Primero, debes sellar con un pacto de sangre.\n" +
                "Cuando lo hagas, recien ahi encontraras a tu verdadero ser.\n" +
                "No cualquier cosa servirá, este pacto tendra q tener un significado de verdad para ti.\n" +
                "Recuerda, el sacrificio no es solo un objeto, es una parte de ti, algo que demuestre tu disposición a dejar atrás el pasado para abrazar un nuevo futuro.\n" +
                "Solo entonces, pronunciando las palabras sagradas inscritas en la piedra, podrás cambiar el nombre de tu personaje.\n" +
                "Este no es un acto que deba tomarse a la ligera, joven aventurero. Piensa bien en lo que estás dispuesto a sacrificar.";
        System.out.println(dialogo);
        presionarParaContinuar();
        limpiarConsola();
        System.out.println("¿desea modificar su nombre? 0 es no, 1 es si");
        int opcion = scanner.nextInt();
        if(opcion==1) {
            scanner.nextLine();
            try{
                System.out.println("Cual va a ser su nuevo nombre ? : ");
                String nombreNuevo = scanner.nextLine();
                jugador.setNombre(nombreNuevo);
            }
            catch (Exception e){throw new RuntimeException("inserta un nombre valido por favor");}
            jugador.setVida(jugador.getVida() - 15);
            System.out.println(jugador.getNombre() + "has perdido 15 de vida por sellar el pacto, felicitaciones por tu nuevo yo");
            presionarParaContinuar();
        }else {
            System.out.println(
                               "El anciano dijo:\n" +
                               "Joven, si no tienes la firme intención de cambiar tu nombre, te aconsejo que no me molestes.\n" +
                        "Este proceso no es para los indecisos ni para aquellos que buscan solo entretenimiento.\n" +
                                "Requiere un verdadero compromiso y un sacrificio significativo.\n" +
                                "Mi tiempo es valioso, al igual que el tuyo. Si no estás dispuesto a hacer lo necesario,\n" +
                                "será mejor que busques otra manera de pasar tu tiempo.");
        }
    }

    /**
     * Inicia e interactua en el combate entre el enemigo y el usuario generando asi un combate con opciones para jugar
     * @param enemigo*/

    public void iniciarCombate(Personaje enemigo) {
        Random random = new Random();
        boolean jugadorVivo = true;
        boolean enemigoVivo = true;

        while (jugadorVivo && enemigoVivo) {
            System.out.println("\nVida del jugador: " + jugador.getVida());
            System.out.println("Vida del enemigo (" + enemigo.getNombre() + "): " + enemigo.getVida());
            System.out.println("\nTurno del jugador:");
            System.out.println("1. Atacar");
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
                presionarParaContinuar();
                System.out.println("Volviendo al ultimo checkpoint.....");
                enemigosPorZona= inicializarEnemigos(jugador.getNivel());
                jugador.setVida(100 + jugador.getNivel()*10);
                presionarParaContinuar();
            }
        }
    }

    /**
     * Crea un escenario para el usuario , el cual permite interactuar con una tienda de objetos a los cuales podra acceder mediante avance en el juego*/

    public void irTienda() {
        System.out.println("Hola Viajero necesito que me traigas mis fragmentos de vinilo para poder escuchar mis melodias favoritas");
        System.out.println("Mata a esas malditas alimañas que me robaron para recuperarlos, te dare tu primera recompensa cuando me traigas 3 fragementos y una segunda cuando tengas 6");
        presionarParaContinuar();
        limpiarConsola();
        if (jugador.inventario.contarElementos()>= 3) {
            Arma poderosa = new Arma("Gibson J-160E de J.L.", 2, 50, TipoArma.HIELO);
            System.out.println("Toma esta vieja guitarra sin cuerdas, te ayudara a golpearlos mejor");
            System.out.println("Has recibido "+ poderosa.getNombre());
            jugador.setArma(poderosa);
            presionarParaContinuar();
        } else if (jugador.inventario.contarElementos()>= 6) {
            Arma poderosaElectrica = new Arma("Bajo Iconico Hofner de 'Macca'", 2, 100, TipoArma.ELECTRICO);
            System.out.println("Toma este iconico Bajo Hofner electrico que me regalo un amigo, te servira en tu viaje");
            jugador.setArma(poderosaElectrica);
            System.out.println("Has recibido "+ poderosaElectrica.getNombre());
            presionarParaContinuar();
        } else if (jugador.inventario.contarElementos() >= 9) {
            Item entradasSorpresa = new Item("Par de entradas para el show de Paul Mcartney en River Plate", 1.0);
            System.out.println("GRACIAS POR AYUDARME A REUNIRME MI VINILO NUEVAMENTE!! \n Como agradecimiento te regalo este par de entradas para el 5/10. Mi amigo del bajo regalado previamente tocará en River.");
            jugador.agregarItemAlInventario(entradasSorpresa);
            System.out.println("Has recibido "+ entradasSorpresa.getNombre());
            presionarParaContinuar();

        } else {
            System.out.println("Sigues aqui? Ve a por mis vinilos");
            presionarParaContinuar();
        }
    }

    /**
     * Muestra las estadisticas del usuario*/
    private void verEstadisticas() {
        // Implementa la lógica para mostrar las estadísticas del jugador
        System.out.println("Mostrando estadísticas del jugador.");
    }

    /**
     * Cambia los escenarios mediante avanzas y derrotas enemigos en el juego*/
    private void cambiarZona() {
        switch (zonaActual) {
            case SELVA:
                zonaActual = Zona.DESIERTO;
                System.out.println("1er CHECKPOINT- SELVA COMPLETADA!");
                System.out.println("Guarda partida si lo crees necesario");
                presionarParaContinuar();

                break;
            case DESIERTO:
                zonaActual = Zona.CIUDAD;
                System.out.println("2do CHECKPOINT DESIERTO COMPLETADO!");
                System.out.println("Guarda partida si lo crees necesario");
                presionarParaContinuar();

                break;
            case CIUDAD:
                System.out.println("¡Completaste todas las zonas!");
                System.out.println("Gracias por jugar!!");
                presionarParaContinuar();
                return;
        }
        System.out.println("¡Has llegado a la " + zonaActual + "!");
    }

    /**
     * Baja en la consola lo suficiente como para simular que se limpia la consola*/
    public void limpiarConsola()
    {
        for(int i=0;i<100;i++){
            System.out.println();
        }
    }


    /**
     * Lee un String con un imput
     * @return opcion*/
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
    /**
     * Crea el jugador para iniciar a jugar y lo retorna
     * @return nuevoPersonaje*/
    public Personaje crearJugadors() {
        double vida=100;
        System.out.println("Creación de personaje:");

        // Nombre del personaje
        System.out.print("Ingresa el nombre del personaje: ");
        String nombre = leerUnInputString();

        // Clase del personaje
        System.out.print("Elige una clase (Enano, Humano, NoMuerto, Pandaren, ElfoMago, Goblin): ");
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
            case ElfoMago:
                nuevoPersonaje = new ElfoMago(nombre, vida);
                break;
            case Goblin:
                nuevoPersonaje = new Goblin(nombre, vida);
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