package Modelos.Clases;

import Genericas.ListaGenerica;
import Modelos.Objeto.Arma;
import Interfaces.iManejoInventario;
import Modelos.Objeto.Item;

import java.util.ArrayList;

public abstract class Personaje implements iManejoInventario {
    private String nombre;
    private double dano;
    private double vida;
    private int nivel;
    private int experiencia;
    private Arma arma;
    private ListaGenerica<Item> inventario;

    // agregar arma

    //implementar interfaz habilidad

    // crear clase de tipo objetosInventario

    //enum estado de animo


}
