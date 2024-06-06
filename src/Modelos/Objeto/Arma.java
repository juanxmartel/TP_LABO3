package Modelos.Objeto;

import Enums.TipoArma;

public abstract class Arma extends Item {
    private double dano;
    private TipoArma tipo;

    public double getDano() {
        return dano;
    }
    public void setDano(double danio){this.dano=danio;}

}