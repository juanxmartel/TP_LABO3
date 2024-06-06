package Modelos.Clases;

import Modelos.Objeto.Arma;
import Interfaces.iManejoInventario;
import Modelos.Objeto.Item;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class Personaje implements iManejoInventario {
    private String nombre;
    private double vida;
    private int nivel;
    private int experiencia;
    private int experienciaNecesariaParaSubir;
    private Arma arma;
    private ArrayList<Item> inventario;

    public Personaje(String nombre, double vida, int nivel, int experiencia, int experienciaNecesariaParaSubir, Arma arma, ArrayList<Item> inventario) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.experienciaNecesariaParaSubir = experienciaNecesariaParaSubir;
        this.arma = arma;
        this.inventario = inventario;
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
        return Double.compare(getVida(), personaje.getVida()) == 0 && getNivel() == personaje.getNivel() && getExperiencia() == personaje.getExperiencia() && getExperienciaNecesariaParaSubir() == personaje.getExperienciaNecesariaParaSubir() && Objects.equals(getNombre(), personaje.getNombre()) && Objects.equals(getArma(), personaje.getArma()) && Objects.equals(getInventario(), personaje.getInventario());
    }

    @Override
    public int hashCode() {
        return 1;
    }

    //implementar interfaz inventario

    @Override
    public void agregarObjeto(Item objeto) {
        inventario.add(objeto);

    }

    @Override
    public void retirarObjeto(Item objeto) {
        inventario.remove(objeto);

    }

    @Override
    public void verInventario() {
        int i =0;
        for(Item objetos:inventario)
        {
            System.out.println(i);
            System.out.println(objetos.toString());
            i++;
        }

    }

    @Override
    public void calcularPeso() {
    for(Item objetos:inventario){
        double peso=0;
        peso+= objetos.getPeso();
    }

    }

    Scanner scanner;
    @Override
    public void agarrarObjeto(Personaje objetivo) {
        objetivo.verInventario();
        new Scanner(System.in);
        System.out.println("cual desea agarrar?");
        int opcion= scanner.nextInt();
        this.inventario.add(objetivo.inventario.get(opcion));
    }

    public void agarrarTodosLosObjetos(Personaje objetivo)
    {
        for(Item objetos:objetivo.inventario)
        {
            int opcion=0;
            this.inventario.add(objetivo.inventario.get(opcion));
            opcion++;
        }
    }

    //Ataque
    public void atacar(Personaje objetivo){
        double danio = this.arma.getDano();
        danio= danio-objetivo.nivel*2;
        System.out.println(this.getNombre() + " ha hecho " + danio + " puntos de daño a " + objetivo.nombre + ".");
        objetivo.vida-=danio;
        System.out.println(objetivo.nombre + " tiene " + objetivo.getVida() + " puntos de salud restantes.");
    }

    public void recibirDanio(double dano) {
        this.vida -= dano;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    //enum estado de animo


}
