package com.mayank.abaddon.netflixmovies.common.database;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Mayank.
 */

public abstract class BaseOrm<T> {

  public ContentValues providesContentValues(T config) {
    ContentValues cv = new ContentValues();
    return setTableFields(cv, config);
  }

  public T providesConfig(Cursor cursor) {
    T baseSyncConfig = getInstance();
    setConfigFields(baseSyncConfig, cursor);
    return baseSyncConfig;
  }

  protected abstract T getInstance();

  protected abstract ContentValues setTableFields(ContentValues cv, T config);

  protected abstract T setConfigFields(T config, Cursor c);
}
