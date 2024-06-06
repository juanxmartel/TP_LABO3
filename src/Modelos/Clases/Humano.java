package Modelos.Clases;

import Interfaces.IPlacaje;

public class Humano extends Personaje implements IPlacaje {


    @Override
    public double placaje (Personaje enemigo) {
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

    @Override
    public void atacar(Personaje objetivo) {

    }
}
