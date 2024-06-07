package Modelos.Clases;

import Genericas.ListaGenerica;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class Personaje implements Serializable {
    private String nombre;
    private double vida;
    private int nivel;
    private Arma arma;
    public ListaGenerica<Item> inventario;

    public Personaje(String nombre, double vida, int nivel) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = nivel;
        this.inventario = new ListaGenerica<Item>();
    }

    public String getNombre() {
        return nombre;
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

    //implementar interfaz inventario


    public ListaGenerica<Item> getInventario() {
        return inventario;
    }

    public void agarrarObjeto(Personaje objetivo) {
        Scanner scanner = new Scanner(System.in); // Crear Scanner aquí para reutilizarlo

        objetivo.inventario.listarElementos();
        System.out.println("¿Cuál desea agarrar?");
        int opcion = scanner.nextInt();

        // Verificar que el índice es válido
        if (opcion >= 0 && opcion < objetivo.inventario.contarElementos()) {
            Item itemAgarrado = objetivo.inventario.devolverUno(opcion);
            this.inventario.agregarElemento(itemAgarrado);
            objetivo.inventario.eliminarElemento(itemAgarrado);
            System.out.println("Has agarrado: " + itemAgarrado);
        } else {
            System.out.println("Opción no válida.");
        }

        // No cerrar el Scanner si se reutiliza en otras partes del código
    }
    @Override
    public String toString() {
        return "Personaje{" +
                "nombre= " + nombre +
                "vida= " + vida +
                "nivel= " + nivel +
                "arma= " + arma.getNombre() + arma.getDano();
    }

    public void verInventario()
    {
        inventario.listarElementos();
    }

    Scanner scanner;


    //Ataque
    public abstract void atacar(Personaje objetivo);

    public void recibirDanio(double danio)
    {
        System.out.println(this.getNombre()+" recibio " +danio +"daño");
        this.setVida(getVida()-danio);

    }
    public double atacarConArma()
    {
        return arma.getDano();
    }

    public void subirNivel() {
        this.nivel++;
        System.out.println(getNombre() + " ha subido al nivel " + getNivel() + "!");
        this.vida += 20; // Incrementar la vida como recompensa por subir de nivel
    }

    //enum estado de animo


    //stats
    public void mostrarEstadisticas(){
        System.out.println("Personaje{" + "\n" +
                "nombre=" + nombre + "\n" +
                "vida=" + vida + "\n" +
                "nivel=" + nivel + "\n" +
                "daño=" + arma.getDano() + "\n");
    }
}
