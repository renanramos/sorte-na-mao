package com.deviscoming.sortenamao;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.deviscoming.sortenamao.pojo.SorteNaMaoMegaSena;
import com.deviscoming.sortenamao.conteudo.SorteNaMaoUtil;

/**
 * Created by Renan Rodrigues Ramos on 22/06/2016.
 */
public class MegaSenaActivity extends AppCompatActivity{

    public SorteNaMaoMegaSena sorteNaMao;
    public String proximoPremio = "";
    public String[] numerosSorteados = new String[6];
    public String[] valoresAcumulados = new String[3];
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private boolean statusConexao = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mega_sena);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new SorteNaMaoSyncDados().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class SorteNaMaoSyncDados extends AsyncTask{

        SorteNaMaoMegaSena sorteNaMao = new SorteNaMaoMegaSena();
        ProgressDialog progress;
        String link = "http://loterias.caixa.gov.br/wps/portal/loterias/landing/megasena";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(MegaSenaActivity.this);
            progress.setTitle("Processando...");
            progress.setMessage("Por favor, aguarde!");
            progress.setCancelable(false);
            progress.setIndeterminate(true);
            progress.show();
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Object doInBackground(Object[] params) {
              sorteNaMao = SorteNaMaoUtil.getSorteNaMaoMegaSena(link);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

                if (sorteNaMao.getProximoPremio() == "") {
                    sorteNaMao.setProximoPremio("  R$  0,00");
                }

                if (sorteNaMao.getListaNumeros() == null) {
                    sorteNaMao.setListaNumeros(new String[6]);
                }

                if (sorteNaMao.getValoresAcumulados() == null) {
                    sorteNaMao.setValoresAcumulados(new String[3]);
                }

                numerosSorteados = sorteNaMao.getListaNumeros();

                for (int i = 0; i < numerosSorteados.length; i++) {
                    if (numerosSorteados[i] == null) {
                        numerosSorteados[i] = "  *  ";
                    }
                }

                valoresAcumulados = sorteNaMao.getValoresAcumulados();

                for (int i = 0; i < valoresAcumulados.length; i++) {
                    if (valoresAcumulados[i] == null) {
                        valoresAcumulados[i] = "   R$ 0,00";
                    }
                }

                TextView textViewPrimeiroResultado = new TextView(getBaseContext());
                textViewPrimeiroResultado = (TextView) findViewById(R.id.textPrimeiroResultado);
                textViewPrimeiroResultado.setText(numerosSorteados[0].toString());

                TextView textViewSegundoResultado = new TextView(getBaseContext());
                textViewSegundoResultado = (TextView) findViewById(R.id.textSegundoResultado);
                textViewSegundoResultado.setText(numerosSorteados[1].toString());

                TextView textViewTerceiroResultado = new TextView(getBaseContext());
                textViewTerceiroResultado = (TextView) findViewById(R.id.textTerceiroResultado);
                textViewTerceiroResultado.setText(numerosSorteados[2].toString());

                TextView textViewQuartoResultado = new TextView(getBaseContext());
                textViewQuartoResultado = (TextView) findViewById(R.id.textQuartoResultado);
                textViewQuartoResultado.setText(numerosSorteados[3].toString());

                TextView textViewQuintoResultado = new TextView(getBaseContext());
                textViewQuintoResultado = (TextView) findViewById(R.id.textQuintoResultado);
                textViewQuintoResultado.setText(numerosSorteados[4].toString());

                TextView textViewSextoResultado = new TextView(getBaseContext());
                textViewSextoResultado = (TextView) findViewById(R.id.textSextoResultado);
                textViewSextoResultado.setText(numerosSorteados[5].toString());

                TextView textViewProximoPremio = new TextView(getBaseContext());
                textViewProximoPremio = (TextView) findViewById(R.id.proximoPremio);
                textViewProximoPremio.setText(sorteNaMao.getProximoPremio().toString());

                TextView textViewAcumulado = new TextView(getBaseContext());
                textViewAcumulado = (TextView) findViewById(R.id.valorAcumuladoProximoConcurso);
                textViewAcumulado.setText(valoresAcumulados[0].toString());

                TextView textViewAcumuladoFinal = new TextView(getBaseContext());
                textViewAcumuladoFinal = (TextView) findViewById(R.id.valorAcumuladoConcursoCinco);
                textViewAcumuladoFinal.setText(valoresAcumulados[1].toString());

                TextView textViewMegaVirada = new TextView(getBaseContext());
                textViewMegaVirada = (TextView) findViewById(R.id.valorMegaVirada);
                textViewMegaVirada.setText(valoresAcumulados[2].toString());

                progress.dismiss();
        }
    }
}

