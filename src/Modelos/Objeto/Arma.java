package Modelos.Objeto;

import Enums.TipoArma;

public abstract class Arma extends Item {
    private double dano;
    private TipoArma tipo;

    public double getDano() {
        return dano;
    }

    public Arma(String nombre, double peso,int id, double dano, TipoArma tipo) {
        super(nombre, peso, id);
        this.dano = dano;
        this.tipo = tipo;
    }
}