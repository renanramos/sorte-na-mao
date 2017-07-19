package com.deviscoming.sortenamao.conteudo;

import android.content.Context;
import android.net.ConnectivityManager;

import com.deviscoming.sortenamao.pojo.SorteNaMaoLotoFacil;
import com.deviscoming.sortenamao.pojo.SorteNaMaoMegaSena;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Renan Rodrigues Ramos on 29/06/2016.
 */
public class SorteNaMaoUtil {

    public static SorteNaMaoMegaSena getSorteNaMaoMegaSena(String link){

        SorteNaMaoMegaSena sorteNaMao = new SorteNaMaoMegaSena();

        try{
            Document doc = Jsoup.connect(link).get();

            Elements numeros = doc.select("ul.numbers");

            Elements infoParagrafo = doc.select("div.totals").select("span.value");

            String info[] = infoParagrafo.html().split("<span>");

            sorteNaMao.setValoresAcumulados(info[0].split("\n"));

            sorteNaMao.setProximoPremio(doc.select("div.next-prize").select("p.value").text());

            sorteNaMao.setListaNumeros(numeros.text().split(" "));

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return sorteNaMao;
    }

    public static SorteNaMaoLotoFacil getSorteNaMaoLotoFacil(String link){

        SorteNaMaoLotoFacil sorteNaMaoLotoFacil = new SorteNaMaoLotoFacil();
        String[] valores = new String[15];
        String[] aux = new String[3];

        try{
            Document doc = Jsoup.connect(link).get();

            sorteNaMaoLotoFacil.setLocalSorteio(doc.select("div.resultado-loteria").select("p.description").select("p").text());

            sorteNaMaoLotoFacil.setProximoPremio(doc.select("div.next-prize").select("p.value").text());

            sorteNaMaoLotoFacil.setAcumulado(doc.select("div.totals").select("span.value").text());

            Elements tabelaValores = doc.select("div.resultado-loteria").select("table.simple-table").select("tr");

            for(int i = 0; i < tabelaValores.size(); i++){
                aux = tabelaValores.text().split(" ");
                for(int j = 0; j < aux.length; j++){
                    valores[j] = aux[j];
                }
            }

            sorteNaMaoLotoFacil.setResultado(valores);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return sorteNaMaoLotoFacil;
    }

    public static boolean validaConexao(Context context){
        boolean statusConexao = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected()) {
            statusConexao = true;
        }
        return statusConexao;
    }

}
