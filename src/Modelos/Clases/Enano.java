package Modelos.Clases;

import Interfaces.IPlacaje;

import java.util.Scanner;

public class Enano extends Personaje implements IPlacaje {

    Scanner scanner;
    public void atacar(Personaje objetivo){
        System.out.println("Tus ataques son:");
        System.out.println("1: placaje");
        System.out.println("2: ataque espada");
       double danio=0;
        scanner = new Scanner(System.in);
        scanner.nextInt();
       objetivo.recibirDanio(danio);
    }


    @Override
    public double placaje(Personaje enemigo) {
        double danio=20;
        danio-= enemigo.getNivel()*3 ;
        if(enemigo instanceof NoMuerto)
        {
            danio=9999999;
            System.out.println("Desarmaste el pobre cadaver");

        }
        System.out.println("Arremete con su pequeño cuerpo sobre el enemigo haciendole "+danio);
        return danio;

    }
}
