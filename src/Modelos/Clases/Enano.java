package Modelos.Clases;

import Genericas.ListaGenerica;
import Interfaces.IPlacaje;
import Interfaces.iCabezaso;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class Enano extends Personaje implements IPlacaje, iCabezaso {

    Scanner scanner;

    public Enano(String nombre, double vida, int nivel, int experiencia, int experienciaNecesariaParaSubir, Arma arma, ListaGenerica<Item> inventario) {
        super(nombre, vida, nivel, experiencia, experienciaNecesariaParaSubir, arma, inventario);
    }

    public void atacar(Personaje objetivo){
        System.out.println("Tus ataques son:");
        System.out.println("1: placaje");
        System.out.println("2: ataque espada");
        System.out.println("3: cabezaso");
        double danio=0;
        scanner = new Scanner(System.in);
        scanner.nextInt();
        objetivo.recibirDanio(danio);
    }

    @Override
        public double placaje (Personaje enemigo) {
        double danio=20*getNivel();
        danio-= enemigo.getNivel()*3 ;
        if(enemigo instanceof NoMuerto)
        {
            danio=9999999;
            System.out.println("Desarmaste el pobre cadaver");

        }
        System.out.println("Arremete con su pequeño cuerpo sobre el enemigo haciendole "+danio);
        return danio;
    }

    @Override
    public double cabezaso(Personaje enemigo) {
        double danio=20;
        danio-= enemigo.getNivel()*3 ;
        if(enemigo instanceof Pandaren)
        {
            System.out.println("Le pegaste en la rodilla y te hiciste "+danio +"de daño");
            this.setVida(getVida()-danio);
            danio=0;

        }
        System.out.println("Arremete con su pequeño craneo sobre el enemigo haciendole "+danio);
        return danio;
    }
}


