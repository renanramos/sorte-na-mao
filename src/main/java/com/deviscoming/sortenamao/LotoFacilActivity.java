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

import com.deviscoming.sortenamao.pojo.SorteNaMaoLotoFacil;
import com.deviscoming.sortenamao.pojo.SorteNaMaoMegaSena;
import com.deviscoming.sortenamao.conteudo.SorteNaMaoUtil;

/**
 * Created by Renan Rodrigues Ramo on 04/07/2016.
 */
public class LotoFacilActivity extends AppCompatActivity {


    private String proximoPremio;
    private String acumulado;
    private String localSorteio;
    private String[] valores = new String[15];
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private boolean statusConexao = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loto_facil);

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

        SorteNaMaoLotoFacil sorteNaMao = new SorteNaMaoLotoFacil();
        ProgressDialog progress;
        String link = "http://loterias.caixa.gov.br/wps/portal/loterias/landing/lotofacil";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(LotoFacilActivity.this);
            progress.setTitle("Processando...");
            progress.setMessage("Por favor, aguarde!");
            progress.setCancelable(false);
            progress.setIndeterminate(true);
            progress.show();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            sorteNaMao = SorteNaMaoUtil.getSorteNaMaoLotoFacil(link);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            proximoPremio = sorteNaMao.getProximoPremio();
            acumulado = sorteNaMao.getAcumulado();
            localSorteio = sorteNaMao.getLocalSorteio();
            valores = sorteNaMao.getResultado();

            if (proximoPremio == "") {
                proximoPremio = "  R$  0,00";
            }

            if (acumulado == "") {
                acumulado = "  R$  0,00";
            }

            if(localSorteio == ""){
                localSorteio = "";
            }

            for (int i = 0; i < valores.length; i++) {
                if (valores[i] == null) {
                    valores[i] = "   R$ 0,00";
                }
            }

            TextView lotoFacilPrimeiro = new TextView(getBaseContext());
            lotoFacilPrimeiro = (TextView) findViewById(R.id.resultadoLotoFacilPrimeiro);
            lotoFacilPrimeiro.setText(valores[0].toString());

            TextView lotoFacilSegundo = new TextView(getBaseContext());
            lotoFacilSegundo = (TextView) findViewById(R.id.resultadoLotoFacilSegundo);
            lotoFacilSegundo.setText(valores[1].toString());

            TextView lotoFacilTerceiro = new TextView(getBaseContext());
            lotoFacilTerceiro = (TextView) findViewById(R.id.resultadoLotoFacilTerceiro);
            lotoFacilTerceiro.setText(valores[2].toString());

            TextView lotoFacilQuarto = new TextView(getBaseContext());
            lotoFacilQuarto = (TextView) findViewById(R.id.resultadoLotoFacilQuarto);
            lotoFacilQuarto.setText(valores[3].toString());

            TextView lotoFacilQuinto = new TextView(getBaseContext());
            lotoFacilQuinto = (TextView) findViewById(R.id.resultadoLotoFacilQuinto);
            lotoFacilQuinto.setText(valores[4].toString());

            TextView lotoFacilSexto = new TextView(getBaseContext());
            lotoFacilSexto = (TextView) findViewById(R.id.resultadoLotoFacilSexto);
            lotoFacilSexto.setText(valores[5].toString());

            TextView lotoFacilSetimo = new TextView(getBaseContext());
            lotoFacilSetimo = (TextView) findViewById(R.id.resultadoLotoFacilSetimo);
            lotoFacilSetimo.setText(valores[6].toString());

            TextView lotoFacilOitavo = new TextView(getBaseContext());
            lotoFacilOitavo = (TextView) findViewById(R.id.resultadoLotoFacilOitavo);
            lotoFacilOitavo.setText(valores[7].toString());

            TextView lotoFacilNono = new TextView(getBaseContext());
            lotoFacilNono = (TextView) findViewById(R.id.resultadoLotoFacilNono);
            lotoFacilNono.setText(valores[8].toString());

            TextView lotoFacilDecimo = new TextView(getBaseContext());
            lotoFacilDecimo = (TextView) findViewById(R.id.resultadoLotoFacilDecimo);
            lotoFacilDecimo.setText(valores[9].toString());

            TextView lotoFacilDecimoPrimeiro = new TextView(getBaseContext());
            lotoFacilDecimoPrimeiro = (TextView) findViewById(R.id.resultadoLotoFacilDecimoPrimeiro);
            lotoFacilDecimoPrimeiro.setText(valores[10].toString());

            TextView lotoFacilDecimoSegundo = new TextView(getBaseContext());
            lotoFacilDecimoSegundo = (TextView) findViewById(R.id.resultadoLotoFacilDecimoSegundo);
            lotoFacilDecimoSegundo.setText(valores[11].toString());

            TextView lotoFacilDecimoTerceiro = new TextView(getBaseContext());
            lotoFacilDecimoTerceiro = (TextView) findViewById(R.id.resultadoLotoFacilDecimoTerceiro);
            lotoFacilDecimoTerceiro.setText(valores[12].toString());

            TextView lotoFacilDecimoQuarto = new TextView(getBaseContext());
            lotoFacilDecimoQuarto = (TextView) findViewById(R.id.resultadoLotoFacilDecimoQuarto);
            lotoFacilDecimoQuarto.setText(valores[13].toString());

            TextView lotoFacilDecimoQuinto = new TextView(getBaseContext());
            lotoFacilDecimoQuinto = (TextView) findViewById(R.id.resultadoLotoFacilDecimoQuinto);
            lotoFacilDecimoQuinto.setText(valores[14].toString());

            progress.dismiss();
        }
    }
}
