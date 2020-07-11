package br.com.pokedex_poke.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.pokedex_poke.activity.ActKanto;
import br.com.pokedex_poke.idao.IDAO;
import br.com.pokedex_poke.infra.DBHelper;
import br.com.pokedex_poke.infra.DatabaseManager;
import br.com.pokedex_poke.model.Pokemon;
import br.com.pokedex_poke.util.FormatGson;
import br.com.pokedex_poke.util.Preferences;

public class PokemonDAO implements IDAO<Pokemon> {

    private String NOME = "NOME";
    private String NUMERO = "NUMERO";
    private String SORTUDO = "SORTUDO";
    private String SHINY = "SHINY";
    private String SOMBROSO = "SOMBROSO";
    private String PURIFICADO = "PURIFICADO";
    private String POKEMON_100 = "POKEMON_100";
    private String POKEMON_0 = "POKEMON_0";
    private String MACHO = "MACHO";
    private String FEMEA = "FEMEA";
    private String REGIAO = "REGIAO";

    public PokemonDAO(Context ctx){
        DatabaseManager.initializeInstance(new DBHelper(ctx));
    }

    @Override
    public Pokemon cursorValues(Cursor c) {
        Pokemon pokemon = new Pokemon();
        pokemon.setNUMERO( c.getString( c.getColumnIndex(NUMERO)));
        pokemon.setNOME( c.getString( c.getColumnIndex(NOME)));
        pokemon.setSORTUDO( c.getString( c.getColumnIndex(SORTUDO)));
        pokemon.setSHINY( c.getString( c.getColumnIndex(SHINY)));
        pokemon.setSOMBROSO( c.getString( c.getColumnIndex(SOMBROSO)));
        pokemon.setPURIFICADO( c.getString( c.getColumnIndex(PURIFICADO)));
        pokemon.setMACHO( c.getString( c.getColumnIndex(MACHO)));
        pokemon.setFEMEA( c.getString( c.getColumnIndex(FEMEA)));
        pokemon.setPOKEMON_100( c.getString( c.getColumnIndex(POKEMON_100)));
        pokemon.setPOKEMON_0( c.getString( c.getColumnIndex(POKEMON_0)));
        pokemon.setREGIAO( c.getString( c.getColumnIndex(REGIAO)));
        return pokemon;
    }

    @Override
    public ContentValues toValues(Pokemon pokemon) {
        ContentValues cv = new ContentValues();
        cv.put(NUMERO, pokemon.getNUMERO());
        cv.put(NOME, pokemon.getNOME());
        cv.put(SORTUDO, pokemon.getSORTUDO());
        cv.put(SHINY, pokemon.getSHINY());
        cv.put(SOMBROSO, pokemon.getSOMBROSO());
        cv.put(PURIFICADO, pokemon.getPURIFICADO());
        cv.put(MACHO, pokemon.getMACHO());
        cv.put(FEMEA, pokemon.getFEMEA());
        cv.put(POKEMON_100, pokemon.getPOKEMON_100());
        cv.put(POKEMON_0, pokemon.getPOKEMON_0());
        cv.put(REGIAO, pokemon.getREGIAO());
        return cv;
    }

