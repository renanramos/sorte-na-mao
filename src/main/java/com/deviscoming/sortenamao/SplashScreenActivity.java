package com.deviscoming.sortenamao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Renan Rodrigues Ramos on 21/06/2016.
 */
public class SplashScreenActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    boolean statusConexao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_sortenamao);

        new CarregaDadosAsync().execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                if (statusConexao) {
                    i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    i = new Intent(SplashScreenActivity.this, SemConexaoActivity.class);
                    startActivity(i);
                    finish();
                }
            }
            },SPLASH_DISPLAY_LENGTH);
    }

    private class CarregaDadosAsync extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            if(connectivityManager.getActiveNetworkInfo() != null
                    && connectivityManager.getActiveNetworkInfo().isAvailable()
                    && connectivityManager.getActiveNetworkInfo().isConnected()){
                statusConexao = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}

