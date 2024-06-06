package Modelos.Objeto;

public abstract class Item {
    private String nombre;
    private double peso;
    private int id;


    public Item(String nombre, double peso) {
        this.id=id++;
        this.nombre = nombre;
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public String getNombre() {
        return nombre;
    }
}


