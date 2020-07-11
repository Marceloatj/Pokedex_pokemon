package br.com.pokedex_poke.infra;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.pokedex_poke.util.Pokedex_poke;

public class DatabaseManager {
    private static DatabaseManager instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;
    private int mOpenCounter;

    public static synchronized void initializeInstance(SQLiteOpenHelper sQLiteOpenHelper) {
        synchronized (DatabaseManager.class) {
            if (instance == null) {
                instance = new DatabaseManager();
                mDatabaseHelper = sQLiteOpenHelper;
            }
        }
    }

    public static synchronized DatabaseManager getInstance() {
        DatabaseManager databaseManager;
        synchronized (DatabaseManager.class) {
            if (instance == null) {
                initializeInstance(new DBHelper(Pokedex_poke.getAppContext()));
            }
            databaseManager = instance;
        }
        return databaseManager;
    }

    public synchronized SQLiteDatabase openDatabase() {
        this.mOpenCounter++;
        if (this.mOpenCounter == 1) {
            this.mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return this.mDatabase;
    }

    public synchronized void closeDatabase() {
        this.mOpenCounter--;
        if (this.mOpenCounter == 0) {
            this.mDatabase.close();
        }
    }
}