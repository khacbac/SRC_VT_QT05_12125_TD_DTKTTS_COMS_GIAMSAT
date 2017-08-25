package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Product_CompanyEntity;
import com.viettel.database.field.Product_CompanyField;

import java.util.ArrayList;
import java.util.List;

public class Product_CompanyController {
	private Context mContext = null;

	public Product_CompanyController(Context pContext) {
		this.mContext = pContext;
	}

	public static String[] allColumn = new String[] {
			Product_CompanyField.COLUMN_COMPANY_ID,
			Product_CompanyField.COLUMN_NAME,
			Product_CompanyField.COLUMN_DESCRIPTION,
			Product_CompanyField.COLUMN_IS_ACTIVE,
			Product_CompanyField.COLUMN_CODE,
			Product_CompanyField.COLUMN_STATUS,
			Product_CompanyField.COLUMN_TYPE_23G,
			Product_CompanyField.COLUMN_TYPE_TRANS,
			Product_CompanyField.COLUMN_PROCESS_ID };

	public static final String CREATE_PRODUCT_COMPANY_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Product_CompanyField.TABLE_NAME
			+ "("
			+ Product_CompanyField.COLUMN_COMPANY_ID
			+ " TEXT PRIMARY KEY,"
			+ Product_CompanyField.COLUMN_NAME
			+ " TEXT,"
			+ Product_CompanyField.COLUMN_DESCRIPTION
			+ " TEXT,"
			+ Product_CompanyField.COLUMN_TYPE_23G
			+ " INTEGER,"
			+ Product_CompanyField.COLUMN_TYPE_TRANS
			+ " INTEGER,"
			+ Product_CompanyField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Product_CompanyField.COLUMN_CODE
			+ " TEXT,"
			+ Product_CompanyField.COLUMN_STATUS
			+ " INTEGER,"
			+ Product_CompanyField.COLUMN_PROCESS_ID + " INTEGER" + ")";

	public List<Product_CompanyEntity> getListProductCompanyEntity(int iType) {
		List<Product_CompanyEntity> result = new ArrayList<Product_CompanyEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String querry = "SELECT " + Product_CompanyField.COLUMN_COMPANY_ID
				+ "," + Product_CompanyField.COLUMN_NAME + " FROM "
				+ Product_CompanyField.TABLE_NAME + " WHERE "
				+ Product_CompanyField.COLUMN_IS_ACTIVE + " = "
				+ Constants.IS_ACTIVE;
		if(iType == 1){
			querry += " AND "+Product_CompanyField.COLUMN_TYPE_23G + " = 1 ORDER BY "
					+ Product_CompanyField.COLUMN_NAME;
		} else {
			querry += " AND "+Product_CompanyField.COLUMN_TYPE_TRANS + " = 2 ORDER BY "
					+ Product_CompanyField.COLUMN_NAME;
		}
		Cursor cu = db.rawQuery(querry, null);
		while (cu.moveToNext()) {
			Product_CompanyEntity productEntity = new Product_CompanyEntity();
			productEntity.setCompanyId(cu.getLong(cu
					.getColumnIndex(Product_CompanyField.COLUMN_COMPANY_ID)));
			productEntity.setName(cu.getString(cu
					.getColumnIndex(Product_CompanyField.COLUMN_NAME)));
			result.add(productEntity);
		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return result;
	}

	public boolean insertProductCompanyEntity(
			Product_CompanyEntity companyEntity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Product_CompanyField.COLUMN_COMPANY_ID,
				companyEntity.getCompanyId());

		values.put(Product_CompanyField.COLUMN_NAME, companyEntity.getName());

		values.put(Product_CompanyField.COLUMN_CODE, companyEntity.getCode());

		values.put(Product_CompanyField.COLUMN_DESCRIPTION,
				companyEntity.getDescription());

		values.put(Product_CompanyField.COLUMN_STATUS,
				companyEntity.getStatus());

		values.put(Product_CompanyField.COLUMN_PROCESS_ID,
				companyEntity.getProcessId());

		values.put(Product_CompanyField.COLUMN_IS_ACTIVE,
				companyEntity.getIsActive());

		values.put(Product_CompanyField.COLUMN_PROCESS_ID,
				companyEntity.getProcessId());

		// Inserting Row
		Long idBtsLast = db.insert(Product_CompanyField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();
		if (idBtsLast > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Ham kiem tra xem ban ghi da ton tai chua
	 * 
	 * @param itemId Id cong trinh
	 * 
	 * @return Cong trinh duoc giam sat
	 */
	public boolean checkExitItem(long itemId) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Product_CompanyField.TABLE_NAME,
						new String[] { Product_CompanyField.COLUMN_COMPANY_ID },
						Product_CompanyField.COLUMN_COMPANY_ID + "=?",
						new String[] { String.valueOf(itemId) }, null, null,
						null, null);
		if (cursor.moveToFirst()) {
			bResult = true;
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	/**
	 * Ham cap nhat gia tri dong bo tu server ve
	 * 
	 * @param listData
	 * @return
	 */
	public boolean updateGetData(List<Product_CompanyEntity> listData) {
		boolean bResult = false;
		for (Product_CompanyEntity curItem : listData) {
			if (this.checkExitItem(curItem.getCompanyId())) {
				// TODO viet ham update
			} else {
				this.insertProductCompanyEntity(curItem);
			}
		}
		return bResult;
	}

}
