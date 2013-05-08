package tinygsn.storage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseOpenHelper extends SQLiteOpenHelper {

	public SQLiteDatabaseOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createQuery = "CREATE TABLE vsList (_id integer primary key autoincrement,"
				+ "running, vsname, vstype, "
				+ "sswindowsize, ssstep, sssamplingrate, ssaggregator, wrappername, " 
				+ "notify_field, notify_condition, notify_value, notify_action, notify_contact, save_to_db" 
				+ ");";
		db.execSQL(createQuery);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("Drop table vsList");
    onCreate(db);
	}

}
