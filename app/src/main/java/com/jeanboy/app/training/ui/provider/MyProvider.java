package com.jeanboy.app.training.ui.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyProvider extends ContentProvider {

    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase database;


    public static final String AUTHORITY = "com.jeanboy.myprovider";
    public static final String PATH = "user";
    public static final int USER_CODE = 1;

    private static final UriMatcher matcher;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, PATH, USER_CODE);
    }

    @Override
    public boolean onCreate() {
        context = getContext();
        dbHelper = new DBHelper(getContext());

        database = dbHelper.getWritableDatabase();
        database.execSQL("delete from user");
        database.execSQL("insert into user values(1,'Test1');");
        database.execSQL("insert into user values(2,'Test2');");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String table = getTableName(uri);
        return database.query(table, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String table = getTableName(uri);
        database.insert(table, null, values);
        context.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (matcher.match(uri)) {
            case USER_CODE:
                tableName = DBHelper.TABLE_USER;
                break;
        }
        return tableName;
    }
}
