package com.mayank.abaddon.netflixmovies.common.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.mayank.abaddon.netflixmovies.common.callbacks.BaseCallback;
import com.mayank.abaddon.netflixmovies.common.callbacks.ParameterCallback;
import com.mayank.abaddon.netflixmovies.common.di.master.ComponentFactory;
import javax.inject.Inject;

/**
 * Parent class of all the classes wishes to access the database
 * Created by Mayank.
 */

public class BaseDao {

  private static final String TAG = BaseDao.class.getSimpleName();
  @Inject Context context;
  private SQLiteDatabase writeableDatabase;
  private CursorHelper cursorHelper;

  protected BaseDao() {
    ComponentFactory.getInstance().getHttpComponent().inject(this);
    writeableDatabase = DbHelper.getInstance(context).getWritableDatabase();
    cursorHelper = CursorHelper.getInstance();
  }

  protected void runRawQuery(String query, ParameterCallback<Cursor> callback) {
    try {
      Cursor c = getWriteableDatabase().rawQuery(query, null);
      cursorHelper.iterateCursor(c, callback);
    } catch (IllegalStateException e) {
      Log.e(TAG, e.toString());
    }
  }

  protected <T> T getFirstRecord(String query, BaseCallback<T, Cursor> callback) {
    try {
      Cursor cursor = getWriteableDatabase().rawQuery(query, null);
      return cursorHelper.getFirstEntry(cursor, callback);
    } catch (IllegalStateException e) {
      Log.e(TAG, e.toString());
      return null;
    }
  }

  protected SQLiteDatabase getWriteableDatabase() {
    return writeableDatabase;
  }
}
