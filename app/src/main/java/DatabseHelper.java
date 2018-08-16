import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

class DatabseHelper extends SQLiteOpenHelper {
    private static DatabseHelper singleton = null;
    //private static DatabaseHelper singleton = null;

    private static final String DATABASE_NAME = "weibo.db";
    private static final int DATABASE_VERSION = 16;

    static final String CREATE_ACCOUNT_TABLE_SQL = "create table " + AccountTable.TABLE_NAME
            + "("
            + AccountTable.UID + " integer primary key autoincrement,"
            + AccountTable.OAUTH_TOKEN + " text,"
            + AccountTable.OAUTH_TOKEN_SECRET + " text,"
            + AccountTable.PORTRAIT + " text,"
            + AccountTable.USERNAME + " text,"
            + AccountTable.USERNICK + " text,"
            + AccountTable.AVATAR_URL + " text,"
            + AccountTable.INFOJSON + " text"
            + ");";

    DatabseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNT_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            default:
                deleteAllTable(db);
                onCreate(db);
        }
    }

    public static synchronized DatabseHelper getInstance() {
        if (singleton == null) {
            (singleton = new DatabseHelper(Settings.GlobalContext);
        }
        return singleton;
    }

    private void deleteAllTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + AccountTable.TABLE_NAME);
    }
}
