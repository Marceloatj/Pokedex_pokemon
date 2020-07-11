package br.com.pokedex_poke.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Field;

import br.com.pokedex_poke.criarBanco.AtualizarBanco;
import br.com.pokedex_poke.criarBanco.VersaoBanco;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "pokemon.db";
    public static final int VERSAO = 1;
    public static final String TBL_POKEMON = "POKEMON";



    public static DBHelper instance;

    public DBHelper(Context context) {
        super(context, DATABASE, null, VERSAO);
    }


    public static synchronized DBHelper getInstance(Context context) {
        DBHelper dBHelper;
        synchronized (DBHelper.class) {
            if (instance == null) {
                instance = new DBHelper(context);
            }
            dBHelper = instance;
        }
        return dBHelper;
    }



    public void limparBanco() {
        Field[] declaredFields;
        for (Field field : DBHelper.class.getDeclaredFields()) {
            String str = "";
            try {
                if (field.getName().contains("TBL_")) {
                    String str2 = (String) field.get(DBHelper.class);
                    StringBuilder sb = new StringBuilder();
                    sb.append("DROP TABLE ");
                    sb.append(str2);
                    sb.append(";");
                    str = sb.toString();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
            if (str.length() > 0) {
                Log.i("LOG - SQL", str);
                getWritableDatabase().execSQL(str);
            }
        }
        onCreate(getWritableDatabase());
    }


    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        atualizarBanco(sQLiteDatabase, 0, VERSAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        atualizarBanco(sqLiteDatabase, i, i1);
    }

    private void atualizarBanco(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        AtualizarBanco atualizarBanco = new AtualizarBanco();
        while (true) {
            i++;
            if (i <= i2) {
                VersaoBanco versaoBanco = null;
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("br.com.pokedex_poke.criarBanco.Versao");
                    sb.append(i);
                    versaoBanco = (VersaoBanco) Class.forName(sb.toString()).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (ClassNotFoundException unused) {
                }

                if (versaoBanco != null) {
                    atualizarBanco.addVersao(versaoBanco);
                }
            } else {
                atualizarBanco.criarBanco(sQLiteDatabase);
                return;
            }
        }
    }

    public final SQLiteDatabase getSqliteDatabase() {
        return getWritableDatabase();
    }
}