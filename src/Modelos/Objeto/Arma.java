package Modelos.Objeto;

import Enums.TipoArma;

public abstract class Arma extends Item {
    private double dano;
    private TipoArma tipo;

    public Arma(String nombre, double peso, double dano, TipoArma tipo) {
        super(nombre, peso);
        this.dano = dano;
        this.tipo = tipo;
    }

    public double getDano() {
        return dano;
    }

}