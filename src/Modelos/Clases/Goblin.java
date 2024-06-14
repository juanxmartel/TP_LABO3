package Modelos.Clases;

import Enums.TipoArma;
import Genericas.ListaGenerica;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.io.Serializable;
import java.util.Scanner;


/**
 * Clase para una de las tantas clases de personajes "Goblin" */
public class Goblin extends Personaje implements Serializable {
    Scanner scanner;


    public Goblin(String nombre, double vida) {
        super(nombre, vida,1 );
        Arma ArcoYFlecha = new Arma("ArcoYFlechas",14,40, TipoArma.FUEGO);
        this.setArma(ArcoYFlecha);
    }
    public Goblin(String nombre, double vida, int nivel) {
        super(nombre, vida,nivel );
        Arma ArcoYFlecha = new Arma("ArcoYFlechas",14,40, TipoArma.FUEGO);
        this.setArma(ArcoYFlecha);
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("Tus ataques son:");
        System.out.println("1. Ataque arma");
        System.out.println("2. Ataque rapido");
        double danio=0;
        scanner = new Scanner(System.in);
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.atacarConArma();
                break;
            case 2: danio = this.ataqueRapido(objetivo);
                break;
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno =) ");
                break;
        }
        objetivo.recibirDanio(danio);
    }

    public double ataqueRapido(Personaje objetivo)
    {
        double dano=15;
        if(objetivo instanceof Enano)
        {
            dano=30*this.getNivel();
        }

        return dano;
    }


}
