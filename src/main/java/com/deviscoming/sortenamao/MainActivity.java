package com.deviscoming.sortenamao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.deviscoming.sortenamao.conteudo.SorteNaMaoUtil;

public class MainActivity extends AppCompatActivity {

    private Button btnMegaSena;
    private Button btnLotofacil;
    private boolean statusConexao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new ValidaConexao().execute();

        btnMegaSena = (Button) findViewById(R.id.megasena);
        btnMegaSena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                statusConexao = SorteNaMaoUtil.validaConexao(getBaseContext());

                if(statusConexao) {
                    Intent i = new Intent(MainActivity.this, MegaSenaActivity.class);
                    startActivity(i);;
                }else{
                    Intent i = new Intent(MainActivity.this, SemConexaoActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        btnLotofacil = (Button) findViewById(R.id.lotofacil);
        btnLotofacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                statusConexao = SorteNaMaoUtil.validaConexao(getBaseContext());

                if(statusConexao) {
                    Intent i = new Intent(MainActivity.this, LotoFacilActivity.class);
                    startActivity(i);;
                }else{
                    Intent i = new Intent(MainActivity.this, SemConexaoActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ValidaConexao extends AsyncTask{

        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(MainActivity.this);
            progress.setTitle("Processando...");
            progress.setMessage("Por favor, aguarde!");
            progress.setCancelable(false);
            progress.setIndeterminate(true);
            progress.show();
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
