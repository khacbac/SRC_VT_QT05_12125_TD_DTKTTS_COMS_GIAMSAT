package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Login_Log_ConstrEntity;
import com.viettel.database.field.Login_Log_ConstrField;

public class Login_Log_ConstrController {
	public static final String[] allColumn = new String[] {
		Login_Log_ConstrField.COLUMN_LOG_ID,
		Login_Log_ConstrField.COLUMN_USER_NAME,
		Login_Log_ConstrField.COLUMN_LOG_DATE,
		Login_Log_ConstrField.COLUMN_MAC
	};
	
	private Context mContext = null;
	
	public static final String CREATE_LOGIN_LOG_CONSTR_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Login_Log_ConstrField.TABLE_NAME
			+ "("
			+ Login_Log_ConstrField.COLUMN_LOG_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ Login_Log_ConstrField.COLUMN_USER_NAME
			+ " TEXT,"
			+ Login_Log_ConstrField.COLUMN_LOG_DATE
			+ " TEXT,"
			+ Login_Log_ConstrField.COLUMN_MAC
			+ " TEXT)";

	public Login_Log_ConstrController(Context pContext) {
		this.mContext = pContext;
	}
	
	public Long insertLoginLogConstr(Login_Log_ConstrEntity addItem) {
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Login_Log_ConstrField.COLUMN_USER_NAME,
				addItem.getUserName());
		values.put(Login_Log_ConstrField.COLUMN_LOG_DATE,
				addItem.getLogDate().toString());
		values.put(Login_Log_ConstrField.COLUMN_MAC,
				addItem.getMac());

		// Inserting Row
		db.insert(Login_Log_ConstrField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		return idItem;
	}
	
	public void deleteLoginLogConstr(
			Long idDel) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
	
		String[] sqlPara = new String[] { String.valueOf(idDel) };
		
		// delete Row
		db.delete(
				Login_Log_ConstrField.TABLE_NAME,
				Login_Log_ConstrField.COLUMN_LOG_ID
						+ "= ?",
						sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
	}
}
