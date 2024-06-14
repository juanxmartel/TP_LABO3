package Modelos.Objeto;

import java.io.Serializable;


/**
 * Clase para items dentro del juego*/
public class Item implements Serializable {
    private static final long serialVersionUID = 6394049200017074735L; // Mantén este serialVersionUID consistente
    private String nombre;
    private double peso;
    private int id;
    private static int contador = 0;

    public Item(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
        this.id = ++contador; // Incrementa el id correctamente
    }

    @Override
    public String toString() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public String getNombre() {
        return nombre;
    }
}


