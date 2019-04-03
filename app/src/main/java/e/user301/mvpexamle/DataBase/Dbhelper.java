package e.user301.mvpexamle.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import e.user301.mvpexamle.Common.UserTable;

public class Dbhelper extends SQLiteOpenHelper {

    public static final String NAME = "database.db";
    public static final int VERSION = 1;
    public Dbhelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
