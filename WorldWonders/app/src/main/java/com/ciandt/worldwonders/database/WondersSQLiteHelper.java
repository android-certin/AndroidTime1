package com.ciandt.worldwonders.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by jfranco on 8/24/15.
 */
public class WondersSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wonders.db";
    private static final String DATABASE_DIRECTORY = "data/data/com.ciandt.worldwonders/databases/";
    private static final String DATABASE_PATH = DATABASE_DIRECTORY + DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;

    public WondersSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //roda o script de update
    }

    public static void createDatabase(Context context) throws IOException {
        Log.i("WondersSQLiteHelper", "Criando database");
        InputStream original = context.getAssets().open("database/wonders.db");
        File dest = new File(DATABASE_PATH);
        if(!checkFileDatabase()) {
            copyFile(original, dest);
        }

    }

    public static boolean checkOpenDatabase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DATABASE_PATH, null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException sqlLiteException) {
            Log.e("WondersSQLiteHelper", "Banco de dados ainda não existe.", sqlLiteException);
        } finally {
            return checkDB != null;
        }
    }

    public static boolean checkFileDatabase() {
        File file =  new File(DATABASE_PATH);
        return (file.exists());
    }

    private static void copyFile(InputStream sourceInput, File destFile) throws IOException {
        File directory = new File(DATABASE_DIRECTORY);
        if(!directory.exists()) {
            directory.mkdirs();
        }

        OutputStream outStream = new FileOutputStream(destFile);

        //Transfer bytes from in to out
        byte[] buffer = new byte[1024];

        int length;
        while ((length = sourceInput.read(buffer)) > 0){
            outStream.write(buffer, 0, length);
        }
        outStream.flush();

        if (sourceInput != null)sourceInput.close();
        if (outStream != null)outStream.close();
    }

}
