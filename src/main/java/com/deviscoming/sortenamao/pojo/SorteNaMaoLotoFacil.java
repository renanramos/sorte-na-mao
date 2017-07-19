package com.deviscoming.sortenamao.pojo;

/**
 * Created by Renan Rodrigues Ramo on 06/07/2016.
 */
public class SorteNaMaoLotoFacil {

    private String localSorteio;
    private String[] resultado;
    private String proximoPremio;
    private String acumulado;


    public String getLocalSorteio() {
        return localSorteio;
    }

    public void setLocalSorteio(String localSorteio) {
        this.localSorteio = localSorteio;
    }

    public String[] getResultado() {
        return resultado;
    }

    public void setResultado(String[] resultado) {
        this.resultado = resultado;
    }

    public String getProximoPremio() {
        return proximoPremio;
    }

    public void setProximoPremio(String proximoPremio) {
        this.proximoPremio = proximoPremio;
    }

    public String getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(String acumulado) {
        this.acumulado = acumulado;
    }
}
