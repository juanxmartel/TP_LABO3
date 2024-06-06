package Interfaces;

import Modelos.Clases.Personaje;

public interface iManejoInventario {
    void agregarObjeto();
    void retirarObjeto();
    void verInventario();
    void calcularPeso();
    void agarrarObjeto(Personaje enemigo);
    void robarObjeto();

}
