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
    public void insert(final Bookmark bookmark, final BookmarkInsertListener bookmarkInsertListener) {

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

    public interface BookmarkInsertListener{
        void onBookmarkInsert(Exception exception, Boolean result);
    }

    public interface  WonderAllListener{
        void onWonderAll(Exception exception, List<Wonder> wonders);
    }

    public void cancel() {
        for(AsyncTask asyncTask: tasks) {
            if(!asyncTask.isCancelled()) {
                asyncTask.cancel(true);
            }
        }
    }
}
