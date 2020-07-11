package br.com.pokedex_poke.criarBanco;

import android.database.sqlite.SQLiteDatabase;

public interface VersaoBanco {	
	void criarBanco(SQLiteDatabase db);
}
