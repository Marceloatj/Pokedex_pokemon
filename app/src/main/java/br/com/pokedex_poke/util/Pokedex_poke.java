package br.com.pokedex_poke.util;

import android.app.Application;
import android.content.Context;

public class Pokedex_poke extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        Pokedex_poke.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Pokedex_poke.context;
    }
}
