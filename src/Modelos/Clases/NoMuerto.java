package Modelos.Clases;

import Interfaces.iNecormancia;

import java.util.Scanner;

public class NoMuerto extends Humano implements iNecormancia {

    Scanner scanner;

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("Tus ataques son:");
        System.out.println("1: ataque espada");
        System.out.println("2: cabezaso");
        System.out.println("3: superioridad racial");
        System.out.println("4: placaje");
        System.out.println("5: necrosar");
        double danio=0;
        scanner = new Scanner(System.in);
        int opcion=scanner.nextInt();
        switch (opcion){
            case 1: danio = this.atacarConArma();
            case 2: danio = this.cabezaso(objetivo);
            case 3: danio = this.superioridadRacial(objetivo);
            case 4 : danio= this.placaje(objetivo);
            case 5 : danio=this.necrosar();
            default:
                System.out.println("no elegiste bien la opcion, perdiste tu turno =) ");
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
    public double cabezaso(Personaje enemigo) {
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