    @Override
    public boolean insert(Pokemon pokemon) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        boolean inserted = false;
        try{
            db.insert(DBHelper.TBL_POKEMON,null,toValues(pokemon));
            inserted = true;
        }catch (Exception e){
            inserted = false;
        }finally {
            DatabaseManager.getInstance().closeDatabase();
        }
        return inserted;
    }

    @Override
    public Pokemon loadById(String id) {
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public Pokemon update(Pokemon pokemon) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        try{
            db.update(DBHelper.TBL_POKEMON, toValues(pokemon), this.NUMERO + " = ?", new String[]{pokemon.getNUMERO()});
        }finally {
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemon;
    }

    @Override
    public List<Pokemon> findAll() {
        String query = "SELECT * FROM " + DBHelper.TBL_POKEMON;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(query, null);
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try{
            while (c.moveToNext()){
                pokemons.add(cursorValues(c));
            }
        }finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemons;
    }

    public Pokemon loadByNumero(String numero) {
        Pokemon pokemon = null;
        String query = "SELECT * FROM " + DBHelper.TBL_POKEMON + " WHERE " + this.NUMERO + " = ?" ;
        SQLiteDatabase sd = DatabaseManager.getInstance().openDatabase();
        Cursor c = sd.rawQuery(query, new String[]{numero});

        try{
            while (c.moveToNext()){
                pokemon = cursorValues(c);
                break;
            }
        }finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemon;
    }

    public List<Pokemon> findByFiltro(String filtro, String regiao) {
        String query = filtro + this.REGIAO + " = ? order by NUMERO asc";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(query, new String[]{regiao});
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try{
            while (c.moveToNext()){
                pokemons.add(cursorValues(c));
            }
        }finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemons;
    }

    public List<Pokemon> findAllRegiao(String regiao) {
        String query = "SELECT * FROM " + DBHelper.TBL_POKEMON + " where " + this.REGIAO + " = ? ";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(query, new String[]{regiao});
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try{
            while (c.moveToNext()){
                pokemons.add(cursorValues(c));
            }
        }finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemons;
    }

    public List<Pokemon> findSortudos(String regiao) {

        // fazer por regiao

        String query = "SELECT * FROM " + DBHelper.TBL_POKEMON + " where " + this.REGIAO + " = ? and " + this.SORTUDO + " = 'S'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(query, new String[]{regiao});
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try{
            while (c.moveToNext()){
                pokemons.add(cursorValues(c));
            }
        }finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemons;
    }

    public List<Pokemon> findCem(String regiao) {
        // fazer por regiao

        String query = "SELECT * FROM " + DBHelper.TBL_POKEMON + " where " + this.REGIAO + " = ? and " + this.POKEMON_100 + " = 'S'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(query, new String[]{regiao});
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try{
            while (c.moveToNext()){
                pokemons.add(cursorValues(c));
            }
        }finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemons;
    }

    public List<Pokemon> findPurificados(String regiao) {
        // fazer por regiao

        String query = "SELECT * FROM " + DBHelper.TBL_POKEMON + " where " + this.REGIAO + " = ? and " + this.PURIFICADO + " = 'S'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(query, new String[]{regiao});
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try{
            while (c.moveToNext()){
                pokemons.add(cursorValues(c));
            }
        }finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemons;
    }

    public List<Pokemon> findSombroso(String regiao) {
        // fazer por regiao

        String query = "SELECT * FROM " + DBHelper.TBL_POKEMON + " where " + this.REGIAO + " = ? and " + this.SOMBROSO + " = 'S'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(query, new String[]{regiao});
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try{
            while (c.moveToNext()){
                pokemons.add(cursorValues(c));
            }
        }finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemons;

    }

    public List<Pokemon> findShinys(String regiao) {
        // fazer por regiao

        String query = "SELECT * FROM " + DBHelper.TBL_POKEMON + " where " + this.REGIAO + " = ? and " + this.SHINY + " = 'S'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(query, new String[]{regiao});
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try{
            while (c.moveToNext()){
                pokemons.add(cursorValues(c));
            }
        }finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemons;
    }


    public List<Pokemon> findZero(String regiao) {
        // fazer por regiao

        String query = "SELECT * FROM " + DBHelper.TBL_POKEMON + " where " + this.REGIAO + " = ? and " + this.POKEMON_0 + " = 'S'";
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.rawQuery(query, new String[]{regiao});
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        try{
            while (c.moveToNext()){
                pokemons.add(cursorValues(c));
            }
        }finally {
            c.close();
            DatabaseManager.getInstance().closeDatabase();
        }
        return pokemons;
    }
}
