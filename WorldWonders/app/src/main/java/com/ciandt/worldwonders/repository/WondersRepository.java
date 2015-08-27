package com.ciandt.worldwonders.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.ciandt.worldwonders.database.AbstractDao;
import com.ciandt.worldwonders.database.BookmarkDao;
import com.ciandt.worldwonders.database.WonderDao;
import com.ciandt.worldwonders.model.Bookmark;
import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jfranco on 8/25/15.
 */
public class WondersRepository {

    private Context context;
    private List<AsyncTask> tasks;

    public WondersRepository(Context context) {
        this.context = context;
        tasks = new ArrayList<>();
    }

    @NonNull
    public void getAll(final WonderAllListener wonderAllListener) {

        AsyncTask<Void, Void, List<Wonder>> asyncTask = new AsyncTask<Void, Void, List<Wonder>>() {

            @Override
            protected List<Wonder> doInBackground(Void... voids) {
                AbstractDao<Wonder> abstractDao = new WonderDao(context);
                List<Wonder> result = abstractDao.getAll();
                abstractDao.close();

                return result;
            }

            @Override
            protected void onPostExecute(List<Wonder> wonders) {
                super.onPostExecute(wonders);
                wonderAllListener.onWonderAll(null, wonders);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);

        asyncTask.execute();
    }

    @NonNull
    public void insertBookmark(final Bookmark bookmark, final BookmarkInsertListener bookmarkInsertListener) {

        AsyncTask<Bookmark, Void, Boolean> asyncTask = new AsyncTask<Bookmark, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Bookmark... bookmarks) {
                AbstractDao<Bookmark> abstractDao = new BookmarkDao(context);
                Boolean result = abstractDao.insert(bookmark);
                abstractDao.close();

                return result;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                bookmarkInsertListener.onBookmarkInsert(null, result);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);

        asyncTask.execute();
    }

    @NonNull
    public void deleteBookmark(final Bookmark bookmark, final BookmarkDeleteListener bookmarkDeleteListener) {

        AsyncTask<Bookmark, Void, Boolean> asyncTask = new AsyncTask<Bookmark, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Bookmark... bookmarks) {
                AbstractDao<Bookmark> abstractDao = new BookmarkDao(context);
                Boolean result = abstractDao.delete(bookmark);
                abstractDao.close();

                return result;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                bookmarkDeleteListener.onBookmarkDelete(null, result);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);

        asyncTask.execute();
    }

    @NonNull
    public void getAllBookmarks(final BookmarkAllListener bookmarkAllListener) {

        AsyncTask<Void, Void, List<Bookmark>> asyncTask = new AsyncTask<Void, Void, List<Bookmark>>() {

            @Override
            protected List<Bookmark> doInBackground(Void... voids) {
                AbstractDao<Bookmark> abstractDao = new BookmarkDao(context);
                List<Bookmark> result = abstractDao.getAll();
                abstractDao.close();

                return result;
            }

            @Override
            protected void onPostExecute(List<Bookmark> bookmarks) {
                super.onPostExecute(bookmarks);
                bookmarkAllListener.onBookmarkAll(null, bookmarks);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);

        asyncTask.execute();
    }

    @NonNull
    public void getBookmarkByWonder(final Integer idWonder, final BookmarkByWonderListener bookmarkByWonderListener) {

        AsyncTask<Integer, Void, Bookmark> asyncTask = new AsyncTask<Integer, Void, Bookmark>() {

            @Override
            protected Bookmark doInBackground(Integer... integers) {
                AbstractDao<Bookmark> abstractDao = new BookmarkDao(context);
                Bookmark result = abstractDao.getById(idWonder);
                abstractDao.close();

                return result;
            }

            @Override
            protected void onPostExecute(Bookmark bookmark) {
                super.onPostExecute(bookmark);
                bookmarkByWonderListener.onBookmarkByWonder(null, bookmark);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);

        asyncTask.execute();
    }

    public void cancel() {
        for(AsyncTask asyncTask: tasks) {
            if(!asyncTask.isCancelled()) {
                asyncTask.cancel(true);
            }
        }
    }

    public interface  WonderAllListener{
        void onWonderAll(Exception exception, List<Wonder> wonders);
    }

    public interface  BookmarkAllListener{
        void onBookmarkAll(Exception exception, List<Bookmark> bookmarks);
    }

    public interface BookmarkInsertListener{
        void onBookmarkInsert(Exception exception, Boolean result);
    }

    public interface BookmarkDeleteListener{
        void onBookmarkDelete(Exception exception, Boolean result);
    }

    public interface BookmarkByWonderListener{
        void onBookmarkByWonder(Exception exception, Bookmark bookmark);
    }

}
