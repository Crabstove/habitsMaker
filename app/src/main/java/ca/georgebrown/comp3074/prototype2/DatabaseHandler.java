package ca.georgebrown.comp3074.prototype2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDatabase_habits";
    public static final int DATABASE_VERSION = 1;
    public static final String col_1 = "ID";
    public static final String col_2 = "name";
    public static final String col_3 = "email";
    public static final String col_4 = "password";
    //to let checkBoxDone be use once a day - Alan
    Date lastDateDone;

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Habits( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT UNIQUE, TYPE TEXT, DAY_COUNT INTEGER, LASTDATE DATE)"); //TEXT added to let checkBoxDone be use once a day - Alan
        db.execSQL("CREATE TABLE User( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL EMAIL UNIQUE, PASSWORD PASSWORD, GENDER TEXT)");
        db.execSQL("CREATE TABLE Settings(ID INTEGER PRIMARY KEY, DISABLE_ALL INTEGER, REMINDERS INTEGER, TIPS INTEGER, RECOMMENDATIONS INTEGER, CHALLENGERS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Habits");
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Settings");
        onCreate(db);
    }

    public long insert_habit(String name, String type, int count) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("TYPE", type);
        contentValues.put("DAY_COUNT", count);

        //to let checkBoxDone be use once a day - Alan
        Date yesterday = new Date(System.currentTimeMillis()-24*60*60*1000);

        // Google java date object set time to 0s
        // Try using a Calendar object to get the current date and remove the time

        lastDateDone = yesterday;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        contentValues.put("LASTDATE", dateFormat.format(lastDateDone));

        return db.insertOrThrow("Habits", "", contentValues);
    }

    public Cursor getAllHabits() {
        SQLiteDatabase db = getReadableDatabase();
        final String query = "SELECT * FROM Habits";
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public void delete_habit(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Habits", "NAME = ? ", new String[]{name});
    }

    public void update_habit(String name, int count) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DAY_COUNT", count);
        String whereClause = "NAME=?";
        String whereArgs[] = {name};
        db.update("Habits", contentValues, whereClause, whereArgs);
    }

    public long addUser(String name, String email, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("EMAIL", email);
        contentValues.put("PASSWORD", pass);
        long res = db.insert("User", null, contentValues);
        return  res;
        //db.insertOrThrow("User", "", contentValues);
    }

    public int getUserID(String email) {
        String[] column = {col_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = col_3 + "=?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query("User",column,selection,selectionArgs,null,null,null);
        return cursor.getInt(0);
    }

    public Boolean checkUser(String email, String pass) {
        String[] columns = {col_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = col_3 + "=?" + " AND " + col_4 + "=?";
        String[] selectionArgs = {email, pass};
        Cursor cursor = db.query("User", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0) {return true;}
        else {return false;}
    }

    public Cursor getAllSettings(int id) {
        SQLiteDatabase db = getReadableDatabase();
        final String query = "SELECT * FROM Settings WHERE '" + col_1 + "'='" + id +"'";
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public long insert_Settings(int id, int dAll, int reminders, int tips, int recommendations, int challengers) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ID", id);
        contentValues.put("DISABLE_ALL", dAll);
        contentValues.put("REMINDERS", reminders);
        contentValues.put("TIPS", tips);
        contentValues.put("RECOMMENDATIONS", recommendations);
        contentValues.put("CHALLENGERS", challengers);

        long ans = db.insertOrThrow("Settings", "", contentValues);

        return ans;
    }

    public long update_All(int id, int dAll, int reminders, int tips, int recommendations, int challengers) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("DISABLE_ALL", dAll);
        contentValues.put("REMINDERS", reminders);
        contentValues.put("TIPS", tips);
        contentValues.put("RECOMMENDATIONS", recommendations);
        contentValues.put("CHALLENGERS", challengers);
        String whereClause = "ID=?";
        String whereArgs[] = {String.valueOf(id)};

        long ans = db.update("Settings", contentValues, whereClause, whereArgs);

        return ans;
    }

    public long update_Rem(int id, int onOff) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("REMINDERS", onOff);
        String whereClause = "ID=?";
        String whereArgs[] = {String.valueOf(id)};

        long ans = db.update("Settings", contentValues, whereClause, whereArgs);
        return ans;
    }

}
