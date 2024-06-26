package Modelos.Clases;

import Enums.TipoArma;
import Genericas.ListaGenerica;
import Interfaces.IPlacaje;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.io.Serializable;
import java.util.Scanner;


/**
 * Clase para una de las tantas clases de personajes "Pandaren" */
public class Pandaren extends Personaje implements IPlacaje , Serializable {

    Scanner scanner;

    public Pandaren(String nombre, double vida) {
        super(nombre, vida, 1);
        Arma PalitoBambu = new Arma("PalitoBambu",4.5,15, TipoArma.AGUA);
        this.setArma(PalitoBambu);
    }
    public Pandaren(String nombre, double vida, int nivel) {
        super(nombre, vida, 1);
        Arma PalitoBambu = new Arma("PalitoBambu",4.5,15, TipoArma.AGUA);
        this.setArma(PalitoBambu);
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("Tus ataques son:");
        System.out.println("1. Ataque espada");
        System.out.println("2. Placaje");
        double danio=0;
        scanner = new Scanner(System.in);
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.atacarConArma();
                break;
            case 2 : danio= this.placaje(objetivo);
                break;
            default:
                System.out.println("No elegiste bien la opcion, perdiste tu turno =) ");
                break;
        }
        objetivo.recibirDanio(danio);
    }

    @Override
    public double placaje(Personaje enemigo) {
        double danio=20*getNivel();
        danio-= enemigo.getNivel()*3 ;
        if(enemigo instanceof NoMuerto || enemigo instanceof Enano)
        {
            danio=9999999;
            System.out.println("Desarmaste el pobre enemigo");

        }
        System.out.println("Arremete con su pesado cuerpo sobre el enemigo haciendole "+danio);
        return danio;
    }


}
