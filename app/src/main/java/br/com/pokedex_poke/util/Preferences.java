package br.com.pokedex_poke.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_APPEND;

public class Preferences {

    private static Preferences mInstance = null;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor myEdit;
    private static Context context;


    public static synchronized Preferences getInstance(Context context) {
        setContext(context);
        if(sharedPreferences == null){
            mInstance = new Preferences();
        }
        return mInstance;
    }

    @SuppressLint("WrongConstant")
    protected Preferences(){
        sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_APPEND);
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Preferences.context = context;
    }


    private void putSharedPreferences(String tag, Object value){

        myEdit = sharedPreferences.edit();

        if(value instanceof String){
            myEdit.putString(tag, String.valueOf(value) );
        }else if(value instanceof Boolean){
            myEdit.putBoolean(tag, Boolean.valueOf(value.toString()) );
        }else if(value instanceof Integer){
            myEdit.putInt(tag, Integer.valueOf(value.toString()) );
        }

        myEdit.apply();
    }


    public String getfiltro() {
        return sharedPreferences.getString("filtro", "");
    }

    public void setFiltro(String filtro) {
        putSharedPreferences("filtro",filtro);
    }


    public void setFiltrado(boolean filtrado) {
        putSharedPreferences("filtrado",filtrado);
    }

    public boolean getFiltrado() {
        return sharedPreferences.getBoolean("Filtrado", false);
    }

    public String getRegiao() {
        return sharedPreferences.getString("regiao", "");
    }

    public void setRegiao(String regiao) {
        putSharedPreferences("regiao",regiao);
    }
}
