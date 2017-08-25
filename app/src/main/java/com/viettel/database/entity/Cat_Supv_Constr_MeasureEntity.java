package com.viettel.database.entity;

import com.viettel.database.field.Cat_Supv_Constr_MeasureField;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Cat_Supv_Constr_MeasureEntity {
	private long cat_Supv_Constr_Measure_Id;
	private String measure_Name;
	private String measure_Code;
	private int is_Active;
	private long processId;

	public Cat_Supv_Constr_MeasureEntity() {

	}

	public long getCat_Supv_Constr_Measure_Id() {
		return cat_Supv_Constr_Measure_Id;
	}

	public void setCat_Supv_Constr_Measure_Id(long cat_Supv_Constr_Measure_Id) {
		this.cat_Supv_Constr_Measure_Id = cat_Supv_Constr_Measure_Id;
	}

	public String getMeasure_Name() {
		return measure_Name;
	}

	public void setMeasure_Name(String measure_Name) {
		this.measure_Name = measure_Name;
	}

	public String getMeasure_Code() {
		return measure_Code;
	}

	public void setMeasure_Code(String measure_Code) {
		this.measure_Code = measure_Code;
	}

	public int getIs_Active() {
		return is_Active;
	}

	public void setIs_Active(int is_Active) {
		this.is_Active = is_Active;
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
	public static List<Cat_Supv_Constr_MeasureEntity> parseJson(JSONObject entry) {
		List<Cat_Supv_Constr_MeasureEntity> listResult = new ArrayList<Cat_Supv_Constr_MeasureEntity>();
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
				Cat_Supv_Constr_MeasureEntity addItem = new Cat_Supv_Constr_MeasureEntity();
				addItem.setCat_Supv_Constr_Measure_Id(jsonItem.getLong(Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID));

				if (jsonItem.getString(Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_NAME)
						.equals("null")) {
					addItem.setMeasure_Name(StringUtil.EMPTY_STRING);
				} else {
					addItem.setMeasure_Name(jsonItem
							.getString(Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_NAME));
				}

				if (jsonItem.getString(Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_CODE)
						.equals("null")) {
					addItem.setMeasure_Code(StringUtil.EMPTY_STRING);
				} else {
					addItem.setMeasure_Code(jsonItem
							.getString(Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_CODE));
				}				
				addItem.setIs_Active(jsonItem
						.getInt(Cat_Supv_Constr_MeasureField.COLUMN_IS_ACTIVE));				

				addItem.setProcessId(jsonItem
						.getInt(Cat_Supv_Constr_MeasureField.COLUMN_PROCESS_ID));

				listResult.add(addItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

//	public static List<Cat_Supv_Constr_MeasureEntity> parseJsonTest(JSONObject entry) {
//		List<Cat_Supv_Constr_MeasureEntity> listResult = new ArrayList<Cat_Supv_Constr_MeasureEntity>();
//		Cat_Supv_Constr_MeasureEntity constrMeasure1 = new Cat_Supv_Constr_MeasureEntity();
//		Cat_Supv_Constr_MeasureEntity constrMeasure2 = new Cat_Supv_Constr_MeasureEntity();
//		Cat_Supv_Constr_MeasureEntity constrMeasure3 = new Cat_Supv_Constr_MeasureEntity();
//		Cat_Supv_Constr_MeasureEntity constrMeasure4 = new Cat_Supv_Constr_MeasureEntity();
//		Cat_Supv_Constr_MeasureEntity constrMeasure5 = new Cat_Supv_Constr_MeasureEntity();
//		Cat_Supv_Constr_MeasureEntity constrMeasure6 = new Cat_Supv_Constr_MeasureEntity();
//		Cat_Supv_Constr_MeasureEntity constrMeasure7 = new Cat_Supv_Constr_MeasureEntity();
//		Cat_Supv_Constr_MeasureEntity constrMeasure8 = new Cat_Supv_Constr_MeasureEntity();
//		Cat_Supv_Constr_MeasureEntity constrMeasure9 = new Cat_Supv_Constr_MeasureEntity();
//		Cat_Supv_Constr_MeasureEntity constrMeasure10 = new Cat_Supv_Constr_MeasureEntity();
//
//		constrMeasure1.setCat_Supv_Constr_Measure_Id(1);
//		constrMeasure1.setProcessId(1);
//		constrMeasure1.setIs_Active(1);
//		constrMeasure1
//				.setMeasure_Name("Tỷ số sóng đứng (VSWR) trên toàn hệ thống");
//		constrMeasure1
//				.setMeasure_Code(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ALL);
//
//		constrMeasure2.setCat_Supv_Constr_Measure_Id(2);
//		constrMeasure2.setProcessId(2);
//		constrMeasure2.setIs_Active(1);
//		constrMeasure2
//				.setMeasure_Name("Tỷ số sóng đứng (VSWR) tại vị trí đầu Jumper 1/2 nối với máy phát");
//		constrMeasure2
//				.setMeasure_Code(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER);
//
//		constrMeasure3.setCat_Supv_Constr_Measure_Id(3);
//		constrMeasure3.setProcessId(3);
//		constrMeasure3.setIs_Active(1);
//		constrMeasure3
//				.setMeasure_Name("Tỷ số sóng đứng (VSWR) tại van thoát sét lõi Feeder");
//		constrMeasure3
//				.setMeasure_Code(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_FEEDER);
//
//		constrMeasure4.setCat_Supv_Constr_Measure_Id(4);
//		constrMeasure4.setProcessId(4);
//		constrMeasure4.setIs_Active(1);
//		constrMeasure4
//				.setMeasure_Name("Tỷ số sóng đứng (VSWR) trên Feeder, Jumper");
//		constrMeasure4
//				.setMeasure_Code(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_FEEDER);
//
//		constrMeasure5.setCat_Supv_Constr_Measure_Id(5);
//		constrMeasure5.setProcessId(5);
//		constrMeasure5.setIs_Active(1);
//		constrMeasure5
//				.setMeasure_Name("Tỷ số sóng đứng (VSWR) tại vị trí đầu Connecter");
//		constrMeasure5
//				.setMeasure_Code(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_CONNECTER);
//
//		constrMeasure6.setCat_Supv_Constr_Measure_Id(6);
//		constrMeasure6.setProcessId(6);
//		constrMeasure6.setIs_Active(1);
//		constrMeasure6
//				.setMeasure_Name("Tỷ số sóng đứng (VSWR) tại vị trí đầu anten");
//		constrMeasure6
//				.setMeasure_Code(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ANTEN);
//
//		constrMeasure7.setCat_Supv_Constr_Measure_Id(7);
//		constrMeasure7.setProcessId(7);
//		constrMeasure7.setIs_Active(1);
//		constrMeasure7
//				.setMeasure_Name("Tỷ số sóng đứng (VSWR) tại điểm cộng hưởng Anten");
//		constrMeasure7
//				.setMeasure_Code(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_RESOANT_ANTEN);
//
//		constrMeasure8.setCat_Supv_Constr_Measure_Id(8);
//		constrMeasure8.setProcessId(8);
//		constrMeasure8.setIs_Active(1);
//		constrMeasure8.setMeasure_Name("Công suất phát (dBm)");
//		constrMeasure8
//				.setMeasure_Code(Constants.BTS_CONSTR_MEASURE.WORK_CODE_CAPACITY_VIBA);
//
//		constrMeasure9.setCat_Supv_Constr_Measure_Id(9);
//		constrMeasure9.setProcessId(9);
//		constrMeasure9.setIs_Active(1);
//		constrMeasure9.setMeasure_Name("Mức thu (dBm)");
//		constrMeasure9
//				.setMeasure_Code(Constants.BTS_CONSTR_MEASURE.WORK_CODE_OBTAIN_VIBA);
//
//		constrMeasure10.setCat_Supv_Constr_Measure_Id(10);
//		constrMeasure10.setProcessId(10);
//		constrMeasure10.setIs_Active(1);
//		constrMeasure10.setMeasure_Name("Ber (dBm)");
//		constrMeasure10
//				.setMeasure_Code(Constants.BTS_CONSTR_MEASURE.WORK_CODE_BER_VIBA);
//		listResult.add(constrMeasure1);
//		listResult.add(constrMeasure2);
//		listResult.add(constrMeasure3);
//		listResult.add(constrMeasure4);
//		listResult.add(constrMeasure5);
//		listResult.add(constrMeasure6);
//		listResult.add(constrMeasure7);
//		listResult.add(constrMeasure8);
//		listResult.add(constrMeasure9);
//		listResult.add(constrMeasure10);
//		return listResult;
//	}
}
