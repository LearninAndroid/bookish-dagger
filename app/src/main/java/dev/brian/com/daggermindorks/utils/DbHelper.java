package dev.brian.com.daggermindorks.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import dev.brian.com.daggermindorks.Model.User;
import dev.brian.com.daggermindorks.di.ApplicationContext;
import dev.brian.com.daggermindorks.di.DatabaseInfo;

@Singleton
public class DbHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_USER_ID = "id";
    public static final String USER_COLUMN_USER_NAME = "usr_name";
    public static final String USER_COLUMN_USER_ADDRESS = "usr_add";
    public static final String USER_COLUMN_USER_CREATED_AT = "created_at";
    public static final String USER_COLUMN_USER_UPDATED_AT = "updated_at";
    @Inject
    public DbHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo Integer version){
        super(context,dbName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        tableCreateStatements(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void tableCreateStatements(SQLiteDatabase db){
        try{
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + USER_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + USER_COLUMN_USER_ADDRESS + " VARCHAR(50), "
                            + USER_COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ", "
                            + USER_COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ")"

            );

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    protected User getUser(Long userId) throws Resources.NotFoundException,NullPointerException{
        Cursor cursor = null;
        try{
            SQLiteDatabase database= this.getReadableDatabase();
            cursor  = database.rawQuery("SELECT * FROM "+USER_TABLE_NAME+" WHERE " + USER_COLUMN_USER_ID + " = ?"
            ,new String[]{userId + ""});
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CREATED_AT)));
                user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT)));
                return user;
            }else{
                throw new Resources.NotFoundException("User with id: "+userId+"Does not exist");

            }
        }catch (SQLException ex){
            ex.printStackTrace();
            throw ex;
        }finally {
            if(cursor!=null){
                cursor.close();
            }
        }

    }
    protected Long insertUser(User user) throws Exception{
        try{
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_COLUMN_USER_NAME, user.getName());
            contentValues.put(USER_COLUMN_USER_ADDRESS, user.getAddress());
            return sqLiteDatabase.insert(USER_TABLE_NAME, null, contentValues);

        }catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
    }
    private String getCurrentTimeStamp(){
        return String.valueOf(System.currentTimeMillis()/1000);
    }
}
