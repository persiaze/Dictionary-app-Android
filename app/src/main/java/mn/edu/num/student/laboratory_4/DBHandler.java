package mn.edu.num.student.laboratory_4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "dictionaryApp";
    private static final int DB_VERSION = 1;

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + "words" + " ("
                + "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "wordMN" + " TEXT,"
                + "wordEN" + " TEXT"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
