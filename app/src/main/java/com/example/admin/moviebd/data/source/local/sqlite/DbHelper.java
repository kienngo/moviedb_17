package com.example.admin.moviebd.data.source.local.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "MovieDb.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String FLOAT_TYPE = " FLOAT";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_MOVIES_ENTRIES = "CREATE TABLE "
            + TrackEntry.TABLE_NAME
            + " ("
            + TrackEntry.COLUMN_NAME_ID
            + INTEGER_TYPE
            + COMMA_SEP
            + TrackEntry.COLUMN_NAME_TITLE
            + TEXT_TYPE
            + COMMA_SEP
            + TrackEntry.COLUMN_NAME_VOTE_AVERAGE
            + FLOAT_TYPE
            + COMMA_SEP
            + TrackEntry.COLUMN_NAME_POSTER_PATH
            + TEXT_TYPE
            + COMMA_SEP
            + TrackEntry.COLUMN_NAME_RELEASE_DATE
            + TEXT_TYPE
            + " )";
    private static final String SQL_DELETE_MOVIES_ENTRIES = "DROP TABLE IF EXISTS "
            + TrackEntry.TABLE_NAME;

    private static DbHelper sInstance;

    public static DbHelper getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new DbHelper(context);
        }
        return sInstance;
    }

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIES_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_MOVIES_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static final class TrackEntry implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_NAME_POSTER_PATH = "poster_path";
        public static final String COLUMN_NAME_RELEASE_DATE = "release_date";
    }
}
