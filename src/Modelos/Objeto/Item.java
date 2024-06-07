package Modelos.Objeto;

public class Item {
    private String nombre;
    private double peso;
    private int id;

    public Item(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
        this.id++;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }
}


