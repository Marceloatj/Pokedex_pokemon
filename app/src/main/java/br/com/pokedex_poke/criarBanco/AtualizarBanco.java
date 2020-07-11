package br.com.pokedex_poke.criarBanco;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AtualizarBanco implements VersaoBanco{

	private List<VersaoBanco> versoes;
	
	public AtualizarBanco() {
		this.versoes = new ArrayList<VersaoBanco>();
	}
	
	public void addVersao(VersaoBanco versao){
		this.versoes.add(versao);
	}
	
	public void removeVersao(VersaoBanco versao){
		this.versoes.remove(versao);
	}
	
	
	@Override
	public void criarBanco(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		for(VersaoBanco v : this.versoes){
			v.criarBanco(db);
		}
		
	}

}
