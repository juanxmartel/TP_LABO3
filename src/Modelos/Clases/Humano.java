package Modelos.Clases;

import Enums.TipoArma;
import Genericas.ListaGenerica;
import Interfaces.IPlacaje;
import Interfaces.iCabezaso;
import Modelos.Objeto.Arma;
import Modelos.Objeto.Item;

import java.util.Scanner;

public class Humano extends Personaje implements IPlacaje, iCabezaso {

    Scanner scanner;



    public Humano(String nombre, double vida, int nivel, ListaGenerica<Item> inventario) {
        super(nombre, vida, 1, inventario);
        Arma Latigo = new Arma("Latigo",2,20, TipoArma.ELECTRICO);
        this.setArma(Latigo);
    }

    @Override
    public double placaje (Personaje enemigo) {
        double danio=15*getNivel();
        danio-= enemigo.getNivel()*3 ;
        System.out.println("Arremete con su cuerpo sobre el enemigo haciendole "+danio);
        return danio;
    }

    @Override
    public double cabezaso(Personaje enemigo) {
        double danio=20;
        danio-= enemigo.getNivel()*3 ;
        System.out.println("Arremete con su craneo sobre el enemigo haciendole "+danio);
        return danio;
    }

    public double superioridadRacial(Personaje enemigo){

        double danio=5;
        danio-= enemigo.getNivel()*3 ;
        if(enemigo instanceof Goblin || enemigo instanceof NoMuerto)
        {
            danio=40;
            System.out.println("Te burlaste tan fuerte de el que le sacaste "+danio +" de vida");

        }else {System.out.println("tu burla es poco efectiva le hiciste "+danio);}
        return danio;
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("Tus ataques son:");
        System.out.println("1: ataque espada");
        System.out.println("2: cabezaso");
        System.out.println("3: superioridad racial");
        System.out.println("4: placaje");
        double danio=0;
        scanner = new Scanner(System.in);
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.atacarConArma();
            case 2: danio = this.cabezaso(objetivo);
            case 3: danio = this.superioridadRacial(objetivo);
            case 4 : danio= this.placaje(objetivo);
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno =) ");
        }
        objetivo.recibirDanio(danio);
    }
}
