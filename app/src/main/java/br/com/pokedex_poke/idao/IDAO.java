package br.com.pokedex_poke.idao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

public interface IDAO<T> {

    public T cursorValues(Cursor c);
    public ContentValues toValues(T t);

    public boolean insert(T t);
    public T loadById(String id);
    public boolean deleteById(String id);
    public T update(T t);

    public List<T> findAll();
}
