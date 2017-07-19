package com.deviscoming.sortenamao.pojo;

import java.util.List;
/**
 * Created by Renan Rodrigues Ramos on 22/06/2016.
 */
public class SorteNaMaoMegaSena {

    private String proximoPremio;
    private String[] listaNumeros;
    private String[] infoParagrafo;
    private String[] valoresAcumulados;

    public String[] getListaNumeros() {
        return listaNumeros;
    }

    public void setListaNumeros(String[] listaNumeros) {
        this.listaNumeros = listaNumeros;
    }

    public String getProximoPremio() {
        return proximoPremio;
    }

    public void setProximoPremio(String proximoPremio) {
        this.proximoPremio = proximoPremio;
    }

    public String[] getInfoParagrafo() {
        return infoParagrafo;
    }

    public void setInfoParagrafo(String[] infoParagrafo) {
        this.infoParagrafo = infoParagrafo;
    }

    public String[] getValoresAcumulados() {
        return valoresAcumulados;
    }

    public void setValoresAcumulados(String[] valoresAcumulados) {
        this.valoresAcumulados = valoresAcumulados;
    }
}
