package Modelos.Clases;

import Enums.TipoArma;
import Genericas.ListaGenerica;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.util.Scanner;

public class Goblin extends Personaje {
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
        System.out.println("1: ataque arma");
        System.out.println("2: ataque rapido");
        double danio=0;
        scanner = new Scanner(System.in);
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.atacarConArma();
            case 2: danio = this.ataqueRapido(objetivo);
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno =) ");
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
