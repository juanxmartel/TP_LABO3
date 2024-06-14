package Modelos.Clases;

import Genericas.ListaGenerica;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


/**
 * Clase que gestiona a todos los personajes posibles para crear dentro del juego*/
public class Personaje implements Serializable {
    private String nombre;
    private double vida;
    private int nivel;
    private Arma arma;
    public ListaGenerica<Item> inventario;

    public Personaje(){

    }

    public Personaje(String nombre, double vida, int nivel) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = nivel;
        this.inventario = new ListaGenerica<Item>();
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public int getNivel() {
        return nivel;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personaje personaje)) return false;
        return Double.compare(getVida(), personaje.getVida()) == 0 && getNivel() == personaje.getNivel() && Objects.equals(getNombre(), personaje.getNombre()) && Objects.equals(getArma(), personaje.getArma());
    }

    @Override
    public int hashCode() {
        return 1;
    }


    public ListaGenerica<Item> getInventario() {
        return inventario;
    }

    /**
     * Funcion para agarrar un objeto luego de un combate
     * @param objetivo*/
    public void agarrarObjeto(Personaje objetivo) {
        Scanner scanner = new Scanner(System.in); // Crear Scanner aquí para reutilizarlo

        objetivo.inventario.listarElementos();
        System.out.println("¿Cuál desea agarrar?");

        int opcion = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                opcion = scanner.nextInt();
                entradaValida = true; // Si no se lanza una excepción, la entrada es válida
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }

        if (opcion >= 0 && opcion < objetivo.inventario.contarElementos()) {
            Item itemAgarrado = objetivo.inventario.devolverUno(opcion);
            this.inventario.agregarElemento(itemAgarrado);
            objetivo.inventario.eliminarElemento(itemAgarrado);
            System.out.println("Has agarrado: " + itemAgarrado);
        } else {
            System.out.println("Opción no válida.");
        }
    }


    public void agregarItemAlInventario(Item item) {
        this.inventario.agregarElemento(item);
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre= " + nombre +
                "vida= " + vida +
                "nivel= " + nivel +
                "arma= " + arma.getNombre() + arma.getDano();
    }

    /**
     * Muestra el inventario*/
    public void verInventario()
    {
        inventario.listarElementos();
    }

    /**
     * Funcion para atacar al enemigo
     * @param objetivo*/
    public  void atacar(Personaje objetivo){
        System.out.println("Los personajes ficticios no atacan");
    }

    /**
     * Recibe el daño generado por el enemigo*/
    public void recibirDanio(double danio)
    {
        System.out.println(this.getNombre()+" recibio " +danio +"daño");
        this.setVida(getVida()-danio);

    }
    public double atacarConArma()
    {
        return arma.getDano();
    }


    /**
     * Modifica el nivel del usuario luego de eliminar al enemigo*/
    public void subirNivel() {
        this.nivel++;
        System.out.println(getNombre() + " ha subido al nivel " + getNivel() + "!");
        setVida(100+ nivel*10);
        this.vida += 20; // Incrementar la vida como recompensa por subir de nivel
    }
    /**
     * Muestra las estadisicas del personaje con el que se esta jugando*/
    public void mostrarEstadisticas(){
        System.out.println(
                "Nombre=" + nombre + "\n" +
                "Vida=" + vida + "\n" +
                "Nivel=" + nivel + "\n" +
                "Arma=" + arma.getNombre()+ "\n" +
                "Daño Arma=" + arma.getDano() + "\n");
    }


}
