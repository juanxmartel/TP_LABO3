package Genericas;

import Modelos.Objeto.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaGenerica <T> implements IGenerica<T>, Serializable {

     ArrayList<T> listaGenerica;

 public ListaGenerica() {listaGenerica= new ArrayList<>();}
    @Override
    public <T1> void agregarElemento(T1 elemento) {
     listaGenerica.add((T) elemento);

    }

    @Override
    public <T1> void eliminarElemento(T1 elemento) {
        listaGenerica.remove((T) elemento);
    }

    @Override
    public int contarElementos() {
        int i =0;
        for(T elemento:listaGenerica)
        {
            i++;
        }
        return i;
    }

    @Override
    public void listarElementos() {
        int i =0;
        for(T objetos:listaGenerica)
        {
            System.out.println(i+") "+objetos.toString());
            i++;
        }

    }

    public T devolverUno(int indice){
     T cosa =listaGenerica.get(indice);
     return cosa;
    }

    public int contarElementosEspecifico(T elemnto) {
        int i =0;
        for(T elemento:listaGenerica)
        {
            if(elemento==elemnto)
            {i++;}
        }
        return i;
    }

    public void eliminarElementosEspecifico(T elemnto) {
        for(T elemento:listaGenerica)
        {
            if(elemento==elemnto)
            {listaGenerica.remove(elemento);}
        }
    }
}

