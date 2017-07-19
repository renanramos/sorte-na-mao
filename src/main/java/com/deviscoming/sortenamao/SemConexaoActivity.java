package com.deviscoming.sortenamao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.deviscoming.sortenamao.conteudo.SorteNaMaoUtil;

/**
 * Created by Renan Rodrigues Ramos on 01/07/2016.
 */
public class SemConexaoActivity extends AppCompatActivity {

    private int SPLASH_DISPLAY_LENGTH = 1000;
    private Button btnTentaConexao;
    private boolean statusConexao;
    private Intent i = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_conexao_content);

        statusConexao = false;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new Conexao().execute();

        btnTentaConexao = (Button) findViewById(R.id.btnTentaConexao);
        btnTentaConexao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Conexao().execute();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (statusConexao) {
                            i = new Intent(SemConexaoActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            i = new Intent(SemConexaoActivity.this, SemConexaoActivity.class);
                            startActivity(i);
                            Toast.makeText( getBaseContext(), "Sem conexão", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                },SPLASH_DISPLAY_LENGTH);
            }
        });
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

    private class Conexao extends AsyncTask{

        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(SemConexaoActivity.this);
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
            statusConexao = SorteNaMaoUtil.validaConexao(getBaseContext());
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progress.dismiss();
        }
    }
}