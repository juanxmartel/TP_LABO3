package Modelos.Objeto;

import Enums.TipoArma;

public abstract class Arma extends Item {
    private double dano;
    private TipoArma tipo;

    public double getDano() {
        return dano;
    }

<<<<<<< HEAD
    public Arma(String nombre, double peso,int id, double dano, TipoArma tipo) {
        super(nombre, peso, id);
=======
    public Arma(String nombre, double peso, double dano, TipoArma tipo) {
        super(nombre, peso);
>>>>>>> parent of 0f512fd (Modfiicaciones locuras)
        this.dano = dano;
        this.tipo = tipo;
    }
}