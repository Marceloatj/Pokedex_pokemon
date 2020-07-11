package br.com.pokedex_poke.infra;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

public interface Dao<T> {
	
	void insert(T t);
	void update(T t);
	T load(Integer id, Class<T> clazz);
	void delete(T t);
	List<T> listAll();
	
	ContentValues toValues(T t);
	
	T cursorValues(Cursor c) throws Exception;

}
