package Modelos.Objeto;

import Enums.TipoArma;

public class Arma extends Item {
    private double dano;
    private TipoArma tipo;

    public Arma(String nombre, double peso, double dano, TipoArma tipo) {
        super(nombre, peso);
        this.dano = dano;
        this.tipo = tipo;
    }

    public String getNombre(){
        return this.getNombre();
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