package com.mayank.abaddon.netflixmovies.common.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mayank.abaddon.netflixmovies.R;

/**
 * Created by Mayank
 */

public class DbHelper extends SQLiteOpenHelper {

  private static DbHelper dbHelper;

  private DbHelper(Context context, String databaseName, int databaseVersion) {
    super(context, databaseName, null, databaseVersion);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    // create table statements
    db.execSQL(Schema.SQL_CREATE_MOVIE_TABLE);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // handle evolutions here
  }

  public static DbHelper getInstance(Context context) {
    if (dbHelper == null) {
      String dbName = context.getPackageName() + "database.db";
      int dbVersion = context.getResources().getInteger(R.integer.database_version);
      dbHelper = new DbHelper(context, dbName, dbVersion);
    }
    return dbHelper;
  }
}
