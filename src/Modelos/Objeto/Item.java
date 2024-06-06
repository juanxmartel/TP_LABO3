package Modelos.Objeto;

public abstract class Item {
    private String nombre;
    private double peso;
    private int id;

    public Item(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
        this.id++;
    }

    public double getPeso() {
        return peso;
    }
}


