package com.example.pokercardcal.ui.home;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class PreviewThis extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.pokercardcal.PreviewThis";
    static final String URL = "content://" + PROVIDER_NAME ;
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String NAME1 = "name1";
    static final String NAME2 = "name2";
    static final String NAME3 = "name3";
    static final String NAME4 = "name4";
    static final String score1 = "score1";
    static final String score2 = "score2";
    static final String score3 = "score3";
    static final String score4 = "score4";

    /**
     * 数据库特定常量声明
     */
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    static final String DATABASE_NAME = "history";
    static final String TABLE_NAME = "thisRound";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name1 TEXT NOT NULL, " +" name2 TEXT NOT NULL, " +" name3 TEXT NOT NULL, " +" name4 TEXT NOT NULL, " +
                    " score1 INTEGER DEFAULT(0)," + " score2 INTEGER DEFAULT(0)," + " score3 INTEGER DEFAULT(0)," + " score4 INTEGER DEFAULT(0));";

    /**
     * 创建和管理提供者内部数据源的帮助类.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        /**
         * 添加新记录
         */
        long rowID = db.insert( TABLE_NAME, "", contentValues);

        /**
         * 如果记录添加成功
         */

        if (rowID > 0)
        {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);
        Cursor c = db.query(TABLE_NAME,projection,null,null,null,null,null);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        count = db.delete(TABLE_NAME, selection, selectionArgs);
        String sql = "update sqlite_sequence set seq=0 where name='"+TABLE_NAME+"'";
        db.execSQL(sql);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
