package com.viettel.database.entity;

import com.viettel.database.field.Product_CompanyField;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Product_CompanyEntity {
	private long companyId;
	private String name;
	private String description;
	private String code;
	private int isActive;
	private int status;
	private long processId;

	public Product_CompanyEntity() {

	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	/**
	 * Ham chuyen chuoi json ve object
	 * 
	 * @param entry
	 * @return
	 */
	public static List<Product_CompanyEntity> parseJson(JSONObject entry) {
		List<Product_CompanyEntity> listResult = new ArrayList<Product_CompanyEntity>();
		if (entry == null) {
			return listResult;
		}
		try {
			if (entry.isNull("listResult")) {
				return listResult;
			}
			JSONArray jsonMainArray = entry.getJSONArray("listResult");
			int total = jsonMainArray.length();
			for (int i = 0; i < total; i++) {

				JSONObject jsonItem = jsonMainArray.getJSONObject(i);
				Product_CompanyEntity addItem = new Product_CompanyEntity();
				addItem.setCompanyId(jsonItem
						.getInt(Product_CompanyField.COLUMN_COMPANY_ID));

				if (jsonItem.getString(Product_CompanyField.COLUMN_NAME)
						.equals("null")) {
					addItem.setName(StringUtil.EMPTY_STRING);
				} else {
					addItem.setName(jsonItem
							.getString(Product_CompanyField.COLUMN_NAME));
				}

				if (jsonItem.getString(Product_CompanyField.COLUMN_DESCRIPTION)
						.equals("null")) {
					addItem.setDescription(StringUtil.EMPTY_STRING);
				} else {
					addItem.setDescription(jsonItem
							.getString(Product_CompanyField.COLUMN_DESCRIPTION));
				}

				if (jsonItem.getString(Product_CompanyField.COLUMN_CODE)
						.equals("null")) {
					addItem.setCode(StringUtil.EMPTY_STRING);
				} else {
					addItem.setCode(jsonItem
							.getString(Product_CompanyField.COLUMN_CODE));
				}
				addItem.setIsActive(jsonItem
						.getInt(Product_CompanyField.COLUMN_IS_ACTIVE));

				addItem.setStatus(jsonItem
						.getInt(Product_CompanyField.COLUMN_STATUS));

				addItem.setProcessId(jsonItem
						.getInt(Product_CompanyField.COLUMN_PROCESS_ID));

				listResult.add(addItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	public static List<Product_CompanyEntity> parseJsonTest(JSONObject entry) {
		List<Product_CompanyEntity> listResult = new ArrayList<Product_CompanyEntity>();
		Product_CompanyEntity product1 = new Product_CompanyEntity();
		product1.setCompanyId(1);
		product1.setProcessId(1);
		product1.setName("Acterna");
		product1.setIsActive(1);

		Product_CompanyEntity product2 = new Product_CompanyEntity();
		product2.setName("Adlink");
		product2.setIsActive(1);
		product2.setCompanyId(2);
		product2.setProcessId(2);

		Product_CompanyEntity product3 = new Product_CompanyEntity();
		product3.setName("Alcaltel");
		product3.setIsActive(1);
		product3.setCompanyId(3);
		product3.setProcessId(3);

		Product_CompanyEntity product4 = new Product_CompanyEntity();
		product4.setName("Bluebird");
		product4.setIsActive(1);
		product4.setCompanyId(4);
		product4.setProcessId(4);

		Product_CompanyEntity product5 = new Product_CompanyEntity();
		product5.setName("CNC");
		product5.setIsActive(1);
		product5.setCompanyId(5);
		product5.setProcessId(5);

		Product_CompanyEntity product6 = new Product_CompanyEntity();
		product6.setName("DBG");
		product6.setIsActive(1);
		product6.setCompanyId(6);
		product6.setProcessId(6);

		Product_CompanyEntity product7 = new Product_CompanyEntity();
		product7.setName("GTV");
		product7.setIsActive(1);
		product7.setCompanyId(7);
		product7.setProcessId(7);

		Product_CompanyEntity product8 = new Product_CompanyEntity();
		product8.setName("CMK");
		product8.setIsActive(1);
		product8.setCompanyId(8);
		product8.setProcessId(8);

		Product_CompanyEntity product9 = new Product_CompanyEntity();
		product9.setName("EIX");
		product9.setIsActive(1);
		product9.setCompanyId(9);
		product9.setProcessId(9);

		Product_CompanyEntity product10 = new Product_CompanyEntity();
		product10.setName("MVC");
		product10.setIsActive(1);
		product10.setCompanyId(10);
		product10.setProcessId(10);
		
		listResult.add(product1);
		listResult.add(product2);
		listResult.add(product3);
		listResult.add(product4);
		listResult.add(product5);
		listResult.add(product6);
		listResult.add(product7);
		listResult.add(product8);
		listResult.add(product9);
		listResult.add(product10);
		return listResult;
	}
}
