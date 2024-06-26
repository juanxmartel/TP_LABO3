package Modelos.Clases;

import Interfaces.iNecormancia;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Clase para una de las tantas clases de personajes "NoMuerto" el cual proviene del Humano */
public class NoMuerto extends Humano implements iNecormancia, Serializable {

    Scanner scanner;

    public NoMuerto(String nombre, double vida,int nivel) {
        super(nombre,vida,nivel);
    }
    public NoMuerto(String nombre, double vida) {
        super(nombre, vida);
    }




    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("Tus ataques son:");
        System.out.println("1. Ataque espada");
        System.out.println("2. Cabezazo");
        System.out.println("3. Superioridad racial");
        System.out.println("4. Placaje");
        System.out.println("5. Necrosar");
        double danio=0;
        scanner = new Scanner(System.in);
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.atacarConArma();
                break;
            case 2: danio = this.cabezazo(objetivo);
                break;
            case 3: danio = this.superioridadRacial(objetivo);
                break;
            case 4 : danio= this.placaje(objetivo);
                break;
            case 5 : danio=this.necrosar();
                break;
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno =) ");
                break;
        }
        objetivo.recibirDanio(danio);
    }

    @Override
    public double placaje(Personaje enemigo) {
        return super.placaje(enemigo);
    }

    @Override
    public double superioridadRacial(Personaje enemigo) {

        double danio=5;
        danio-= enemigo.getNivel()*3 ;
        if(enemigo instanceof Goblin)
        {
            danio=25;
            System.out.println("Te burlaste tan fuerte de el que le sacaste "+danio +" de vida");
            System.out.println("Que suerte que los goblins que no se dieron cuenta que sos un no muerto");

        }else {System.out.println("tu burla es poco efectiva le hiciste "+danio);}
        return danio;
    }

    @Override
    public double cabezazo(Personaje enemigo) {
        double danio=15;
        this.setVida(getVida()-danio);
        System.out.println("Como se te ocurre hacer un cabezaso siendo un no muerto!!");
        System.out.println("Te hiciste "+danio);
        danio=0;
        return danio;
    }

    public double necrosar(){
        double danio=20*(this.getNivel()-3);
        System.out.println("Le pudriste los huesos");
        System.out.println("Le hiciste "+danio);
        return danio;
    }


}
