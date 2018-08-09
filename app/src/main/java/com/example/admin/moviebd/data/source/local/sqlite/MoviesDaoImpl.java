package com.example.admin.moviebd.data.source.local.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.moviebd.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesDaoImpl implements MoviesDao {
    public static final String QUERY_SELECTION = " = ?";
    private static MoviesDaoImpl sInstance;
    private DbHelper mDbHelper;

    private MoviesDaoImpl(DbHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    public static MoviesDaoImpl getInstance(DbHelper dbHelper) {
        if (sInstance == null) {
            sInstance = new MoviesDaoImpl(dbHelper);
        }
        return sInstance;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.query(
                    DbHelper.TrackEntry.TABLE_NAME,
                    genProjection(),
                    null,
                    null,
                    null,
                    null,
                    null
            );
            movies = genMovies(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return movies;
    }

    @Override
    public Movie getMovie(long movieId) {
        Movie movie = null;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        try {
            final String selection = DbHelper.TrackEntry.COLUMN_NAME_ID + " = ?";
            final String[] selectionArgs = {String.valueOf(movieId)};
            Cursor cursor = db.query(
                    DbHelper.TrackEntry.TABLE_NAME,
                    genProjection(),
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
            if (cursor != null) {
                cursor.moveToFirst();
                movie = genMovie(cursor);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return movie;
    }

    @Override
    public boolean insertMovie(Movie movie) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(DbHelper.TrackEntry.COLUMN_NAME_ID, movie.getId());
            values.put(DbHelper.TrackEntry.COLUMN_NAME_TITLE, movie.getTitleVideo());
            values.put(DbHelper.TrackEntry.COLUMN_NAME_VOTE_AVERAGE, movie.getVoteAverage());
            values.put(DbHelper.TrackEntry.COLUMN_NAME_POSTER_PATH, movie.getPosterPath());
            values.put(DbHelper.TrackEntry.COLUMN_NAME_RELEASE_DATE, movie.getReleaseDate());
            db.insert(DbHelper.TrackEntry.TABLE_NAME, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return false;
    }

    @Override
    public boolean deleteMovie(Movie movie) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            final String selection = DbHelper.TrackEntry.COLUMN_NAME_ID + " = ? ";
            final String[] selectionArgs = {String.valueOf(movie.getId())};
            db.delete(DbHelper.TrackEntry.TABLE_NAME, selection, selectionArgs);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return false;
    }

    @Override
    public boolean isFavouriteMovie(String movieId) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DbHelper.TrackEntry.TABLE_NAME,
                null,
                DbHelper.TrackEntry.COLUMN_NAME_ID + QUERY_SELECTION,
                new String[]{movieId},
                null,
                null,
                null);
        try {
            return cursor.moveToFirst();
        } finally {
            db.close();
        }
    }

    private List<Movie> genMovies(Cursor cursor) {
        List<Movie> movies = new ArrayList<>();
        if (cursor.moveToFirst()) {
            movies = new ArrayList<>();
            do {
                movies.add(genMovie(cursor));
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return movies;
    }

    private Movie genMovie(Cursor cursor) {
        Movie movie = new Movie();
        movie.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.TrackEntry.COLUMN_NAME_ID)));
        movie.setTitleVideo(cursor.getString(cursor.getColumnIndex(DbHelper.TrackEntry.COLUMN_NAME_TITLE)));
        movie.setVoteAverage(cursor.getFloat(cursor.getColumnIndex(DbHelper.TrackEntry.COLUMN_NAME_VOTE_AVERAGE)));
        movie.setPosterPath(cursor.getString(cursor.getColumnIndex(DbHelper.TrackEntry.COLUMN_NAME_POSTER_PATH)));
        movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(DbHelper.TrackEntry.COLUMN_NAME_RELEASE_DATE)));
        return movie;
    }

    private String[] genProjection() {
        return new String[]{
                DbHelper.TrackEntry.COLUMN_NAME_ID,
                DbHelper.TrackEntry.COLUMN_NAME_TITLE,
                DbHelper.TrackEntry.COLUMN_NAME_VOTE_AVERAGE,
                DbHelper.TrackEntry.COLUMN_NAME_POSTER_PATH,
                DbHelper.TrackEntry.COLUMN_NAME_RELEASE_DATE,
        };
    }
}
