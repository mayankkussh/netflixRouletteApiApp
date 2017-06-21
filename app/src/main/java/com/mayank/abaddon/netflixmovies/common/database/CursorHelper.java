package com.mayank.abaddon.netflixmovies.common.database;

import android.database.Cursor;
import android.util.Log;
import com.mayank.abaddon.netflixmovies.common.callbacks.BaseCallback;
import com.mayank.abaddon.netflixmovies.common.callbacks.FoldCallback;
import com.mayank.abaddon.netflixmovies.common.callbacks.ParameterCallback;
import com.mayank.abaddon.netflixmovies.common.callbacks.ReturnWithParameterCallback;
import java.util.ArrayList;
import java.util.List;

/**
 * A helper class which eliminates the redundant cursor iteration code
 * Created by Mayank
 */
class CursorHelper {

  private static final String TAG = CursorHelper.class.getSimpleName();

  private static CursorHelper cursorHelper;

  private CursorHelper() {
  }

  public static CursorHelper getInstance() {
    if (cursorHelper == null) cursorHelper = new CursorHelper();
    return cursorHelper;
  }

  public List<String> iterateCursor(Cursor c,
      ReturnWithParameterCallback<String, Cursor> callback) {
    List<String> response = new ArrayList<>();
    // http://stackoverflow.com/questions/14316082/cursor-window-could-not-be-created-from-binder
    try {
      if (c
          != null) { // must close cursor regardless of the count and the condition of not affected by this way
        if (c.getCount() > 0 && c.moveToFirst()) {
          do {
            try {
              if (callback.onResponse(c) != null) response.add(callback.onResponse(c));
            } catch (Exception e) {
              Log.e(TAG, "iterateCursor: ", e);
            }
          } while (c.moveToNext());
        }
        c.close();
      }
    } catch (Exception e) {
      c.close();
      Log.e(TAG, "iterateCursor: ", e);
    }
    return response;
  }

  void iterateCursor(Cursor c, ParameterCallback<Cursor> callback) {
    // http://stackoverflow.com/questions/14316082/cursor-window-could-not-be-created-from-binder
    try {
      if (c
          != null) { // must close cursor regardless of the count and the condition of not affected by this way
        if (c.getCount() > 0 && c.moveToFirst()) {
          do {
            try {
              callback.onResponse(c);
            } catch (Exception e) {
              Log.e(TAG, "iterateCursor: ", e);
            }
          } while (c.moveToNext());
        }
        c.close();
      }
    } catch (Exception e) {
      c.close();

      Log.e(TAG, "iterateCursor: ", e);
    }
  }

  <T, U> T foldCursor(Cursor c, BaseCallback<U, Cursor> callback, FoldCallback<T, U> foldFunction,
      T initialValue) {
    T result = initialValue;
    try {
      if (c != null) {
        if (c.getCount() > 0 && c.moveToFirst()) {
          do {
            try {
              result = foldFunction.onResponse(result, callback.onResponse(c));
            } catch (Exception e) {
              Log.e(TAG, "iterateCursor: ", e);
            }
          } while (c.moveToNext());
        }
        c.close();
      }
    } catch (Exception e) {
      c.close();
      Log.e(TAG, "iterateCursor: ", e);
    }

    return result;
  }

  <T> T getFirstEntry(Cursor c, BaseCallback<T, Cursor> callback) {
    T t = null;
    try {
      if (c != null) {
        if (c.getCount() > 0 && c.moveToFirst()) {
          try {
            t = callback.onResponse(c);
          } catch (Exception e) {
            Log.e(TAG, "iterateCursor: ", e);
          }
        }
        c.close();
      }
    } catch (Exception e) {
      c.close();
      Log.e(TAG, "iterateCursor: ", e);
    }

    return t;
  }
}
