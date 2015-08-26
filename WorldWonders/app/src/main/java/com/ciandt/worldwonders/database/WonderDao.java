package com.ciandt.worldwonders.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jfranco on 8/24/15.
 */
public class WonderDao implements AbstractDao<Wonder> {

    private final String TABLE_NAME = "wonders";

    private WondersSQLiteHelper sqLiteHelper;
    private SQLiteDatabase database;

    public WonderDao(Context context) {
        this.sqLiteHelper = new WondersSQLiteHelper(context);
        database = this.sqLiteHelper.getWritableDatabase();
    }

    @Override
    public List<Wonder> getAll() {
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        List<Wonder> list = new ArrayList<>();

        while(cursor.moveToNext()) {
            list.add(createFromCursor(cursor));
        }

        return list;
    }

    @Override
    public Wonder getById(int id) {
        String where = "id = ?";
        String[] whereArgs = new String[] {String.valueOf(id)};
        Cursor cursor = database.query(TABLE_NAME, null, where, whereArgs, null, null, null);
        Wonder wonder = null;

        if (cursor.moveToNext()) {
            wonder = createFromCursor(cursor);
        }

        return wonder;
    }

    @Override
    public List<Wonder> search(String word) {
        String where = "name LIKE ?";
        String[] whereArgs = new String[] {"%" + word + "%"};
        Cursor cursor = database.query(TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Wonder> list = new ArrayList<>();

        while(cursor.moveToNext()) {
            list.add(createFromCursor(cursor));
        }

        return list;
    }

    @Override
    public boolean insert(Wonder wonder) {
        ContentValues values = createContentValues(wonder);
        long rowIndex = database.insert(TABLE_NAME, null, values);
        return rowIndex != -1;
    }

    @Override
    public boolean update(Wonder wonder) {
        String where = "id = ?";
        String[] whereArgs = new String[] {String.valueOf(wonder.getId())};
        ContentValues values = createContentValues(wonder);
        int rowIndex = database.update(TABLE_NAME, values, where, whereArgs);
        return rowIndex != -1;
    }

    @Override
    public boolean delete(Wonder wonder) {
        String where = "id = ?";
        String[] whereArgs = new String[] {String.valueOf(wonder.getId())};
        int rowIndex = database.delete(TABLE_NAME, where, whereArgs);
        return rowIndex != -1;
    }

    @Override
    public void close() {
        database.close();
        sqLiteHelper.close();
    }

    private ContentValues createContentValues(Wonder wonder) {
        ContentValues values = new ContentValues();

        values.put("photo", wonder.getPhoto());
        values.put("description", wonder.getDescription());
        values.put("name", wonder.getName());
        values.put("url", wonder.getUrl());
        values.put("latitude", wonder.getLatitude());
        values.put("longitude", wonder.getLongitude());

        return values;
    }

    private Wonder createFromCursor(Cursor cursor) {
        Wonder wonder = new Wonder();

        wonder.setId(cursor.getInt(cursor.getColumnIndex("id")));
        wonder.setPhoto(cursor.getString(cursor.getColumnIndex("photo")));
        wonder.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        wonder.setName(cursor.getString(cursor.getColumnIndex("name")));
        wonder.setUrl(cursor.getString(cursor.getColumnIndex("url")));
        wonder.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
        wonder.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));

        return wonder;
    }


}
