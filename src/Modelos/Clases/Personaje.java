package Modelos.Clases;

import Genericas.ListaGenerica;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class Personaje  {
    private String nombre;
    private double vida;
    private int nivel;
    private int experiencia;
    private int experienciaNecesariaParaSubir;
    private Arma arma;
    private ListaGenerica<Item> inventario;

    public Personaje(String nombre, double vida, int nivel, int experiencia, int experienciaNecesariaParaSubir, Arma arma, ListaGenerica<Item> inventario) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.experienciaNecesariaParaSubir = experienciaNecesariaParaSubir;
        this.arma = arma;
        this.inventario = new ListaGenerica<Item>();
    }

    public String getNombre() {
        return nombre;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getExperienciaNecesariaParaSubir() {
        return experienciaNecesariaParaSubir;
    }

    public void setExperienciaNecesariaParaSubir(int experienciaNecesariaParaSubir) {
        this.experienciaNecesariaParaSubir = experienciaNecesariaParaSubir;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personaje personaje)) return false;
        return Double.compare(getVida(), personaje.getVida()) == 0 && getNivel() == personaje.getNivel() && getExperiencia() == personaje.getExperiencia() && getExperienciaNecesariaParaSubir() == personaje.getExperienciaNecesariaParaSubir() && Objects.equals(getNombre(), personaje.getNombre()) && Objects.equals(getArma(), personaje.getArma());
    }

    @Override
    public int hashCode() {
        return 1;
    }

    //implementar interfaz inventario



    Scanner scanner;

    public void agarrarObjeto(Personaje objetivo) {
        objetivo.inventario.listarElementos();
        new Scanner(System.in);
        System.out.println("cual desea agarrar?");
        int opcion= scanner.nextInt();
        this.inventario.agregarElemento(objetivo.inventario.devolverUno(opcion));
    }


    //Ataque
    public abstract void atacar(Personaje objetivo);

    public void recibirDanio(double danio)
    {
        this.setVida(getVida()-danio);

    }


    //enum estado de animo


}
