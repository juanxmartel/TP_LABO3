package Genericas;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaGenerica <T> implements IGenerica<T>, Serializable {

     ArrayList<T> listaGenerica;

 public ListaGenerica() {listaGenerica= new ArrayList<>();}
    @Override
    public <T1> void agregarElemento(T1 elemento) {

    }

    @Override
    public <T1> void eliminarElemento(T1 elemento) {

    }

    @Override
    public int contarElementos() {
        return 0;
    }

    @Override
    public String listarElementos() {
        return "";
    }
}

