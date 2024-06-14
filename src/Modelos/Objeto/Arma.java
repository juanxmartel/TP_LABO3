package Modelos.Objeto;

import Enums.TipoArma;

import java.io.Serializable;


/**
 * Clase asignada a la organizacion de las armaas dentro del juego*/
public class Arma extends Item implements Serializable {
    private double dano;
    private TipoArma tipo;

    public Arma(String nombre, double peso, double dano, TipoArma tipo) {
        super(nombre, peso);
        this.dano = dano;
        this.tipo = tipo;
    }


    public TipoArma getTipo() {
        return tipo;
    }

    public void setTipo(TipoArma tipo) {
        this.tipo = tipo;
    }

    public double getDano() {
        return dano;
    }

    public void setDano(double danio){
        this.dano=danio;
    }

}