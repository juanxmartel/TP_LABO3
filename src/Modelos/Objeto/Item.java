package Modelos.Objeto;

public abstract class Item {
    private String nombre;
    private double peso;


    public Item(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }
}
