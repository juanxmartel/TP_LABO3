package Modelos.Clases;

import Interfaces.iAtaque;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.util.ArrayList;

public class Enano extends Personaje implements iAtaque {

    public Enano(String nombre, double vida, int nivel, int experiencia, int experienciaNecesariaParaSubir, Arma arma, ArrayList<Item> inventario) {
        super(nombre, vida, nivel, experiencia, experienciaNecesariaParaSubir, arma, inventario);
    }

    @Override
    public void agregarObjeto() {

    }

    @Override
    public void retirarObjeto() {

    }

    @Override
    public void verInventario() {

    }

    @Override
    public void calcularPeso() {

    }

    @Override
    public void robarObjeto() {

    }
    @Override
    public void placaje(Personaje enemigo) {
        double dano = 10; // Daño de ejemplo
        enemigo.recibirDanio(dano);
        System.out.println(nombre + " usa Placaje contra " + enemigo.getNombre() + " causando " + dano + " de daño.");
    }

    public void cabezazo(Personaje enemigo) {
        double dano = 15; // Daño de ejemplo
        double autoDanio = 5; // Daño recibido por el propio personaje
        enemigo.recibirDanio(dano);
        this.recibirDanio(autoDanio);
        System.out.println(nombre + " usa Cabezazo contra " + enemigo.getNombre() + " causando " + dano + " de daño y recibiendo " + autoDanio + " de daño.");
    }
}
