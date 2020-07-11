package br.com.pokedex_poke.criarBanco;

import android.database.sqlite.SQLiteDatabase;

public class Versao1 implements VersaoBanco {

    private String TBL_POKEMON = "CREATE TABLE IF NOT EXISTS POKEMON ( " +
            " NOME TEXT, " +
            " NUMERO TEXT, " +
            " SORTUDO TEXT, " +
            " SOMBROSO TEXT, " +
            " PURIFICADO TEXT, " +
            " SHINY TEXT, " +
            " MACHO TEXT, " +
            " FEMEA TEXT, " +
            " POKEMON_100 TEXT, " +
            " POKEMON_0 TEXT, " +
            " REGIAO TEXT " +
            ");";


    @Override
    public void criarBanco(SQLiteDatabase db) {
        db.execSQL(TBL_POKEMON);


    }
}
