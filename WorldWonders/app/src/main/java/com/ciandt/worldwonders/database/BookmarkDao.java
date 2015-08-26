package com.ciandt.worldwonders.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ciandt.worldwonders.model.Bookmark;
import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jfranco on 8/26/15.
 */
public class BookmarkDao implements AbstractDao<Bookmark> {
    private final String TABLE_NAME = "bookmarks";

    private WondersSQLiteHelper sqLiteHelper;
    private SQLiteDatabase database;

    public BookmarkDao(Context context) {
        this.sqLiteHelper = new WondersSQLiteHelper(context);
        database = this.sqLiteHelper.getWritableDatabase();
    }

    @Override
    public List<Bookmark> getAll() {
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        List<Bookmark> list = new ArrayList<>();

        while(cursor.moveToNext()) {
            list.add(createFromCursor(cursor));
        }

        return list;
    }

    @Override
    public Bookmark getById(int id) {
        String where = "id = ?";
        String[] whereArgs = new String[] {String.valueOf(id)};
        Cursor cursor = database.query(TABLE_NAME, null, where, whereArgs, null, null, null);
        Bookmark bookmark = null;

        if (cursor.moveToNext()) {
            bookmark = createFromCursor(cursor);
        }

        return bookmark;
    }

    @Override
    public List<Bookmark> search(String word) {
        String where = "name LIKE ?";
        String[] whereArgs = new String[] {"%" + word + "%"};
        Cursor cursor = database.query(TABLE_NAME, null, where, whereArgs, null, null, null);
        List<Bookmark> list = new ArrayList<>();

        while(cursor.moveToNext()) {
            list.add(createFromCursor(cursor));
        }

        return list;
    }

    @Override
    public boolean insert(Bookmark bookmark) {
        ContentValues values = createContentValues(bookmark);
        long rowIndex = database.insert(TABLE_NAME, null, values);
        return rowIndex != -1;
    }

    @Override
    public boolean update(Bookmark bookmark) {
        String where = "id = ?";
        String[] whereArgs = new String[] {String.valueOf(bookmark.getId())};
        ContentValues values = createContentValues(bookmark);
        int rowIndex = database.update(TABLE_NAME, values, where, whereArgs);
        return rowIndex != -1;
    }

    @Override
    public boolean delete(Bookmark bookmark) {
        String where = "id = ?";
        String[] whereArgs = new String[] {String.valueOf(bookmark.getId())};
        int rowIndex = database.delete(TABLE_NAME, where, whereArgs);
        return rowIndex != -1;
    }

    @Override
    public void close() {
        database.close();
        sqLiteHelper.close();
    }

    private ContentValues createContentValues(Bookmark bookmark) {
        ContentValues values = new ContentValues();

        values.put("idWonders", bookmark.getIdWonders());

        return values;
    }

    private Bookmark createFromCursor(Cursor cursor) {
        Bookmark bookmark = new Bookmark();

        bookmark.setId(cursor.getInt(cursor.getColumnIndex("id")));
        bookmark.setId(cursor.getInt(cursor.getColumnIndex("idWonders")));
        return bookmark;
    }
}
